package servlets;

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
import db.DBConnection;

@WebServlet("/UpdateRideStatusServlet")
public class UpdateRideStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int requestId = Integer.parseInt(request.getParameter("rideId")); // This is the request_id, not ride_id
        String status = request.getParameter("status");

        System.out.println("DEBUG: Updating ride request ID " + requestId + " to status " + status);

        try (Connection conn = DBConnection.getConnection()) {
            // ✅ Step 1: If status is "Confirmed", move ride from ride_assignments → confirmed_rides
            if ("Confirmed".equals(status)) {
                String checkSql = "SELECT driver_id FROM ride_assignments WHERE request_id = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setInt(1, requestId);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    int driverId = rs.getInt("driver_id");

                    // ✅ Insert into confirmed_rides
                    String insertSql = "INSERT INTO confirmed_rides (request_id, driver_id, vehicle_id, pickup_address, dropoff_address, ride_distance, ride_fare, ride_status) " +
                                       "SELECT r.request_id, ra.driver_id, dv.vehicle_id, r.pickup_address, r.dropoff_address, r.estimated_distance, r.estimated_fare, 'Confirmed' " +
                                       "FROM ride_requests r " +
                                       "JOIN ride_assignments ra ON r.request_id = ra.request_id " +
                                       "JOIN driver_vehicles dv ON ra.driver_id = dv.driver_id " +
                                       "WHERE r.request_id = ?";
                    PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                    insertStmt.setInt(1, requestId);
                    int rowsInserted = insertStmt.executeUpdate();
                    System.out.println("DEBUG: Ride moved to confirmed_rides: " + rowsInserted);

                    if (rowsInserted > 0) {
                        // ✅ Delete from ride_assignments
                        String deleteSql = "DELETE FROM ride_assignments WHERE request_id = ?";
                        PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                        deleteStmt.setInt(1, requestId);
                        deleteStmt.executeUpdate();
                    }
                }
            }

            // ✅ Step 2: Update ride_status in confirmed_rides
            String updateSql = "UPDATE confirmed_rides SET ride_status = ?, completion_time = CASE WHEN ? = 'Completed' THEN NOW() ELSE completion_time END WHERE request_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, status);
            updateStmt.setString(2, status);
            updateStmt.setInt(3, requestId);
            
            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println("DEBUG: Rows updated in confirmed_rides = " + rowsUpdated);

            if (rowsUpdated > 0) {
                response.sendRedirect("ViewAssignedRidesServlet?msg=statusUpdated");
            } else {
                System.out.println("ERROR: No rows updated in confirmed_rides.");
                response.sendRedirect("viewAssignedRides.jsp?msg=error");
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("viewAssignedRides.jsp?msg=error");
        }
    }
}
