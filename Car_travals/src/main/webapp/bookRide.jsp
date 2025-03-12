<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Book a Ride</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Book a Ride</h2>
        <form action="BookRideServlet" method="post">
            <label>Pickup Location:</label>
            <input type="text" name="pickupLocation" required>
            
            <label>Drop-off Location:</label>
            <input type="text" name="dropoffLocation" required>
            
            <label>Estimated Distance (km):</label>
            <input type="number" name="distance" step="0.1" required>
            
            <label>Estimated Fare ($):</label>
            <input type="number" name="fare" step="0.1" required>
            
            <button type="submit" class="btn-primary">Request Ride</button>
        </form>
        <p><a href="customerDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>