package com.crud.fnpblog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePasswordRequest {

    private Long userId;
    private String newPassword;

}
