package com.crud.fnpblog.security;

import com.crud.fnpblog.services.RedisService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import java.util.concurrent.TimeUnit;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Autowired
    private final RedisService redisService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public JwtFilter(JwtUtil jwtUtil,RedisService redisService) {
        System.out.println("------------into JwtFilter constructor----------");
        this.jwtUtil = jwtUtil;
        this.redisService=redisService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("------------into JwtFiler's doFilterInternal method----------");
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("------------congrats its not null and also it starts with Bearer ----------");
            String token = authorizationHeader.substring(7);
            System.out.println("token we got from req:"+token);
            String username = jwtUtil.extractUsername(token);
            System.out.println("------------Extracted username and token from header----------");

            // 🔄 Check Redis for Token
            String redisKey = "authToken:"+username;
            String cachedToken = redisService.getToken(redisKey);
            System.out.println("token we got from reddis:"+cachedToken);
            if (cachedToken != null && cachedToken.equals(token)) {
                System.out.println("------------Token is valid and found in Redis----------");

                if (jwtUtil.validateToken(token, username)) {
                    System.out.println("------------Token validated successfully----------");

                    // 🔄 Refresh Token Expiration Time in Redis
                    redisTemplate.expire(redisKey, 1, TimeUnit.HOURS);
                    System.out.println("------------Token expiration time refreshed in Redis----------");

                    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                    System.out.println("------------ making newly created authorities(temp)----------");
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(new User(username, "", authorities), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("------------setting usernamePasswordAuthenticationToken----------");
                }
            }
            else {
                System.out.println("------------Token not found in Redis or mismatched!----------");
            }
        }
        System.out.println("------------passing request and response in chain.doFilter----------");
        chain.doFilter(request, response);
    }
}

//if want to do with local storage then this will get used...upper one if for redis
// System.out.println("------------Extracted username and token form header----------");
//List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//            System.out.println("------------ making newly created authorities(temp)----------");
//            if (jwtUtil.validateToken(token, username)) {
//        System.out.println("------------checked with jwtUtil if token and username are valid or not ----------");
//UsernamePasswordAuthenticationToken authentication =
//        new UsernamePasswordAuthenticationToken(new User(username, "", authorities), null, authorities);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                System.out.println("------------setting usernamePasswordAuthenticationToken----------");