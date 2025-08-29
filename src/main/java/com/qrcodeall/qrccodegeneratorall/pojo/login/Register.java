package com.qrcodeall.qrccodegeneratorall.pojo.login;


import lombok.Data;


@Data
public class Register {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
