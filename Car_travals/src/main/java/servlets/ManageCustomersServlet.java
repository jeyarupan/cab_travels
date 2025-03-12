package servlets;



import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bean.CustomerBean;
import Dao.AdminDAO;

@WebServlet("/ManageCustomersServlet")
public class ManageCustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<CustomerBean> customers = AdminDAO.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("manageCustomers.jsp").forward(request, response);
    }
}