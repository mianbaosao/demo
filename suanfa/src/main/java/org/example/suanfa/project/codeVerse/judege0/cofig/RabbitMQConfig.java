package org.example.suanfa.project.codeVerse.judege0.cofig;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue submissionQueue() {
        return new Queue("submission-queue", true);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();

        // 设置允许反序列化的类
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTrustedPackages(
                "org.example.suanfa.project.codeVerse.judege0.entity", // 允许的包路径
                "com.your.package" // 其他允许的包
        );
        converter.setJavaTypeMapper(typeMapper);

        return converter;
    }
}