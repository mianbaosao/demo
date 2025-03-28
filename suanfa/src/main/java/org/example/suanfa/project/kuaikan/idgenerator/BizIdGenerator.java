package org.example.suanfa.project.kuaikan.idgenerator;


import java.util.Date;

import org.example.suanfa.project.kuaikan.idgenerator.bizgen.BizIdGeneratorClient;
import org.example.suanfa.project.kuaikan.idgenerator.config.BizBitsAllocator;
import org.example.suanfa.project.kuaikan.idgenerator.service.BizGeneratorService;

/**
 * @author chenkai_1
 * @time 2019-08-28 16:16
 */
public class BizIdGenerator {


    /**
     * 获取唯一id
     *
     * @return long型唯一id
     */
    public static long getId() {
        return BizIdGeneratorClient.getInstance().getId();
    }

    /**
     * 根据bizId获取唯一id
     *
     * @param bizId 业务id（范围0～4095）
     * @return long型唯一id
     */
    public static long getId(long bizId) {
        return BizIdGeneratorClient.getInstance().getId(bizId);
    }

    /**
     * 根据reserve和bizId获取唯一id
     *
     * @param reserve 保留id（范围0~1）
     * @param bizId   业务id（范围0~4095）
     * @return long型唯一id
     */
    public static long getId(long reserve, long bizId) {
        return BizIdGeneratorClient.getInstance().getId(reserve, bizId);
    }

    /**
     * 获取id中包含的bizId信息
     *
     * @param id
     * @return bizId信息
     */
    public static long getBizIdInfoFromId(long id) {
        long shift = (id >> BizBitsAllocator.BIZ_ID_SHIFT);
        return shift & BizBitsAllocator.MAX_BIZ_ID;
    }

    /**
     * 获取id中包含的reserve信息
     *
     * @param id
     * @return reserve信息
     */
    public static long getReserveInfoFromId(long id) {
        long shift = (id >> BizBitsAllocator.RESERVE_SHIFT);
        return shift & BizBitsAllocator.MAX_RESERVE;
    }

    /**
     * 获取id中包含的时间信息（已加上EPOCH时间）
     *
     * @param id
     * @return
     */
    public static long getTimestampFromId(long id) {
        long shift = (id >> BizBitsAllocator.TIMESTAMP_SHIFT);
        return (shift & BizBitsAllocator.MAX_DELTA_MILLISECONDS) + BizGeneratorService.EPOCH_TIMESTAMP;
    }

}
