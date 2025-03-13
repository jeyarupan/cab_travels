package Dao;
 import db.DBConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    
    public static boolean registerCustomer(String fullName, String email, String phone, String password) {
        boolean isRegistered = false;
        
        String sql = "INSERT INTO app_users (full_name, email, phone, password, user_role) VALUES (?, ?, ?, ?, 'customer')";
        
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
            System.out.println("Error in Customer Registration: " + e.getMessage());
        }
        return isRegistered;
    }
    public static boolean validateUser(String email, String password, String role) {
        boolean isValid = false;

        String sql = "SELECT * FROM app_users WHERE email = ? AND password = ? AND user_role = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, role);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                isValid = true; // User exists with valid credentials
            }

        } catch (Exception e) {
            System.out.println("Error validating user: " + e.getMessage());
        }

        return isValid;
    }
}