package org.example;
import redis.clients.jedis.Jedis;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Jedis jedis = RedisConnection.getConnection();
        String token = "session:admin";
        String session = jedis.get(token);
        jedis.close();

        if ("true".equals(session)) {
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
