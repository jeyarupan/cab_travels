package servlets;
import db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT user_id, full_name, user_role FROM app_users WHERE email=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", rs.getInt("user_id"));  // Store user ID
                session.setAttribute("fullName", rs.getString("full_name")); // Store user name
                session.setAttribute("userRole", rs.getString("user_role")); // Store user role

                // Debugging: Print session details
                System.out.println("DEBUG: Login Successful");
                System.out.println("DEBUG: userId = " + rs.getInt("user_id"));
                System.out.println("DEBUG: fullName = " + rs.getString("full_name"));
                System.out.println("DEBUG: userRole = " + rs.getString("user_role"));

                String role = rs.getString("user_role");
                if ("customer".equals(role)) {
                    response.sendRedirect("customerDashboard.jsp");
                } else if ("driver".equals(role)) {
                    response.sendRedirect("driverDashboard.jsp");
                } else if ("admin".equals(role)) {
                    response.sendRedirect("adminDashboard.jsp");
                }
            } else {
                System.out.println("DEBUG: Login Failed - Invalid credentials");
                response.sendRedirect("login.jsp?msg=invalid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DEBUG: SQL Error - " + e.getMessage());
            response.sendRedirect("login.jsp?msg=error");
        }
    }
}
