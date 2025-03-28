package org.example.suanfa.project.kuaikan.idgenerator.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author chenkai_1
 * @time 2019-09-24 14:45
 */
@Slf4j
public class IdGeneratorConfig {
    private static final String PROPERTY_REFRESH_MINUTE = "biz.refresh_minute";
    private static final String PROPERTY_STANDALONE = "standalone";

    private static Integer refreshMinute;
    private static Boolean onStandalone;

    static {
        try {
            Resource res = new ClassPathResource("idgenerator.properties");
            Properties properties = new Properties();
            if (!res.exists()) {
                log.info("idgenerator.properties is not exist");
            } else {
                properties.load(res.getInputStream());
                String refreshMinuteStr = properties.getProperty(PROPERTY_REFRESH_MINUTE);
                if (NumberUtils.isDigits(refreshMinuteStr) && Integer.parseInt(refreshMinuteStr) > 0) {
                    refreshMinute = Integer.valueOf(refreshMinuteStr);
                }
                String standaloneStr = properties.getProperty(PROPERTY_STANDALONE);
                if ("true".equals(standaloneStr)) {
                    onStandalone = true;
                } else if ("false".equals(standaloneStr)) {
                    onStandalone = false;
                }
            }
        } catch (IOException e) {
            log.error("id generator load properties fail", e);
        }
    }

    public static Integer getRefreshMinute() {
        return refreshMinute;
    }

    public static Boolean isOnStandalone() {
        return onStandalone;
    }
}
