package org.example.suanfa.shiyan.entity;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private Integer userId;
    private String street;
    private String city;
    private String country;
}