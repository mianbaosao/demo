package org.example.suanfa.project.kuaikan.idgenerator.service;

import org.example.suanfa.project.kuaikan.idgenerator.BufferInfo;

/**
 * @author chenkai_1
 * @time 2019-08-28 14:12
 */
public interface BizGeneratorService {
    /**
     * same as kk-idgenerator-api
     */
    long EPOCH_TIMESTAMP = 1464591108105L;

    BufferInfo get(long step);
}
