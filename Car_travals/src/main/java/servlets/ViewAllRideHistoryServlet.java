package servlets;



import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bean.RideRequestBean;
import Dao.AdminDAO;

@WebServlet("/ViewAllRideHistoryServlet")
public class ViewAllRideHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<RideRequestBean> rideHistory = AdminDAO.getAllRides();
        request.setAttribute("rideHistory", rideHistory);
        request.getRequestDispatcher("viewAllRide.jsp").forward(request, response);
    }
}