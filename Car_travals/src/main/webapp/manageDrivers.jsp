<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View All Drivers</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>View All Drivers</h2>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Driver ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                <%-- Driver data dynamically populated --%>
                <%@ page import="java.util.List" %>
                <%@ page import="bean.DriverBean" %>
                <% List<DriverBean> drivers = (List<DriverBean>) request.getAttribute("drivers"); %>
                <% if (drivers != null && !drivers.isEmpty()) { %>
                    <% for (DriverBean driver : drivers) { %>
                        <tr>
                            <td><%= driver.getUserId() %></td>
                            <td><%= driver.getFullName() %></td>
                            <td><%= driver.getEmail() %></td>
                            <td><%= driver.getPhone() %></td>
                        </tr>
                    <% } %>
                <% } else { %>
                    <tr><td colspan="4">No drivers found.</td></tr>
                <% } %>
            </tbody>
        </table>
        
        <p><a href="adminDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>