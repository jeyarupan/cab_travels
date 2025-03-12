<!DOCTYPE html>
<html>
<head>
    <title>Assign Ride</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>Assign Ride to Driver</h2>
        
        <form action="AssignRideServlet" method="post">
            <label for="rideId">Select Ride:</label>
            <select name="rideId" id="rideId" required>
                <%-- Rides will be dynamically populated --%>
                <%@ page import="java.util.List" %>
                <%@ page import="bean.RideRequestBean" %>
                <% List<RideRequestBean> rides = (List<RideRequestBean>) request.getAttribute("unassignedRides"); %>
                <% if (rides != null && !rides.isEmpty()) { %>
                    <% for (RideRequestBean ride : rides) { %>
                        <option value="<%= ride.getRequestId() %>">
                            Ride ID: <%= ride.getRequestId() %> | Pickup: <%= ride.getPickupAddress() %> | Dropoff: <%= ride.getDropoffAddress() %>
                        </option>
                    <% } %>
                <% } else { %>
                    <option value="">No unassigned rides available</option>
                <% } %>
            </select>
            
            <label for="driverId">Select Driver:</label>
            <select name="driverId" id="driverId" required>
                <%-- Drivers will be dynamically populated --%>
                <%@ page import="bean.DriverBean" %>
                <% List<DriverBean> drivers = (List<DriverBean>) request.getAttribute("availableDrivers"); %>
                <% if (drivers != null && !drivers.isEmpty()) { %>
                    <% for (DriverBean driver : drivers) { %>
                        <option value="<%= driver.getUserId() %>">
                            Driver: <%= driver.getFullName() %> | Vehicle: <%= driver.getVehicleModel() %> | Plate: <%= driver.getPlateNumber() %>
                        </option>
                    <% } %>
                <% } else { %>
                    <option value="">No available drivers</option>
                <% } %>
            </select>
            
            <button type="submit" class="btn-primary">Assign Ride</button>
        </form>
        
        <p><a href="adminDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>