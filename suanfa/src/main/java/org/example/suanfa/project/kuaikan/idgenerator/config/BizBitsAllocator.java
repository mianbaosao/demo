package org.example.suanfa.project.kuaikan.idgenerator.config;

import lombok.Getter;



/**
 * sign(1bit) 预留(1bit)	时间戳(39bit)	业务id(12bit)	机器标识(7bit)	毫秒内顺序递增量(4bit)
 *
 * @author chenkai_1
 * @time 2019-08-28 15:46
 */
@Getter
public class BizBitsAllocator extends BitsAllocator {
    /**
     * Bits for
     * BizSnowFlake [sign -> millisecond -> bizId -> workId ->sequence]
     */
    public static final long SIGN_BITS = 2;
    public static final long MAX_SIGN = ~(-1 << SIGN_BITS);
    public static final long TIMESTAMP_BITS = 39L;
    public static final long WORKER_ID_BITS = 7L;
    public static final long SEQUENCE_BITS = 4L;
    public static final long RESERVE_BITS = 1L;
    public static final long INIT_SEQUENCE = 0;
    public static final long BIZ_ID_BITS = 12L;
    public static final long MAX_BIZ_ID = ~(-1L << BIZ_ID_BITS);
    public static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    public static final long MAX_RESERVE = ~(-1L << RESERVE_BITS);
    public static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);
    public static final long MAX_DELTA_MILLISECONDS = ~(-1L << TIMESTAMP_BITS);
    public static final long RESERVE_MASK = MAX_RESERVE;
    public static final long SEQUENCE_SIZE = MAX_SEQUENCE + 1;
    public static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    public static final long BIZ_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    public static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + BIZ_ID_BITS;
    public static final long RESERVE_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + BIZ_ID_BITS + TIMESTAMP_BITS;

    private static BizBitsAllocator bizBitsAllocator = new BizBitsAllocator();

    @Override
    public long getMaxSequence() {
        return MAX_SEQUENCE;
    }

    @Override
    public long allocate(long deltaTimestamp, long workerId, long sequence) {
        return  ((deltaTimestamp & MAX_DELTA_MILLISECONDS) << TIMESTAMP_SHIFT) | (workerId << WORKER_ID_SHIFT) | sequence;
    }

    private BizBitsAllocator(){};


    public static BizBitsAllocator getInstance(){
        return bizBitsAllocator;
    }
}
