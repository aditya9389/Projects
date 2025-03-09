package com.crud.fnpblog.controller;

import com.crud.fnpblog.security.JwtUtil;
import com.crud.fnpblog.services.RedisService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class GoogleController implements AuthenticationSuccessHandler {

    @Autowired
    private RedisService redisService;
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("------------Google Controller getting Accessed----------");
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        Map<String, Object> attributes = token.getPrincipal().getAttributes();

        String jwtToken = (String) attributes.get("token");
        String username=jwtUtil.extractUsername(jwtToken);
        redisService.saveToken("authToken:"+username,jwtToken);
        String redirectUrl = "http://localhost:4200/login?token=" + jwtToken;
        response.sendRedirect(redirectUrl);
        System.out.println("------------Google Controller Accessed----------");
    }
}
