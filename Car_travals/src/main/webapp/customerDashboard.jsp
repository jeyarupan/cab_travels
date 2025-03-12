<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp?msg=sessionExpired");
        return;
    }

    // Retrieve session details
    int userId = (int) sessionObj.getAttribute("userId");
    String fullName = (String) sessionObj.getAttribute("fullName");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= fullName %>!</h2> <!-- Show customer's name -->
        
        <nav>
            <ul>
                <li><a href="bookRide.jsp">Book a Ride</a></li>
                <li><a href="ViewRideStatusServlet">View Ride Status</a></li> <!-- Redirect to servlet -->
                <li><a href="LogoutServlet">Logout</a></li>
            </ul>
        </nav>
        
        <div class="dashboard-content">
            <p>Select an option from the menu to book a ride or check your ride status.</p>
        </div>
    </div>
</body>
</html>

