package org.example.suanfa.project.kuaikan.idgenerator.config;

import lombok.Getter;



/**
 * sign(1bit) 预留(1bit)	时间戳(30bit)	机器id(15bit)	占位(1bit)	秒内顺序递增量(16bit)
 *
 * @author chenkai_1
 * @time 2019-08-27 11:08
 */
@Getter
public class BufferBitsAllocator extends BitsAllocator {

    /**
     * Bits for
     * BufferedSnowFlake [sign -> second -> workId -> placeholder -> sequence]
     */
    public static final long SIGN_BITS = 2;
    public static final long MAX_SIGN = ~(-1 << SIGN_BITS);
    public static final long TIMESTAMP_BITS = 30L;
    public static final long WORKER_ID_BITS = 15L;
    public static final long SEQUENCE_BITS = 16L;
    public static final long MAX_DELTA_SECONDS = ~(-1L << TIMESTAMP_BITS);
    public static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    public static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);
    public static final long INIT_SEQUENCE = 0;
    public static final long PLACEHOLDER_BITS = 1L;
    public static final long SEQUENCE_SIZE = MAX_SEQUENCE + 1;
    public static final long PLACEHOLDER_SHIFT = SEQUENCE_BITS;
    public static final long WORKER_ID_SHIFT = SEQUENCE_BITS + PLACEHOLDER_BITS;
    public static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + PLACEHOLDER_BITS + WORKER_ID_BITS;

    private static BufferBitsAllocator bufferBitsAllocator = new BufferBitsAllocator();

    @Override
    public long getMaxSequence() {
        return MAX_SEQUENCE;
    }

    public long allocate(long deltaSeconds, long workerId, long sequence) {
        return ((deltaSeconds & MAX_DELTA_SECONDS) << TIMESTAMP_SHIFT) | (workerId << WORKER_ID_SHIFT) | 1 << PLACEHOLDER_SHIFT | sequence;
    }

    private BufferBitsAllocator(){};



    public static BufferBitsAllocator getInstance(){
        return bufferBitsAllocator;
    }
}
