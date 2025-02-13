package com.microservices.Authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appuser")
public class AppUser {

    @Id
    @Column(name = "userid", length = 20)
    @NotNull
    private String userid;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password")
    private String password;

    private String authToken;

    private String role;
}