package com.crud.fnpblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
    public AuthResponse(String token)
    {
        System.out.println("------------into AuthResponse dto to save token in instance----------");
        this.token=token;
    }
    private String token;
}
