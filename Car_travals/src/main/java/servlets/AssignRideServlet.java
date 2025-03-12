package servlets;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import db.DBConnection;
import Dao.DriverDAO;

@WebServlet("/AssignRideServlet")
public class AssignRideServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Fetch unassigned rides & available drivers before displaying assignRide.jsp
        request.setAttribute("unassignedRides", Dao.AdminDAO.getUnassignedRides());
        request.setAttribute("availableDrivers", Dao.AdminDAO.getAvailableDrivers());
        request.getRequestDispatcher("assignRide.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve ride and driver IDs from the form submission
        int rideId = Integer.parseInt(request.getParameter("rideId"));
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        try (Connection conn = DBConnection.getConnection()) {
            // Insert into ride_assignments table
            String sql = "INSERT INTO ride_assignments (admin_id, request_id, driver_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1); // Assuming Admin ID is 1 (Replace with session-based admin ID if needed)
            stmt.setInt(2, rideId);
            stmt.setInt(3, driverId);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                // Update ride_requests status to "Assigned"
                String updateSql = "UPDATE ride_requests SET request_status = 'Assigned' WHERE request_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, rideId);
                updateStmt.executeUpdate();

                response.sendRedirect("adminDashboard.jsp?msg=rideAssigned");
            } else {
                response.sendRedirect("assignRide.jsp?msg=error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("assignRide.jsp?msg=error");
        }
    }
}
