package com.crud.fnpblog.controller;

import com.crud.fnpblog.dto.AuthResponse;
import com.crud.fnpblog.dto.LoginRequest;
import com.crud.fnpblog.dto.UpdatePasswordRequest;
import com.crud.fnpblog.model.User;
import com.crud.fnpblog.repository.UserRepository;
import com.crud.fnpblog.services.FcmService;
import com.crud.fnpblog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private FcmService fcmService;

    private final UserRepository userRepository;
    private final UserService userService;


    @PostMapping(value = "/register", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword()));
    }


    // Update password endpoint using JSON
    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(request.getUserId(), request.getNewPassword());
        return ResponseEntity.ok("Password updated successfully!");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!");
    }


    @PostMapping("/sendNotif")
    public ResponseEntity<String> sendNotification(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        String body = request.get("body");
        String token = request.get("token");

        if (title == null || body == null || token == null) {
            return ResponseEntity.badRequest().body("Missing title, body, or token.");
        }

        String response = fcmService.sendNotification(title, body, token);
        return ResponseEntity.ok(response);
    }

}
