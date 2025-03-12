package servlets;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import bean.RideRequestBean;
import Dao.DriverDAO;

@WebServlet("/ViewAssignedRidesServlet")
public class ViewAssignedRidesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            System.out.println("DEBUG: No active session. Redirecting to login.");
            response.sendRedirect("login.jsp?msg=sessionExpired");
            return;
        }
        
        int driverId = (int) session.getAttribute("userId");
        System.out.println("DEBUG: Driver ID from session = " + driverId);
        
        List<RideRequestBean> assignedRides = DriverDAO.getAssignedRides(driverId);

        // Debugging: Print number of rides retrieved
        System.out.println("DEBUG: Number of assigned rides fetched: " + (assignedRides != null ? assignedRides.size() : "null"));

        if (assignedRides == null || assignedRides.isEmpty()) {
            System.out.println("DEBUG: No assigned rides found for this driver.");
        } else {
            for (RideRequestBean ride : assignedRides) {
                System.out.println("DEBUG: Ride - ID: " + ride.getRequestId() + " | Pickup: " + ride.getPickupAddress() + " | Dropoff: " + ride.getDropoffAddress());
            }
        }

        request.setAttribute("assignedRides", assignedRides);
        request.getRequestDispatcher("viewAssignedRides.jsp").forward(request, response);
    }
}
