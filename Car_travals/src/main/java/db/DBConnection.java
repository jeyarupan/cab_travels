package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/CarTravalsDB";
    private static final String USER = "root";  // Change if needed
    private static final String PASSWORD = "1234"; // Change if you have a password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Connection Failed: " + e.getMessage());
        }
        return conn;
    }
    
    public static void main(String[] args) {
        // Test connection
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Connection is successful!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}
