package Dao;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverVehicleDAO {
    
    public static boolean addVehicle(int driverId, String model, String plateNumber, int capacity, String type) {
        boolean isAdded = false;
        
        String sql = "INSERT INTO driver_vehicles (driver_id, vehicle_model, plate_number, seat_capacity, vehicle_type, availability_status) VALUES (?, ?, ?, ?, ?, true)";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, driverId);
            stmt.setString(2, model);
            stmt.setString(3, plateNumber);
            stmt.setInt(4, capacity);
            stmt.setString(5, type);
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                isAdded = true;
            }
        } catch (SQLException e) {
            System.out.println("Error Adding Vehicle: " + e.getMessage());
        }
        return isAdded;
    }
}

