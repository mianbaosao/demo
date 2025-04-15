package org.example.suanfa.shiyan.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createTime;
    private Integer addressId;      // 外键
    private Address address;        // 一对一
    private List<Role> roles;       // 一对多
}