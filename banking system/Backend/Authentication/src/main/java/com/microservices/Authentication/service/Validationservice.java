package com.microservices.Authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservices.Authentication.model.AuthenticationResponse;
import com.microservices.Authentication.repository.UserRepository;

@Component
public class Validationservice {

    @Autowired
    private com.microservices.Authentication.service.JwtUtil jwtutil;
    @Autowired
    private UserRepository userRepo;

    public AuthenticationResponse validate(String token) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        String jwt = token;

        if (jwtutil.validateToken(jwt)) {
            authenticationResponse.setUserid(jwtutil.extractUsername(jwt));
            authenticationResponse.setValid(true);
            authenticationResponse.setName(userRepo.findById(jwtutil.extractUsername(jwt)).get().getUsername());
        } else {
            authenticationResponse.setValid(false);
        }
        return authenticationResponse;
    }
}