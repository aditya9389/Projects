package com.redis;

import redis.clients.jedis.Jedis;

public class RedisConnection {
    private static Jedis jedis = null;

    static {
        jedis = new Jedis("localhost", 6379);  // Adjust if Redis is on a different host/port
    }

    public static Jedis getConnection() {
        return jedis;
    }
}
