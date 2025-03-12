<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Assigned Rides</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>Your Assigned Rides</h2>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Pickup Location</th>
                    <th>Drop-off Location</th>
                    <th>Distance (km)</th>
                    <th>Fare ($)</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%-- Rides will be dynamically populated --%>
                <%@ page import="java.util.List" %>
                <%@ page import="bean.RideRequestBean" %>
                <% List<RideRequestBean> assignedRides = (List<RideRequestBean>) request.getAttribute("assignedRides"); %>
                <% if (assignedRides != null && !assignedRides.isEmpty()) { %>
                    <% for (RideRequestBean ride : assignedRides) { %>
                        <tr>
                            <td><%= ride.getPickupAddress() %></td>
                            <td><%= ride.getDropoffAddress() %></td>
                            <td><%= ride.getEstimatedDistance() %></td>
<td><%= ride.getEstimatedFare() %></td>

                            <td><%= ride.getStatus() %></td>
                          <td>
    <% if ("Assigned".equals(ride.getStatus())) { %>
        <form action="UpdateRideStatusServlet" method="post">
            <input type="hidden" name="rideId" value="<%= ride.getRequestId() %>">
            <input type="hidden" name="status" value="Confirmed">
            <button type="submit" class="btn-primary">Confirm Ride</button>
        </form>
    <% } else if ("Confirmed".equals(ride.getStatus())) { %>
        <form action="UpdateRideStatusServlet" method="post">
            <input type="hidden" name="rideId" value="<%= ride.getRequestId() %>">
            <input type="hidden" name="status" value="Ongoing">
            <button type="submit" class="btn-primary">Start Ride</button>
        </form>
    <% } else if ("Ongoing".equals(ride.getStatus())) { %>
        <form action="UpdateRideStatusServlet" method="post">
            <input type="hidden" name="rideId" value="<%= ride.getRequestId() %>">
            <input type="hidden" name="status" value="Completed">
            <button type="submit" class="btn-success">Complete Ride</button>
        </form>
    <% } else { %>
        <span>N/A</span>
    <% } %>
</td>

                        </tr>
                    <% } %>
                <% } else { %>
                    <tr><td colspan="6">No assigned rides available</td></tr>
                <% } %>
            </tbody>
        </table>
        
        <p><a href="driverDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>
