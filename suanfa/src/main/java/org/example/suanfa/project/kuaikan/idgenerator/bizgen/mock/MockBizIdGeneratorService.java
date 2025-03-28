package org.example.suanfa.project.kuaikan.idgenerator.bizgen.mock;

import org.example.suanfa.project.kuaikan.idgenerator.BufferInfo;
import org.example.suanfa.project.kuaikan.idgenerator.config.BizBitsAllocator;
import org.example.suanfa.project.kuaikan.idgenerator.service.BizGeneratorService;

/**
 * @author chenkai_1
 * @time 2019-09-20 12:20
 */
public class MockBizIdGeneratorService implements BizGeneratorService {
    @Override
    public BufferInfo get(long step) {
        long now = System.currentTimeMillis();
        return BufferInfo.builder().workerId(0).startDeltaTime(now - EPOCH_TIMESTAMP).endDeltaTime(now - EPOCH_TIMESTAMP)
                .startSequence(0).endSequence(BizBitsAllocator.MAX_SEQUENCE).build();
    }
}
