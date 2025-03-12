<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="bean.RideRequestBean" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Ride Status</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>Your Ride Status</h2>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Pickup Location</th>
                    <th>Drop-off Location</th>
                    <th>Distance (km)</th>
                    <th>Fare ($)</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%-- Rides will be populated here dynamically --%>
                <%@ page import="java.util.List" %>
                <%@ page import="bean.RideRequestBean" %>
                <%  List<RideRequestBean> rides = (List<RideRequestBean>) request.getAttribute("rides");  %>
               <%  System.out.println("DEBUG: Number of rides passed to JSP: " + (rides != null ? rides.size() : "null"));%>
                <% if (rides != null && !rides.isEmpty()) { %>
                    <% for (RideRequestBean ride : rides) { %>
                        <tr>
                            <td><%= ride.getPickupAddress() %></td>
                            <td><%= ride.getDropoffAddress() %></td>
                            <td><%= ride.getEstimatedDistance() %></td>
                            <td><%= ride.getEstimatedFare() %></td>
                            <td><%= ride.getStatus() %></td>
                        </tr>
                    <% } %>
                <% } else { %>
                    <tr><td colspan="5">No ride requests found.</td></tr>
                <% } %>
            </tbody>
        </table>
        
        <p><a href="customerDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>