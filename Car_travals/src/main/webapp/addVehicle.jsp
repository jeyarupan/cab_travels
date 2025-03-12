<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Vehicle</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>Add Your Vehicle</h2>
        <form action="AddVehicleServlet" method="post">
            <label>Vehicle Model:</label>
            <input type="text" name="model" required>
            
            <label>Plate Number:</label>
            <input type="text" name="plateNumber" required>
            
            <label>Seating Capacity:</label>
            <input type="number" name="capacity" required>
            
            <label>Vehicle Type:</label>
            <select name="type" required>
                <option value="Car">Car</option>
                <option value="Van">Van</option>
                <option value="Bike">Bike</option>
            </select>
            
            <button type="submit" class="btn-primary">Add Vehicle</button>
        </form>
        <p><a href="driverDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>