package Dao;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRideDAO {
    
    public static boolean requestRide(int customerId, String pickupLocation, String dropoffLocation, double distance, double fare) {
        boolean isRequested = false;
        
        String sql = "INSERT INTO ride_requests (customer_id, pickup_address, dropoff_address, estimated_distance, estimated_fare, request_status) VALUES (?, ?, ?, ?, ?, 'Pending')";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, customerId);
            stmt.setString(2, pickupLocation);
            stmt.setString(3, dropoffLocation);
            stmt.setDouble(4, distance);
            stmt.setDouble(5, fare);
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                isRequested = true;
            }
        } catch (SQLException e) {
            System.out.println("Error Requesting Ride: " + e.getMessage());
        }
        return isRequested;
    }
}
