package com.crud.fnpblog.model;

import com.crud.fnpblog.dto.CustomProvider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import java.util.Date;
import com.crud.fnpblog.dto.CustomProvider;

@Getter
@Setter
@Entity
@Table(name="Google_Users")
public class Google_Login {

    @Id
    @Column(nullable = false,unique = true)
    private String User_Login_Id;

    @Column
    private String username;

    @Column(unique = true,nullable = false)
    private String Email;

    @Column
    private String Current_Password;

    @Column
    private Date Last_Time_Zone;

    @Column
    private int Successive_Failed_Logins;

    @Column
    private Date Last_Updated_Stamp;

    @Column(length = 1)
    private String Password_Change_required;

    @Column
    @Enumerated(EnumType.STRING)
    private OAuth2ClientProperties.Provider provider;
//    private String authorizationUri;
//    private String tokenUri;
//    private String userInfoUri;
//    private String userInfoAuthenticationMethod;
//    private String userNameAttribute;
//    private String jwkSetUri;
//    private String issuerUri;
//    these are the fields for provider type

    @Column
    @Enumerated
    private CustomProvider customProvider;

}
