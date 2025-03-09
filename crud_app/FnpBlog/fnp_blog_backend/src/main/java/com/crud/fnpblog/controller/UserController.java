package com.crud.fnpblog.controller;

import com.crud.fnpblog.dto.AuthResponse;
import com.crud.fnpblog.dto.LoginRequest;
import com.crud.fnpblog.dto.UpdatePasswordRequest;
import com.crud.fnpblog.model.User;
import com.crud.fnpblog.repository.UserRepository;
import com.crud.fnpblog.security.JwtUtil;
import com.crud.fnpblog.services.FcmService;
import com.crud.fnpblog.services.RedisService;
import com.crud.fnpblog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private FcmService fcmService;

    private final UserRepository userRepository;
    private final UserService userService;
    private JwtUtil jwtUtil;
    @Autowired
    private RedisService redisService;


    @PostMapping(value = "/register", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("------------into register mapping and sending req to user service----------");
        return ResponseEntity.ok(userService.registerUser(user));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("------------into login mapping and sending req to user service----------");
        return ResponseEntity.ok(userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword()));
    }


    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest request) {
        System.out.println("------------into updatePassword mapping and sending req to user service----------");
        userService.updatePassword(request.getUserId(), request.getNewPassword());
        return ResponseEntity.ok("Password updated successfully!");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        System.out.println("------------into deleteUser mapping and sending req to user service----------");
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!");
    }


    @PostMapping("/sendNotif")
    public ResponseEntity<String> sendNotification(@RequestBody Map<String, String> request) {
        System.out.println("------------into sendNotif mapping first getting vars from request----------");
        String title = request.get("title");
        String body = request.get("body");
        String token = request.get("token");

        if (title == null || body == null || token == null) {
            return ResponseEntity.badRequest().body("Missing title, body, or token.");
        }
        System.out.println("------------from sendNotif mapping,sending req to FcmService with vars----------");
        String response = fcmService.sendNotification(title, body, token);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);
            redisService.deleteToken(username);  // Remove token from Redis
        }
        return ResponseEntity.ok("Logged out successfully!");
    }


}
