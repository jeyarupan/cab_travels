<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Total Earnings</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>Total Earnings</h2>
        
        <p><strong>Total Revenue:</strong> $<%= request.getAttribute("totalEarnings") %></p>
        
        <p><a href="adminDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>
