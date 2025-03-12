<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View All Rides</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>All Rides</h2>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Ride ID</th>
                    <th>Customer ID</th>
                    <th>Driver ID</th>
                    <th>Pickup Location</th>
                    <th>Drop-off Location</th>
                    <th>Distance (km)</th>
                    <th>Fare ($)</th>
                    <th>Status</th>
                    <th>Completion Time</th>
                </tr>
            </thead>
            <tbody>
                <%-- Ride data dynamically populated --%>
                <%@ page import="java.util.List" %>
                <%@ page import="bean.RideRequestBean" %>
                <% List<RideRequestBean> rides = (List<RideRequestBean>) request.getAttribute("rideHistory"); %>
                <% if (rides != null && !rides.isEmpty()) { %>
                    <% for (RideRequestBean ride : rides) { %>
                        <tr>
                            <td><%= ride.getRequestId() %></td>
                            <td><%= ride.getCustomerId() %></td>
                         
                            <td><%= ride.getPickupAddress() %></td>
                            <td><%= ride.getDropoffAddress() %></td>
                            <td><%= ride.getEstimatedDistance() %></td>
                            <td><%= ride.getEstimatedFare() %></td>
                            <td><%= ride.getStatus() %></td>
                            <td><%= ride.getCompletionTime() != null ? ride.getCompletionTime() : "N/A" %></td>
                        </tr>
                    <% } %>
                <% } else { %>
                    <tr><td colspan="9">No rides found.</td></tr>
                <% } %>
            </tbody>
        </table>
        
        <p><a href="adminDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>
