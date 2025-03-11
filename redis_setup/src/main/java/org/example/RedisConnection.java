package org.example;
import redis.clients.jedis.Jedis;

public class RedisConnection {
    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;

    public static Jedis getConnection() {
        return new Jedis(REDIS_HOST, REDIS_PORT);
    }
}
