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

@WebServlet("/AddVehicleServlet")
public class AddVehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer driverId = (Integer) session.getAttribute("userId");
        
        if (driverId == null) {
            response.sendRedirect("login.jsp?msg=sessionExpired");
            return;
        }
        
        String model = request.getParameter("model");
        String plateNumber = request.getParameter("plateNumber");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String type = request.getParameter("type");
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO driver_vehicles (driver_id, vehicle_model, plate_number, seat_capacity, vehicle_type, availability_status) VALUES (?, ?, ?, ?, ?, true)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, driverId);
            stmt.setString(2, model);
            stmt.setString(3, plateNumber);
            stmt.setInt(4, capacity);
            stmt.setString(5, type);
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("driverDashboard.jsp?msg=vehicleAdded");
            } else {
                response.sendRedirect("addVehicle.jsp?msg=error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("addVehicle.jsp?msg=error");
        }
    }
}

