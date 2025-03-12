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

@WebServlet("/ViewRideHistoryServlet")
public class ViewRideHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp?msg=sessionExpired");
            return;
        }
        
        int driverId = (int) session.getAttribute("userId");
        List<RideRequestBean> rideHistory = DriverDAO.getRideHistory(driverId);
        
        request.setAttribute("rideHistory", rideHistory);
        request.getRequestDispatcher("viewRideHistory.jsp").forward(request, response);
    }
}