package org.example.suanfa.designDemo.工厂策略.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author heweitao538 2025/7/10
 */
@Configuration
@ComponentScan("org.example.suanfa.designDemo.工厂策略.test")
public class tst {
    public static void main(String[] args) {
        // Initialize Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(tst.class);
        
        // Get the factory from Spring context
        runTypeFactory factory = context.getBean(runTypeFactory.class);
        
        // Use the factory
        runType a = factory.getType(1);
        String name = a.getName();
        System.out.println(name);
        a.test();
        
        // Close the context
        context.close();
    }
}
