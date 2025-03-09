package com.crud.fnpblog.services;

import com.crud.fnpblog.dto.AuthResponse;
import com.crud.fnpblog.model.User;
import com.crud.fnpblog.repository.UserRepository;
import com.crud.fnpblog.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final RedisService redisService;

    public User registerUser(User user) {
        System.out.println("------------in registerUser method of userService----------");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("------------creating new user in database----------");
        return userRepository.save(user);
    }

    public AuthResponse loginUser(String username, String password) {

        System.out.println("------------in userLogin method of userService----------");
        System.out.println("------------Authenticate user using Spring Security----------");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        System.out.println("------------searching user in database----------");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("------------calling jwtUtil.generatoken to get new token----------");
        String token = jwtUtil.generateToken(user.getUsername());
        redisService.saveToken("authToken:"+username,token);
        System.out.println("------------returning new AuthResponse containing new token----------");
        return new AuthResponse(token);
    }

    public void updatePassword(Long userId, String newPassword) {
        System.out.println("------------in update method of userService----------");
        System.out.println("------------checking if user there or not to update----------");
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("------------setting new password----------");
        user.setPassword(passwordEncoder.encode(newPassword));
        System.out.println("------------updating user details in database----------");
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        System.out.println("------------in deleteUser method of userService----------");
        userRepository.deleteById(userId);
    }
}
