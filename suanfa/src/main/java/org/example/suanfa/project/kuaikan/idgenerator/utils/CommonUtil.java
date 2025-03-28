package org.example.suanfa.project.kuaikan.idgenerator.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chenkai_1
 * @time 2019-09-24 17:28
 */
@Slf4j
public class CommonUtil {
    private static final boolean DEFAULT_STANDALONE = false;

    public static boolean isOnStandalone() {
        if ("true".equals(System.getProperty("id_generator.standalone"))) {
            log.info("id generator get standalone property on system property: true");
            return true;
        } else if ("false".equals(System.getProperty("id_generator.standalone"))) {
            log.info("id generator get standalone property on system property: false");
            return false;
        }
        if (IdGeneratorConfig.isOnStandalone() != null) {
            log.info("id generator get standalone property on file property: {}", IdGeneratorConfig.isOnStandalone());
            return IdGeneratorConfig.isOnStandalone();
        }
        log.info("id generator get standalone property on default: false");
        return DEFAULT_STANDALONE;
    }

}
