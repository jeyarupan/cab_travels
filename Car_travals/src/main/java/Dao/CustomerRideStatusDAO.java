package Dao;

import bean.RideRequestBean;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRideStatusDAO {
    
    public static List<RideRequestBean> getCustomerRides(int customerId) {
        List<RideRequestBean> rides = new ArrayList<>();
        
        String sql = "SELECT request_id, pickup_address, dropoff_address, estimated_distance, estimated_fare, request_status FROM ride_requests WHERE customer_id = ?";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                RideRequestBean ride = new RideRequestBean(
                    rs.getInt("request_id"),
                    customerId,
                    rs.getString("pickup_address"),
                    rs.getString("dropoff_address"),
                    rs.getDouble("estimated_distance"),
                    rs.getDouble("estimated_fare"),
                    rs.getString("request_status")
                );
                rides.add(ride);
            }
        } catch (SQLException e) {
            System.out.println("Error Fetching Ride Requests: " + e.getMessage());
        }
        return rides;
    }
}
