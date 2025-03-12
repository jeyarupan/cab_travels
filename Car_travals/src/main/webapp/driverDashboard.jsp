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
    int driverId = (int) sessionObj.getAttribute("userId");
    String fullName = (String) sessionObj.getAttribute("fullName");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Dashboard</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>Welcome, Driver</h2>
        
        <nav>
            <ul>
                <li><a href="addVehicle.jsp">Add Vehicle</a></li>
                <li><a href="ViewAssignedRidesServlet">View Assigned Rides</a></li>
                <li><a href="ViewRideHistoryServlet">View Ride History</a></li>
                <li><a href="LogoutServlet">Logout</a></li>
            </ul>
        </nav>
        
        <div class="dashboard-content">
            <p>Select an option from the menu to manage your rides and vehicles.</p>
        </div>
    </div>
</body>
</html>
