package org.example.suanfa.designDemo.工厂策略.stag;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example.suanfa.designDemo.工厂策略.stag")
public class test {
    
    public static void main(String[] args) {
        // 使用Spring上下文加载组件
        ApplicationContext context = new AnnotationConfigApplicationContext(test.class);
        
        // 从上下文中获取工厂bean
        ActivityFactory activityFactory = context.getBean(ActivityFactory.class);
        
        int code = 1;
        ActivityType activityType = activityFactory.getActivityType(code);
        System.out.println(activityType.getDesc());
    }
}
