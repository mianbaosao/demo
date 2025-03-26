package org.example.suanfa.project.dubboFilter;


import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.example.suanfa.project.dubboFilter.res.RpcResult;
import org.example.suanfa.project.dubboFilter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableDubbo
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @DubboReference(version = "1.0.0", check = false)
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        logger.info("=====================================================");
        logger.info("应用启动完成，开始调用服务");
        try {
            RpcResult result = userService.getUser("123");
            logger.info("调用结果: code={}, message={}", result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error("调用服务失败", e);
        }
        logger.info("=====================================================");
    }
}