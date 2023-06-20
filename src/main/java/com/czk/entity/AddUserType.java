package com.czk.entity;

import lombok.Data;

//该类用于注册
@Data
public class AddUserType {
    private String username;
    private String password;
    private String rePassword;
    private String email;
    private String code;
}
