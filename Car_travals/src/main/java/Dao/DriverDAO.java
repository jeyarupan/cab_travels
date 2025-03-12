package Dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.RideRequestBean;
import bean.DriverBean;

public class DriverDAO {
    
    // Register driver (Existing function)
    public static boolean registerDriver(String fullName, String email, String phone, String password) {
        boolean isRegistered = false;
        
        String sql = "INSERT INTO app_users (full_name, email, phone, password, user_role) VALUES (?, ?, ?, ?, 'driver')";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password); // TODO: Hash the password before saving
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                isRegistered = true;
            }
        } catch (SQLException e) {
            System.out.println("Error in Driver Registration: " + e.getMessage());
        }
        return isRegistered;
    }

    public static List<RideRequestBean> getAssignedRides(int driverId) {
        List<RideRequestBean> rides = new ArrayList<>();

        // ✅ First, fetch rides assigned to the driver but not yet confirmed
        String sql = "SELECT ra.assignment_id, ra.request_id, r.pickup_address, r.dropoff_address, r.estimated_distance, r.estimated_fare, 'Assigned' AS ride_status " +
                     "FROM ride_assignments ra " +
                     "JOIN ride_requests r ON ra.request_id = r.request_id " +
                     "WHERE ra.driver_id = ? " +
                     "UNION " +  // ✅ Combine with confirmed rides
                     "SELECT cr.ride_id, cr.request_id, cr.pickup_address, cr.dropoff_address, cr.ride_distance, cr.ride_fare, cr.ride_status " +
                     "FROM confirmed_rides cr " +
                     "WHERE cr.driver_id = ? AND cr.ride_status IN ('Confirmed', 'Ongoing')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, driverId);
            stmt.setInt(2, driverId); // For UNION query

            System.out.println("DEBUG: Running SQL query for driver ID = " + driverId);

            ResultSet rs = stmt.executeQuery();
            
            int count = 0;
            while (rs.next()) {
                RideRequestBean ride = new RideRequestBean(
                    rs.getInt("request_id"), // ✅ Using request_id because it may not be confirmed yet
                    driverId,
                    rs.getString("pickup_address"),
                    rs.getString("dropoff_address"),
                    rs.getDouble("estimated_distance"), // Use estimated_distance for unconfirmed rides
                    rs.getDouble("estimated_fare"),
                    rs.getString("ride_status") // Will be 'Assigned', 'Confirmed', or 'Ongoing'
                );
                rides.add(ride);
                count++;
            }

            System.out.println("DEBUG: Number of assigned rides retrieved from DB: " + count);

        } catch (SQLException e) {
            System.out.println("ERROR: Fetching assigned rides failed - " + e.getMessage());
        }
        return rides;
    }
    
    public static List<RideRequestBean> getRideHistory(int driverId) {
        List<RideRequestBean> rideHistory = new ArrayList<>();
        
        String sql = "SELECT ride_id, pickup_address, dropoff_address, ride_distance, ride_fare, ride_status, completion_time " +
                     "FROM confirmed_rides " +
                     "WHERE driver_id = ? AND ride_status = 'Confirmed'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                RideRequestBean ride = new RideRequestBean(
                    rs.getInt("ride_id"),
                    driverId,
                    rs.getString("pickup_address"),
                    rs.getString("dropoff_address"),
                    rs.getDouble("ride_distance"),
                    rs.getDouble("ride_fare"),
                    rs.getString("ride_status"),
                    rs.getTimestamp("completion_time")
                );
                rideHistory.add(ride);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Fetching ride history failed - " + e.getMessage());
        }
        return rideHistory;
    }
    public static List<DriverBean> getAllDrivers() {
        List<DriverBean> drivers = new ArrayList<>();
        
        String sql = "SELECT user_id, full_name, email, phone FROM app_users WHERE user_role = 'driver'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DriverBean driver = new DriverBean();
                driver.setUserId(rs.getInt("user_id"));
                driver.setFullName(rs.getString("full_name"));
                driver.setEmail(rs.getString("email"));
                driver.setPhone(rs.getString("phone"));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Fetching drivers failed - " + e.getMessage());
        }
        return drivers;
    }
    

}
