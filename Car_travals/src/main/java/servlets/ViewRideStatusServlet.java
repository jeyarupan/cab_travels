package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.RideRequest;

@WebServlet("/ViewRideStatusServlet")
public class ViewRideStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("userId");
        
        if (customerId == null) {
            response.sendRedirect("login.jsp?msg=sessionExpired");
            return;
        }
        
        List<RideRequest> rides = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT pickup_address, dropoff_address, estimated_distance, estimated_fare, request_status FROM ride_requests WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                RideRequest ride = new RideRequest(
                    rs.getString("pickup_address"),
                    rs.getString("dropoff_address"),
                    rs.getDouble("estimated_distance"),
                    rs.getDouble("estimated_fare"),
                    rs.getString("request_status")
                );
                rides.add(ride);
            }
            
            request.setAttribute("rides", rides);
            request.getRequestDispatcher("viewRideStatus.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("customerDashboard.jsp?msg=error");
        }
    }
}
