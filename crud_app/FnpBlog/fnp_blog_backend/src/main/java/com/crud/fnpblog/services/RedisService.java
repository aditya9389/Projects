package com.crud.fnpblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Save token with expiration time (1 hour)
    public void saveToken(String key, String token) {
        System.out.println("------saving token on redis by saveToken method of redisService class------");
        redisTemplate.opsForValue().set(key, token, Duration.ofHours(1));
        System.out.println("Token saved to Redis with key: " + key);
    }

    // Get token by key
    public String getToken(String key) {
        System.out.println("------getting token from redis by getToken method of RedisSevice class------");
        return (String) redisTemplate.opsForValue().get(key);
    }

    // Delete token by key (for logout)
    public void deleteToken(String key) {
        System.out.println("------deleting token from redis by deleteToken method of redisService class------");
        redisTemplate.delete(key);
        System.out.println("Token deleted from Redis with key: " + key);
    }
}
