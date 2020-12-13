package com.example.pojo;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String email;
    private String activeCode;
    private int activeStatus;
    private int role;
}
