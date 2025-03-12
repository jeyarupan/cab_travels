package Dao;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.RideRequestBean;

public class RideRequestDAO {
    
    public static List<RideRequestBean> getCustomerRides(int customerId) {
        List<RideRequestBean> rides = new ArrayList<>();

        // ✅ Use AS aliasing to match Java Bean field names
        String sql = "SELECT request_id, customer_id, pickup_address AS pickupAddress, dropoff_address AS dropoffAddress, estimated_distance AS estimatedDistance, estimated_fare AS estimatedFare, request_status AS status FROM ride_requests WHERE customer_id = ?";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, customerId);
            System.out.println("DEBUG: Running SQL query with customerId = " + customerId);

            ResultSet rs = stmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                RideRequestBean ride = new RideRequestBean(
                    rs.getInt("request_id"),
                    customerId,
                    rs.getString("pickupAddress"),  // ✅ Updated field name
                    rs.getString("dropoffAddress"), // ✅ Updated field name
                    rs.getDouble("estimatedDistance"), // ✅ Updated field name
                    rs.getDouble("estimatedFare"), // ✅ Updated field name
                    rs.getString("status")
                );
                rides.add(ride);
                count++;
            }

            System.out.println("DEBUG: Number of rides found in database: " + count);

        } catch (SQLException e) {
            System.out.println("ERROR: Fetching ride requests failed - " + e.getMessage());
        }
        return rides;
    }
}
