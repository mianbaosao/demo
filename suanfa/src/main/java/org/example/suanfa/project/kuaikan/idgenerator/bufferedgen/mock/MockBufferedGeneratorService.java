package org.example.suanfa.project.kuaikan.idgenerator.bufferedgen.mock;



import java.util.concurrent.TimeUnit;

import org.example.suanfa.project.kuaikan.idgenerator.BufferInfo;
import org.example.suanfa.project.kuaikan.idgenerator.config.BufferBitsAllocator;
import org.example.suanfa.project.kuaikan.idgenerator.service.BufferedGeneratorService;

/**
 * @author chenkai_1
 * @time 2019-09-20 12:09
 */
public class MockBufferedGeneratorService implements BufferedGeneratorService {

    @Override
    public BufferInfo get(long step) {
        long now = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        return BufferInfo.builder().workerId(0).startDeltaTime(now - EPOCH_SECONDS).endDeltaTime(now - EPOCH_SECONDS)
                .startSequence(0).endSequence(BufferBitsAllocator.MAX_SEQUENCE).build();
    }
}
