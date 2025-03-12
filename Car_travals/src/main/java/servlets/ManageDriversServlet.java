package servlets;



import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bean.DriverBean;
import Dao.DriverDAO;

@WebServlet("/ManageDriversServlet")
public class ManageDriversServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<DriverBean> drivers = DriverDAO.getAllDrivers();
        request.setAttribute("drivers", drivers);
        request.getRequestDispatcher("manageDrivers.jsp").forward(request, response);
    }
}
