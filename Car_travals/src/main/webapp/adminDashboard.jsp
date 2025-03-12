<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>
    <div class="container">
        <h2>Welcome, Admin</h2>
        
        <nav>
            <ul>
                <li><a href="AssignRideServlet">Assign Ride to Driver</a></li>
                <li><a href="manageCustomers.jsp">Manage Customers</a></li>
                <li><a href="manageDrivers.jsp">Manage Drivers</a></li>
                <li><a href="viewTotalEarnings.jsp">View Total Earnings</a></li>
                <li><a href="viewRideHistory.jsp">View All Ride History</a></li>
                <li><a href="LogoutServlet">Logout</a></li>
            </ul>
        </nav>
        
        <div class="dashboard-content">
            <p>Select an option from the menu to manage ride requests, customers, drivers, and earnings.</p>
        </div>
    </div>
</body>
</html>
