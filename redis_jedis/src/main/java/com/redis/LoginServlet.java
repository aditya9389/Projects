package com.redis;

import javax.servlet.http.*;
import java.io.IOException;
import redis.clients.jedis.Jedis;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticateUser(username, password)) {  // Assume this method checks user credentials
            String sessionId = request.getSession().getId();  // Get HTTP session ID
            Jedis jedis = RedisConnection.getConnection();

            // Store session in Redis with expiration (1800 seconds = 30 minutes)
            jedis.setex("session:" + sessionId, 1800, username);
            response.getWriter().write("Login Successful!");
        } else {
            response.getWriter().write("Invalid Credentials!");
        }
    }
}
