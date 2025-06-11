package com.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/v1/singin")
public class SingInServerlet extends HttpServlet {
    @Resource(name = "jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SingInServerlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> credentials = mapper.readValue(req.getInputStream(), Map.class);

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM user WHERE (username = ? OR email = ?) AND password = ?"
            );
            String usernameOrEmail = credentials.get("email");
            String password = credentials.get("password");

            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);
            stmt.setString(3, password);

            ResultSet rs = stmt.executeQuery();
            resp.setContentType("application/json");

            Map<String, Object> response = new HashMap<>();
            if (rs.next()) {
                response.put("success", true);
                response.put("userId", rs.getInt("id"));
                response.put("username", rs.getString("username"));
                response.put("email", rs.getString("email"));
            } else {
                response.put("success", false);
                response.put("message", "Invalid credentials");
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }

            mapper.writeValue(resp.getWriter(), response);

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Database error occurred");
            mapper.writeValue(resp.getWriter(), errorResponse);
        }
    }
}
