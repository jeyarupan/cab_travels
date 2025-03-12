package servlets;



import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Dao.AdminDAO;

@WebServlet("/ViewTotalEarningsServlet")
public class ViewTotalEarningsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        double totalEarnings = AdminDAO.getTotalEarnings();
        request.setAttribute("totalEarnings", totalEarnings);
        request.getRequestDispatcher("viewTotalEarnings.jsp").forward(request, response);
    }
}