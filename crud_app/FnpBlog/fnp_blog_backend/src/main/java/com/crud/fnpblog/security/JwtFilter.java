package com.crud.fnpblog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        System.out.println("------------into JwtFilter constructor----------");
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("------------into JwtFiler's doFilterInternal method----------");
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("------------into JwtFiler's doFilterInternal method----------");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("------------congrats its not null and also its starts with Bearer ----------");
            String token = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(token);
            System.out.println("------------Extracted username and token form header----------");
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            System.out.println("------------ making newly created authorities(temp)----------");
            if (jwtUtil.validateToken(token, username)) {
                System.out.println("------------checked with jwtUtil if token and username are valid or not ----------");
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(new User(username, "", authorities), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("------------setting usernamePasswordAuthenticationToken----------");
            }
        }
        System.out.println("------------passing request and response in chain.doFilter----------");
        chain.doFilter(request, response);
    }
}
