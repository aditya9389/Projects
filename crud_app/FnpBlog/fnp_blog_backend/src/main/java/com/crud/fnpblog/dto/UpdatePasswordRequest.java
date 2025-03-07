package com.crud.fnpblog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePasswordRequest {

    public UpdatePasswordRequest(Long userid,String newPassword){
        System.out.println("------------UPdate password req dto got accessed----------");
        this.newPassword=newPassword;
        this.userId=userid;
    }
    private Long userId;
    private String newPassword;

}
