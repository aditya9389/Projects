package com.microservices.Authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.microservices.Authentication.exceptionhandling.AppUserNotFoundException;
import com.microservices.Authentication.model.AppUser;
import com.microservices.Authentication.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class LoginService {

    @Autowired
    private com.microservices.Authentication.service.JwtUtil jwtutil;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private CustomerDetailsService customerDetailservice;

    @Autowired
    private UserRepository userRepo;

    public AppUser userLogin(AppUser appuser) throws AppUserNotFoundException {
        final UserDetails userdetails = customerDetailservice.loadUserByUsername(appuser.getUserid());
        String userid = "";
        String role="";
        String token = "";

        AppUser user = null;
        user = userRepo.findById(appuser.getUserid()).orElse(null); //.get()

        log.info("Password From DB-->{}" ,userdetails.getPassword());
        log.info("Password From Request-->{}", encoder.encode(appuser.getPassword()) );



        if (userdetails.getPassword().equals(appuser.getPassword()) && appuser.getRole().equals(user.getRole()) ) {
            userid = appuser.getUserid();
            token = jwtutil.generateToken(userdetails);
            role = appuser.getRole();
            return new AppUser(userid, null, null, token,role);
        } else {
            throw new AppUserNotFoundException("Username/Password is incorrect...Please check");
        }
    }
}