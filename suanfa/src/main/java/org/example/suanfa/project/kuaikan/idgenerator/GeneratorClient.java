package org.example.suanfa.project.kuaikan.idgenerator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomUtils;
import org.example.suanfa.project.kuaikan.idgenerator.bufferedgen.Segment;
import org.example.suanfa.project.kuaikan.idgenerator.bufferedgen.SegmentBuffer;
import org.example.suanfa.project.kuaikan.idgenerator.exception.IdGeneratorException;
import org.example.suanfa.project.kuaikan.idgenerator.utils.Environments;

import com.alibaba.dubbo.rpc.RpcContext;


/**
 * @author chenkai_1
 * @time 2019-09-20 10:10
 */
@Slf4j
public abstract class GeneratorClient {

    protected static final int MAX_STEP = 10_2400;
    private ExecutorService serviceExecutor = Executors.newSingleThreadExecutor(new GeneratorClient.UpdateThreadFactory());
    protected final SegmentBuffer segmentBuffer = new SegmentBuffer();

    protected abstract BufferInfo get(long step);

    protected abstract List<Long> parseBufferedInfo(BufferInfo bufferInfo);

    public long getId() {
        return getIdFromSegmentBuffer(segmentBuffer, 1);
    }

    public long getRandomId() {
        int randomStep = RandomUtils.nextInt(1, segmentBuffer.getMinStep());
        return getIdFromSegmentBuffer(segmentBuffer, randomStep);
    }

    public void updateSegment(Segment segment) {
        RpcContext.getContext().setAttachment("serviceName", Environments.getServiceName());
        SegmentBuffer segmentBuffer = segment.getSegmentBuffer();
        if (!segmentBuffer.isInitOk() || segmentBuffer.getUpdateTimestamp() == 0) {
            BufferInfo bufferInfo = get(segmentBuffer.getStep());
            segmentBuffer.setUpdateTimestamp(System.currentTimeMillis());
            segment.setIds(parseBufferedInfo(bufferInfo));
        } else {
            long duration = System.currentTimeMillis() - segmentBuffer.getUpdateTimestamp();
            int currStep = segmentBuffer.getStep();
            int nextStep = getNextStep(segmentBuffer, duration, currStep);
            BufferInfo bufferInfo = get(nextStep);
            segmentBuffer.setStep(nextStep);
            segmentBuffer.setUpdateTimestamp(System.currentTimeMillis());
            segment.setIds(parseBufferedInfo(bufferInfo));
            segment.initPos();
        }
    }

    public void waitAndSleep(SegmentBuffer buffer) {
        log.debug("id generator wait and sleep start,{}", buffer.getThreadRunning().get());
        int roll = 0;
        while (buffer.getThreadRunning().get()) {
            try {
                roll += 1;
                TimeUnit.MILLISECONDS.sleep(1);
                if (roll > 100) {
                    break;
                }
            } catch (InterruptedException e) {
                log.warn("id thread {} interrupted", Thread.currentThread().getName());
                break;
            }
        }
    }

    protected abstract int getNextStep(SegmentBuffer segmentBuffer, long duration, int currStep);

    protected long getIdFromSegmentBuffer(final SegmentBuffer buffer, int step) {
        while (true) {
            try {
                buffer.rLock().lock();
                final Segment segment = buffer.getCurrent();
                double updateThreshold = 0.9;
                if (!buffer.isNextReady()
                        && (segment.getIdle() < updateThreshold * segment.getMaxPos())
                        && buffer.getThreadRunning().compareAndSet(false, true)) {
                    asyncUpdateNextSegment(buffer);
                }
                long value = segment.getValue(step);
                if (value != -1L) {
                    return value;
                }
            } finally {
                buffer.rLock().unlock();
            }
            waitAndSleep(buffer);
            try {
                buffer.wLock().lock();
                final Segment segment = buffer.getCurrent();
                long value = segment.getValue(step);
                if (value != -1L) {
                    return value;
                }
                if (buffer.isNextReady()) {
                    buffer.switchPos();
                    buffer.setNextReady(false);
                } else {
                    log.error("##id generator both two segments in {} not ready", buffer);
                    throw new IdGeneratorException("Two segments are null");
                }
            } finally {
                buffer.wLock().unlock();
            }
        }
    }

    protected void updateNextSegment(SegmentBuffer buffer) {
        Segment next = buffer.getSegments()[buffer.nextPos()];
        boolean updateOk = false;
        try {
            updateSegment(next);
            updateOk = true;
            log.info("id generator update segment {} ", next);
        } catch (Exception e) {
            log.error("id generator update segment error {}", next, e);
        } finally {
            if (updateOk) {
                buffer.wLock().lock();
                buffer.setNextReady(true);
                buffer.getThreadRunning().set(false);
                buffer.wLock().unlock();
            } else {
                buffer.getThreadRunning().set(false);
            }
        }
    }

    private void asyncUpdateNextSegment(SegmentBuffer buffer) {
        serviceExecutor.execute(() -> {
            updateNextSegment(buffer);
        });
    }

    private static class UpdateThreadFactory implements ThreadFactory {

        private static int threadInitNumber = 0;

        private static synchronized int nextThreadNum() {
            return threadInitNumber++;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "Thread-Biz-Segment-Update-" + nextThreadNum());
        }
    }

}
