package org.example.suanfa.project.kuaikan.idgenerator.bizgen;


import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;



import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.apache.dubbo.rpc.model.FrameworkModel;
import org.example.suanfa.project.kuaikan.idgenerator.BufferInfo;
import org.example.suanfa.project.kuaikan.idgenerator.GeneratorClient;
import org.example.suanfa.project.kuaikan.idgenerator.bizgen.mock.MockBizIdGeneratorService;
import org.example.suanfa.project.kuaikan.idgenerator.bufferedgen.SegmentBuffer;
import org.example.suanfa.project.kuaikan.idgenerator.config.BizBitsAllocator;
import org.example.suanfa.project.kuaikan.idgenerator.service.BizGeneratorService;
import org.example.suanfa.project.kuaikan.idgenerator.utils.CommonUtil;
import org.example.suanfa.project.kuaikan.idgenerator.utils.Environments;
import org.example.suanfa.project.kuaikan.idgenerator.utils.IdGeneratorConfig;

import static org.example.suanfa.project.kuaikan.idgenerator.config.BizBitsAllocator.BIZ_ID_SHIFT;
import static org.example.suanfa.project.kuaikan.idgenerator.config.BizBitsAllocator.RESERVE_SHIFT;

/**
 * @author chenkai_1
 * @time 2019-08-29 11:41
 */
@Slf4j
public class BizIdGeneratorClient extends GeneratorClient {

    private static final int DEFAULT_REFRESH_MINUTE = 5;
    private static BizIdGeneratorClient bizIdGeneratorClient = new BizIdGeneratorClient();
    private BizGeneratorService bizGeneratorService;
    private int refreshMinute;
    private BizBitsAllocator bizBitsAllocator = BizBitsAllocator.getInstance();

    private BizIdGeneratorClient() {
        long start = System.currentTimeMillis();
        if (CommonUtil.isOnStandalone()) {
            bizGeneratorService = new MockBizIdGeneratorService();
        } else {
            ReferenceConfig<BizGeneratorService> referenceConfig = new ReferenceConfig<>();
            referenceConfig.setInterface(BizGeneratorService.class);
            RegistryConfig registryConfig = new RegistryConfig(Environments.getZkAddress());
            registryConfig.setProtocol("zookeeper");
            referenceConfig.setRegistry(registryConfig);
            referenceConfig.setCheck(true);

            // ✅ 适用于 Dubbo 2.x 和 3.x
            ApplicationModel applicationModel = ApplicationModel.defaultModel();

            String serviceName = StringUtils.join(Environments.getServiceName(), "-idGenClient-biz");
            applicationModel.setModelName(serviceName);

            DubboBootstrap.getInstance(applicationModel)
                    .application(serviceName)
                    .registry(registryConfig)
                    .protocol(new ProtocolConfig("dubbo"))
                    .reference(referenceConfig)
                    .start();
            this.bizGeneratorService = referenceConfig.get();
        }
        refreshMinute = getRefreshMinute();
        startRefresh();
        updateSegment(segmentBuffer.getCurrent());
        segmentBuffer.setInitOk(true);
        log.info("biz id generator cost: {}ms", System.currentTimeMillis() - start);
    }

    public static BizIdGeneratorClient getInstance() {
        return bizIdGeneratorClient;
    }

    private void startRefresh() {
        ScheduledExecutorService refreshExecutor = Executors.newSingleThreadScheduledExecutor(new RefreshThreadFactory());
        refreshExecutor.scheduleWithFixedDelay(() -> refresh(segmentBuffer), 10, 10, TimeUnit.SECONDS);
    }

    public long getId(long reserve, long bizId) {
        return getIdFromSegmentBuffer(segmentBuffer, 1) | (reserve << RESERVE_SHIFT) | (bizId << BIZ_ID_SHIFT);
    }

    public long getId(long bizId) {
        return getIdFromSegmentBuffer(segmentBuffer, 1) | (bizId << BIZ_ID_SHIFT);
    }

    public void switchSegment(SegmentBuffer buffer) {
        try {
            buffer.wLock().lock();
            if (buffer.isNextReady()) {
                buffer.switchPos();
                buffer.setNextReady(false);
                log.debug("##id generator refresh switch pos :{}", buffer);
            }
        } finally {
            buffer.wLock().unlock();
        }
    }

    @Override
    protected int getNextStep(SegmentBuffer segmentBuffer, long duration, int currStep) {
        if (duration < getRefreshMinute() * 60 * 1000) {
            if (currStep * 2 <= MAX_STEP) {
                return currStep * 2;
            }
            return currStep;
        }
        return currStep / 2 >= segmentBuffer.getMinStep() ? currStep / 2 : currStep;
    }

    @Override
    protected BufferInfo get(long step) {
        return bizGeneratorService.get(step);
    }

    @Override
    protected List<Long> parseBufferedInfo(BufferInfo bufferInfo) {
        return bizBitsAllocator.parseGeneratorBufferInfo(bufferInfo);
    }

    private void refresh(final SegmentBuffer buffer) {
        if (needRefresh() && buffer.getThreadRunning().compareAndSet(false, true)) {
            updateNextSegment(buffer);
            switchSegment(buffer);
            log.debug("biz-id-generator refresh {}", buffer);
        }
    }

    private static class RefreshThreadFactory implements ThreadFactory {

        private static int threadInitNumber = 0;

        private static synchronized int nextThreadNum() {
            return threadInitNumber++;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "Thread-Biz-Segment-Refresh-" + nextThreadNum());
            t.setDaemon(true);
            return t;
        }
    }

    private int getRefreshMinute() {
        String systemProperty = System.getProperty("id_generator.biz.refresh_minute");
        if (StringUtils.isNotBlank(systemProperty)) {
            if (NumberUtils.isDigits(systemProperty) && Integer.valueOf(systemProperty) > 0) {
                log.info("id generator get refresh minute on system property: {}", systemProperty);
                return Integer.valueOf(systemProperty);
            } else {
                log.warn("id generator refresh minute setting invalid, {}", systemProperty);
            }
        }
        if (IdGeneratorConfig.getRefreshMinute() != null) {
            log.info("id generator get refresh minute on file property: {}", IdGeneratorConfig.getRefreshMinute());
            return IdGeneratorConfig.getRefreshMinute();
        }
        log.info("id generator get refresh minute on default: {}", DEFAULT_REFRESH_MINUTE);
        return DEFAULT_REFRESH_MINUTE;
    }

    private boolean needRefresh() {
        return System.currentTimeMillis() - segmentBuffer.getUpdateTimestamp() > (refreshMinute * 60 * 1000L);
    }
}
