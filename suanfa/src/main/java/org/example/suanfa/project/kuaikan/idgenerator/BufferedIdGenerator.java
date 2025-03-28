package org.example.suanfa.project.kuaikan.idgenerator;

import org.example.suanfa.project.kuaikan.idgenerator.bufferedgen.BufferedIdGeneratorClient;

/**
 * @author chenkai_1
 * @time 2019-09-04 16:46
 */
public class BufferedIdGenerator {

    /**
     * 获取唯一id
     *
     * @return long型唯一id
     */
    public static long getId() {
        return BufferedIdGeneratorClient.getInstance().getId();
    }

    public static long getRandomId() {
        return BufferedIdGeneratorClient.getInstance().getRandomId();
    }

}
