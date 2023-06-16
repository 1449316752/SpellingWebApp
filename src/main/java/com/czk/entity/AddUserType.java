package com.czk.entity;

import lombok.Data;

@Data
public class AddUserType {
    private String username;
    private String password;
    private String rePassword;
    private String email;
    private String code;
}
