package servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Dao.CustomerDAO;

@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Insert into database using CustomerDAO
        CustomerDAO customerDAO = new CustomerDAO();
        boolean isRegistered = customerDAO.registerCustomer(fullName, email, phone, password);
        
        if (isRegistered) {
            response.sendRedirect("success.jsp"); // Redirect to success page
        } else {
            response.sendRedirect("error.jsp"); // Redirect to error page
        }
    }
}
