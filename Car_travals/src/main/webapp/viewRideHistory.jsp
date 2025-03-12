<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ride History</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>Your Ride History</h2>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Pickup Location</th>
                    <th>Drop-off Location</th>
                    <th>Distance (km)</th>
                    <th>Fare ($)</th>
                    <th>Status</th>
                    <th>Completion Time</th>
                </tr>
            </thead>
            <tbody>
                <%-- Rides will be dynamically populated --%>
                <%@ page import="java.util.List" %>
                <%@ page import="bean.RideRequestBean" %>
                <% List<RideRequestBean> rideHistory = (List<RideRequestBean>) request.getAttribute("rideHistory"); %>
                <% if (rideHistory != null && !rideHistory.isEmpty()) { %>
                    <% for (RideRequestBean ride : rideHistory) { %>
                        <tr>
                            <td><%= ride.getPickupAddress() %></td>
                            <td><%= ride.getDropoffAddress() %></td>
                            <td><%= ride.getEstimatedDistance() %></td>
                            <td><%= ride.getEstimatedFare() %></td>
                            <td><%= ride.getStatus() %></td>
                            <td><%= ride.getCompletionTime() != null ? ride.getCompletionTime() : "N/A" %></td>
                        </tr>
                    <% } %>
                <% } else { %>
                    <tr><td colspan="6">No ride history available</td></tr>
                <% } %>
            </tbody>
        </table>
        
        <p><a href="driverDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>