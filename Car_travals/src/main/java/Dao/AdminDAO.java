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
import bean.CustomerBean;

public class AdminDAO {
    
    // Fetch unassigned ride requests
	public static List<RideRequestBean> getUnassignedRides() {
	    List<RideRequestBean> rides = new ArrayList<>();
	    
	    String sql = "SELECT r.request_id, r.customer_id, r.pickup_address, r.dropoff_address, r.estimated_distance, r.estimated_fare " +
	                 "FROM ride_requests r " +
	                 "LEFT JOIN ride_assignments ra ON r.request_id = ra.request_id " +
	                 "WHERE r.request_status = 'Pending' AND ra.request_id IS NULL";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            RideRequestBean ride = new RideRequestBean(
	                rs.getInt("request_id"),
	                rs.getInt("customer_id"),
	                rs.getString("pickup_address"),
	                rs.getString("dropoff_address"),
	                rs.getDouble("estimated_distance"),
	                rs.getDouble("estimated_fare"),
	                "Pending"
	            );
	            rides.add(ride);
	        }
	    } catch (SQLException e) {
	        System.out.println("ERROR: Fetching unassigned rides failed - " + e.getMessage());
	    }
	    return rides;
	}

    
    // Fetch available drivers
	public static List<DriverBean> getAvailableDrivers() {
	    List<DriverBean> drivers = new ArrayList<>();
	    
	    String sql = "SELECT u.user_id, u.full_name, v.vehicle_id, v.vehicle_model, v.plate_number " +
	                 "FROM app_users u " +
	                 "JOIN driver_vehicles v ON u.user_id = v.driver_id " +
	                 "LEFT JOIN confirmed_rides cr ON u.user_id = cr.driver_id AND cr.ride_status IN ('Confirmed', 'Ongoing') " +
	                 "WHERE u.user_role = 'driver' AND cr.driver_id IS NULL";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            DriverBean driver = new DriverBean();
	            driver.setUserId(rs.getInt("user_id"));
	            driver.setFullName(rs.getString("full_name"));
	            driver.setVehicleId(rs.getInt("vehicle_id"));  // Assuming you have this in DriverBean
	            driver.setVehicleModel(rs.getString("vehicle_model"));
	            driver.setPlateNumber(rs.getString("plate_number"));
	            drivers.add(driver);
	        }
	    } catch (SQLException e) {
	        System.out.println("ERROR: Fetching available drivers failed - " + e.getMessage());
	    }
	    return drivers;
	}
	public static List<RideRequestBean> getAssignedRides(int driverId) {
        List<RideRequestBean> rides = new ArrayList<>();
        
        String sql = "SELECT cr.ride_id, cr.request_id, cr.pickup_address, cr.dropoff_address, cr.ride_distance, cr.ride_fare, cr.ride_status " +
                     "FROM confirmed_rides cr " +
                     "WHERE cr.driver_id = ? AND cr.ride_status IN ('Confirmed', 'Ongoing')";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                RideRequestBean ride = new RideRequestBean(
                    rs.getInt("ride_id"),driverId,
                    rs.getString("pickup_address"),
                    rs.getString("dropoff_address"),
                    rs.getDouble("ride_distance"),
                    rs.getDouble("ride_fare"),
                    rs.getString("ride_status")
                );
                rides.add(ride);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Fetching assigned rides failed - " + e.getMessage());
        }
        return rides;
    }
	
	public static List<CustomerBean> getAllCustomers() {
        List<CustomerBean> customers = new ArrayList<>();
        
        String sql = "SELECT user_id, full_name, email, phone FROM app_users WHERE user_role = 'customer'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                CustomerBean customer = new CustomerBean();
                customer.setUserId(rs.getInt("user_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Fetching customers failed - " + e.getMessage());
        }
        return customers;
    }
	public static double getTotalEarnings() {
        double totalEarnings = 0.0;
        String sql = "SELECT SUM(ride_fare) AS total FROM confirmed_rides WHERE ride_status = 'Confirmed'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalEarnings = rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Fetching total earnings failed - " + e.getMessage());
        }
        return totalEarnings;
    }
	public static List<RideRequestBean> getAllRides() {
        List<RideRequestBean> rides = new ArrayList<>();
        
        String sql = "SELECT cr.ride_id, r.customer_id, cr.driver_id, cr.pickup_address, cr.dropoff_address, cr.ride_distance, cr.ride_fare, cr.ride_status, cr.completion_time " +
                     "FROM confirmed_rides cr " +
                     "JOIN ride_requests r ON cr.request_id = r.request_id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                RideRequestBean ride = new RideRequestBean(
                    rs.getInt("ride_id"),
                    rs.getInt("customer_id"),
                    rs.getString("pickup_address"),
                    rs.getString("dropoff_address"),
                    rs.getDouble("ride_distance"),
                    rs.getDouble("ride_fare"),
                    rs.getString("ride_status"),
                    rs.getTimestamp("completion_time"),
                    rs.getInt("driver_id")
                );
                rides.add(ride);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Fetching ride history failed - " + e.getMessage());
        }
        return rides;
    }
}
