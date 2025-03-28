package org.example.suanfa.project.kuaikan.idgenerator.bufferedgen;



import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.apache.dubbo.rpc.model.FrameworkModel;
import org.example.suanfa.project.kuaikan.idgenerator.BufferInfo;
import org.example.suanfa.project.kuaikan.idgenerator.GeneratorClient;
import org.example.suanfa.project.kuaikan.idgenerator.bufferedgen.mock.MockBufferedGeneratorService;
import org.example.suanfa.project.kuaikan.idgenerator.config.BufferBitsAllocator;
import org.example.suanfa.project.kuaikan.idgenerator.service.BufferedGeneratorService;
import org.example.suanfa.project.kuaikan.idgenerator.utils.CommonUtil;
import org.example.suanfa.project.kuaikan.idgenerator.utils.Environments;

/**
 * 双缓存id生成器
 * 注意：只保证唯一，不保证趋势递增
 * ----------------------------------------------------------------------------
 * 预留(2bit)	时间戳(30bit)	机器id(15bit)	占位(1bit)	秒内顺序递增量(16bit)
 * ----------------------------------------------------------------------------
 * 预留位：2bit，预留以后扩展
 * 时间戳：30bit，使用从某一固定时间点（2019年8月25日00点00分00秒）之后的秒数；一共可使用约34年
 * 机器id：15bit，代表id生成器服务器的id，最多为32768。
 * 占位：1bit，防止与上面的方案生成的id重复。
 * 递增顺序号：16bit，每秒生成最多 2^16=65536个id.
 *
 * @author chenkai_1
 * @time 2019-08-27 15:37
 */
@Slf4j
public class BufferedIdGeneratorClient extends GeneratorClient {

    private static final long DEFAULT_EXPAND_SEGMENT_DURATION_MILLISECOND = 15 * 60 * 1000L;
    private static final long DEFAULT_SHRINK_SEGMENT_DURATION_MILLISECOND = DEFAULT_EXPAND_SEGMENT_DURATION_MILLISECOND * 2;
    private BufferedGeneratorService bufferedGeneratorService;

    private static BufferedIdGeneratorClient instance = new BufferedIdGeneratorClient();
    private BufferBitsAllocator bufferBitsAllocator = BufferBitsAllocator.getInstance();

    private BufferedIdGeneratorClient() {
        long start = System.currentTimeMillis();
        if (CommonUtil.isOnStandalone()) {
            bufferedGeneratorService = new MockBufferedGeneratorService();
        } else {
            ReferenceConfig<BufferedGeneratorService> referenceConfig = new ReferenceConfig<>();
            referenceConfig.setInterface(BufferedGeneratorService.class);
            RegistryConfig registryConfig = new RegistryConfig(Environments.getZkAddress());
            registryConfig.setProtocol("zookeeper");
            referenceConfig.setRegistry(registryConfig);
            // ✅ 适用于 Dubbo 2.x 和 3.x
            ApplicationModel applicationModel = ApplicationModel.defaultModel();
            String serviceName = StringUtils.join(Environments.getServiceName(), "-idGenClient-buffered");
            applicationModel.setModelName(serviceName);
            DubboBootstrap.getInstance(applicationModel)
                    .application(serviceName)
                    .registry(registryConfig)
                    .protocol(new ProtocolConfig("dubbo"))
                    .reference(referenceConfig)
                    .start();
            bufferedGeneratorService = referenceConfig.get();
        }
        updateSegment(segmentBuffer.getCurrent());
        segmentBuffer.setInitOk(true);
        log.info("#buffered id generator cost: {}ms", System.currentTimeMillis() - start);
    }

    protected int getNextStep(SegmentBuffer segmentBuffer, long duration, int currStep) {
        if (duration < DEFAULT_EXPAND_SEGMENT_DURATION_MILLISECOND) {
            if (currStep * 2 <= MAX_STEP) {
                return currStep * 2;
            }
        } else if (duration > DEFAULT_SHRINK_SEGMENT_DURATION_MILLISECOND) {
            return currStep / 2 >= segmentBuffer.getMinStep() ? currStep / 2 : currStep;
        }
        return currStep;
    }

    @Override
    protected BufferInfo get(long step) {
        return bufferedGeneratorService.get(step);
    }

    @Override
    protected List<Long> parseBufferedInfo(BufferInfo bufferInfo) {
        return bufferBitsAllocator.parseGeneratorBufferInfo(bufferInfo);
    }

    public static BufferedIdGeneratorClient getInstance() {
        return instance;
    }
}
