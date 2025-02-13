package com.crud.fnpblog.contoller;

import com.crud.fnpblog.model.User;
import com.crud.fnpblog.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.loginUser(username, password);
        return success ? ResponseEntity.ok("Login successful!") : ResponseEntity.badRequest().body("Invalid credentials");
    }
}
