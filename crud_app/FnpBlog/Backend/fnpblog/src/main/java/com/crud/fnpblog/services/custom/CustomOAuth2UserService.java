package com.crud.fnpblog.services.custom;

import com.crud.fnpblog.dto.AuthResponse;
import com.crud.fnpblog.model.User;
import com.crud.fnpblog.repository.UserRepository;
import com.crud.fnpblog.services.GoogleService;
import com.crud.fnpblog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final GoogleService googleService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User user = super.loadUser(userRequest);
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

        String randomPassword = "RandomPasswordForGoogleLogin";

        Optional<User> existingUser = userRepository.findByUsername(email);
        if (existingUser.isEmpty()) {
            // Register the user if not already present
            User newUser = new User();
            newUser.setEmpId(name);
            newUser.setUsername(email);
            newUser.setPassword(randomPassword);
            googleService.registerUser(newUser);
        }

        AuthResponse authResponse = googleService.loginUser(email, randomPassword);

        // Create a new attributes map to include token
        Map<String, Object> attributes = new HashMap<>(user.getAttributes());
        attributes.put("token", authResponse.getToken()); // Adding JWT token

        // Return OAuth2User with necessary attributes and token
        return new DefaultOAuth2User(
                user.getAuthorities(),
                attributes,
                "email" // Key that represents the username (email in this case)
        );
    }
}
