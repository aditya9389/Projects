package org.example;
import redis.clients.jedis.Jedis;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "admin123".equals(password)) {
            Jedis jedis = RedisConnection.getConnection();
            String token = "session:admin";
            jedis.setex(token, 3600, "true");  // Session expires in 1 hour
            jedis.close();
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
