package org.example.suanfa.project.HBike.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class JsonDemo {

    // 定义要映射的实体类
    @Data
    static class User {
        private String name;
        private int age;
        private Map<String, String> contacts = new HashMap<>();

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + ", contacts=" + contacts + "}";
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // --------------------- 1. 从JSON字符串解析数据 ---------------------
        String jsonInput = """
                {
                    "name": "面包",
                    "age": 21,
                    "contacts": {
                        "email": "bread@example.com",
                        "phone": "13800999999"
                    }
                }
                """;

        // 将JSON字符串转为Java对象
        User user = mapper.readValue(jsonInput, User.class);
        System.out.println("解析结果: " + user);

        // 获取具体字段值
        String email = user.getContacts().get("email");
        System.out.println("提取的邮箱: " + email);

        // --------------------- 2. 将Java对象转为JSON字符串 ---------------------
        User newUser = new User();
        newUser.setName("李四");
        newUser.setAge(30);
        newUser.getContacts().put("wechat", "lisi123");

        String jsonOutput = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(newUser);
        System.out.println("生成的JSON:\\n" + jsonOutput);
    }
}