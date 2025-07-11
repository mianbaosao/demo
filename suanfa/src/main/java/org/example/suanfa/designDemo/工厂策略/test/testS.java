package org.example.suanfa.designDemo.工厂策略.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author heweitao538 2025/7/11
 */
@Configuration
@ComponentScan(basePackages = "org.example.suanfa.designDemo.工厂策略") // 添加组件扫描
public class testS {

    @Bean // 确保工厂类被注册为Bean
    public StudyTypeFactory studyTypeFactory() {
        return new StudyTypeFactory();
    }

    public static void main(String[] args) {
        // 使用Spring上下文加载配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(testS.class);

        // 从上下文中获取工厂bean
        StudyTypeFactory activityFactory = context.getBean(StudyTypeFactory.class);

        int code = 1;
        StudyType activityType = activityFactory.getByCode(code);
        activityType.test();
    }
}
