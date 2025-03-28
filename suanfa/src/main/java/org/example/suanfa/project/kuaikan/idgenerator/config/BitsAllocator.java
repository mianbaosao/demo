package org.example.suanfa.project.kuaikan.idgenerator.config;



import java.util.ArrayList;
import java.util.List;

import org.example.suanfa.project.kuaikan.idgenerator.BufferInfo;

/**
 * @author chenkai_1
 * @time 2019-09-20 10:44
 */
public abstract class BitsAllocator {

    public List<Long> parseGeneratorBufferInfo(BufferInfo bufferInfo) {
        List<Long> ids = new ArrayList<>(100);
        long endSequence = bufferInfo.getEndSequence();
        long endDeltaTime = bufferInfo.getEndDeltaTime();
        long workerId = bufferInfo.getWorkerId();
        long sequence = bufferInfo.getStartSequence();
        long deltaTime = bufferInfo.getStartDeltaTime();
        do {
            long id = allocate(deltaTime, workerId, sequence);
            ids.add(id);
            if (sequence < getMaxSequence()) {
                sequence++;
            } else {
                deltaTime++;
                sequence = 0;
            }
        } while (deltaTime < endDeltaTime || sequence < endSequence);
        return ids;
    }

    public abstract long getMaxSequence();

    public abstract long allocate(long deltaTimestamp,long workerId,long sequence);

}
