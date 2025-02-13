package com.microservices.Authentication.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.Authentication.exceptionhandling.AppUserNotFoundException;
import com.microservices.Authentication.model.AppUser;
import com.microservices.Authentication.model.AuthenticationResponse;
import com.microservices.Authentication.repository.UserRepository;
import com.microservices.Authentication.service.CustomerDetailsService;
import com.microservices.Authentication.service.LoginService;
import com.microservices.Authentication.service.Validationservice;

@RestController
@CrossOrigin
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    // Users Repository
    @Autowired
    private UserRepository userRepository;

    // Service class login
    @Autowired
    private LoginService loginService;

    // Service class for login
    @Autowired
    private Validationservice validationService;

    @Autowired
    private CustomerDetailsService customerService;

    /**
     * The health method to check app
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheckup() {
        log.info("Health Check for Authentication Microservice");
        log.info("health checkup ----->{}", "up");
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }

    /**
     * The login method with post request
     */
    @PostMapping("/login")
    public ResponseEntity<AppUser> login(@RequestBody AppUser appUserloginCredentials)
            throws UsernameNotFoundException, AppUserNotFoundException {
        AppUser user = loginService.userLogin(appUserloginCredentials);
        log.info("Credentials ----->{}", user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    /**
     * The token validation method
     */
    @GetMapping("/validateToken")
    public AuthenticationResponse getValidity(@RequestHeader("Authorization") final String token) {
        log.info("Token Validation ----->{}", token);
        return validationService.validate(token);
    }

    /**
     * The user is created with login credentials
     */
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody AppUser appUserCredentials) {
        AppUser createduser = null;
        try {
            createduser = userRepository.save(appUserCredentials);
        } catch (Exception e) {
            log.error("User creation failed", e);
            return new ResponseEntity<>("Not created", HttpStatus.NOT_ACCEPTABLE);
        }
        log.info("User created ----->{}", createduser);
        return new ResponseEntity<>(createduser, HttpStatus.CREATED);
    }

    /**
     * The find users method to find all users
     */
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/find")
    public ResponseEntity<List<AppUser>> findUsers(@RequestHeader("Authorization") final String token) {
        List<AppUser> createdUsers = new ArrayList<>();
        List<AppUser> findAll = userRepository.findAll();
        createdUsers.addAll(findAll);
        log.info("All Users ----->{}", findAll);
        return new ResponseEntity<>(createdUsers, HttpStatus.OK);
    }

    @GetMapping("/role/{id}")
    public String getRole(@PathVariable("id") String id) {
        return userRepository.findById(id).map(AppUser::getRole).orElse("Role not found");
    }

    @DeleteMapping("/deleteCustomer/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable String id) {
        log.info("Starting deletion of --> {}", id);
        customerService.deleteCustomer(id);
        log.info("Deleted successfully");
        return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
    }
}
