package org.example.suanfa.project.kuaikan.idgenerator.service;

import java.util.concurrent.TimeUnit;

import org.example.suanfa.project.kuaikan.idgenerator.BufferInfo;

/**
 * @author chenkai_1
 * @time 2019-08-28 12:17
 */
public interface BufferedGeneratorService {
    /**
     * epoch,unit as second.Since 2019/09/01 00:00:00
     */
    long EPOCH_SECONDS = TimeUnit.MILLISECONDS.toSeconds(1567267200000L);

    BufferInfo get(long step);

}
