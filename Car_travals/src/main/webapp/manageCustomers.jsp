<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View All Customers</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>View All Customers</h2>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                <%-- Customer data dynamically populated --%>
                <%@ page import="java.util.List" %>
                <%@ page import="bean.CustomerBean" %>
                <% List<CustomerBean> customers = (List<CustomerBean>) request.getAttribute("customers"); %>
                <% if (customers != null && !customers.isEmpty()) { %>
                    <% for (CustomerBean customer : customers) { %>
                        <tr>
                            <td><%= customer.getUserId() %></td>
                            <td><%= customer.getFullName() %></td>
                            <td><%= customer.getEmail() %></td>
                            <td><%= customer.getPhone() %></td>
                        </tr>
                    <% } %>
                <% } else { %>
                    <tr><td colspan="4">No customers found.</td></tr>
                <% } %>
            </tbody>
        </table>
        
        <p><a href="adminDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>
