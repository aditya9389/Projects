package org.example;
import redis.clients.jedis.Jedis;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Jedis jedis = RedisConnection.getConnection();
        String token = "session:admin";
        jedis.del(token);
        jedis.close();
        response.sendRedirect("login.jsp");
    }
}
