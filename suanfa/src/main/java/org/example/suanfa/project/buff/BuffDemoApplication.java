package org.example.suanfa.project.buff;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.suanfa.project.buff.dao")
public class BuffDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuffDemoApplication.class, args);
    }
}