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
import Dao.RideRequestDAO;

@WebServlet("/ViewRideStatusServlet")
public class ViewRideStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session

        if (session == null || session.getAttribute("userId") == null) {
            System.out.println("DEBUG: No active session. Redirecting to login.");
            response.sendRedirect("login.jsp?msg=sessionExpired");
            return;
        }

        Integer customerId = (Integer) session.getAttribute("userId");
        System.out.println("DEBUG: View Ride Status - customerId from session: " + customerId);

        List<RideRequestBean> rides = RideRequestDAO.getCustomerRides(customerId);

        // Debugging: Check if we are getting rides
        System.out.println("DEBUG: Number of rides found = " + (rides != null ? rides.size() : "null"));

        if (rides == null || rides.isEmpty()) {
            System.out.println("DEBUG: No rides found for this customer.");
        } else {
            for (RideRequestBean ride : rides) {
                System.out.println("DEBUG: Ride - " + ride.getPickupAddress() + " to " + ride.getDropoffAddress());
            }
        }

        request.setAttribute("rides", rides); // Ensure rides are set as a request attribute
        request.getRequestDispatcher("viewRideStatus.jsp").forward(request, response);
    }
}
