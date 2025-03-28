package org.example.suanfa.project.kuaikan.database;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * Created by yushixiang on 9/6/16.
 */
@Slf4j
public class Settings {
    private static String ENV_NAME = "env";
    private static String SERVICE_NAME = "serviceName";
    private static String DINGDING_WARN_CHAT_ID = "dingdingWarnChatId";
    private static String PROVIDER_GROUP = "providerGroup";
    public final static String DEFAULT_CHATID = "chat3eadfaa2cef84ef0442a3d1205fc59e2";
    public final static String STAG_CHATID = "chatad815d32e55f7688d2dd78fff1cdc344";

    private static Environment environment;
    private static String serviceName;
    private static String dingdingWarnChatId;
    private static String providerGroup;


    static {
        //初始化host和port数值
        Resource res = new ClassPathResource("setting.properties");
        Properties properties = new Properties();
        try {
            if (res.exists()) {
                properties.load(res.getInputStream());
                String env = properties.getProperty(ENV_NAME, Environment.LOCAL.name());
                try{
                    environment = Environment.valueOf(env.toUpperCase());
                    environment = null == environment ? Environment.LOCAL : environment;
                }catch (Exception e){
                    environment = Environment.LOCAL;
                }
                serviceName = properties.getProperty(SERVICE_NAME, "");
                String defaultChatId = environment.isProd() ? DEFAULT_CHATID : STAG_CHATID;
                dingdingWarnChatId = properties.getProperty(DINGDING_WARN_CHAT_ID, defaultChatId);
                if (StringUtils.isBlank(dingdingWarnChatId)) {
                    dingdingWarnChatId = defaultChatId;
                }
                providerGroup = properties.getProperty(PROVIDER_GROUP, "");
            }
            else {
                throw new RuntimeException("settings.properties not exist");
            }
        }catch (Exception e){
            log.error("setting.properties parse error ", e);
            environment = Environment.LOCAL;
            serviceName = "";
            dingdingWarnChatId = STAG_CHATID;
            providerGroup = "";
        }
        log.info("env {}", environment.name());
        log.info("serviceName {}", serviceName);
        log.info("dingdingWarnChatId {}", dingdingWarnChatId);
        log.info("providerGroup {}", providerGroup);
    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static String getServiceName() {
        return serviceName;
    }

    public static String getDingdingWarnChatId() {
        return dingdingWarnChatId;
    }

    public static String getProviderGroup() {
        return providerGroup;
    }
}
