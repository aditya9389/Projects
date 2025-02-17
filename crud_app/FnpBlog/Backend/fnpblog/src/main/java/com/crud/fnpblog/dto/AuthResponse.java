package com.crud.fnpblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor  //I replaced constructor with this
public class AuthResponse {
    private String token;
}
