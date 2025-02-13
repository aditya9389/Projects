package com.crud.fnpblog.controller;

import com.crud.fnpblog.dto.LoginRequest;
import com.crud.fnpblog.dto.UpdatePasswordRequest;
import com.crud.fnpblog.model.User;
import com.crud.fnpblog.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return user.isPresent() ? ResponseEntity.ok("Login successful!") : ResponseEntity.status(401).body("Invalid credentials");
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
}
