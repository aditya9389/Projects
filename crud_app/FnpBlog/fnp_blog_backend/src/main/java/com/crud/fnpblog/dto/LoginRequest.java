package com.crud.fnpblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    public LoginRequest(String username,String password)
    {
        System.out.println("------------into LoginRequest dto to save username,password in an instance----------");
        this.username=username;
        this.password=password;
    }
    private String username;
    private String password;
}
