package servlets;

import db.DBConnection;
import Dao.DriverDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DriverRegisterServlet")
public class DriverRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Call the DAO to register the driver
        boolean success = DriverDAO.registerDriver(fullName, email, phone, password);

        if (success) {
            response.sendRedirect("login.jsp?msg=success");
        } else {
            response.sendRedirect("driverRegister.jsp?msg=error");
        }
    }
}

