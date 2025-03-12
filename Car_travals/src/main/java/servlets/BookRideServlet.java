package servlets;
import db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BookRideServlet")
public class BookRideServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("userId");
        
        if (customerId == null) {
            response.sendRedirect("login.jsp?msg=sessionExpired");
            return;
        }
        
        String pickupLocation = request.getParameter("pickupLocation");
        String dropoffLocation = request.getParameter("dropoffLocation");
        double distance = Double.parseDouble(request.getParameter("distance"));
        double fare = Double.parseDouble(request.getParameter("fare"));
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO ride_requests (customer_id, pickup_address, dropoff_address, estimated_distance, estimated_fare, request_status) VALUES (?, ?, ?, ?, ?, 'Pending')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            stmt.setString(2, pickupLocation);
            stmt.setString(3, dropoffLocation);
            stmt.setDouble(4, distance);
            stmt.setDouble(5, fare);
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("customerDashboard.jsp?msg=rideRequested");
            } else {
                response.sendRedirect("bookRide.jsp?msg=error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("bookRide.jsp?msg=error");
        }
    }
}

