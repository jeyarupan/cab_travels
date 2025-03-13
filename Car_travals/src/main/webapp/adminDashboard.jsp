<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
    <style>
    /* Global Styles */
/* Global Styles */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}

body {
    background-color: #F4F8E8;
    color: #444444;
    line-height: 1.6;
}

/* Navigation Bar */
.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 5%;
    background-color: #F4F8E8;
    border-bottom: 2px solid #A4D037;
}

.navbar a {
    color: #000000;
    text-decoration: none;
    font-weight: 600;
    margin: 0 15px;
    transition: color 0.3s;
}

.navbar a:hover {
    color: #A4D037;
}

/* Dashboard Layout */
.container {
    width: 80%;
    margin: 30px auto;
    background: #ffffff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    text-align: center;
}

.container h2 {
    font-size: 2rem;
    color: #000000;
    margin-bottom: 15px;
}

.dashboard-content {
    margin-top: 20px;
}

nav ul {
    list-style: none;
    padding: 0;
    display: flex;
    justify-content: center;
    gap: 15px;
}

nav ul li {
    display: inline;
}

nav ul li a {
    text-decoration: none;
    padding: 10px 20px;
    background: #A4D037;
    color: #000000;
    border-radius: 5px;
    transition: background 0.3s;
    font-weight: bold;
}

nav ul li a:hover {
    background: #8BB72C;
}

/* Table View */
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    background: #ffffff;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

table th, table td {
    padding: 12px;
    border: 1px solid #A4D037;
    text-align: center;
}

table th {
    background: #A4D037;
    color: #000000;
}

table tr:nth-child(even) {
    background: #F4F8E8;
}

/* Buttons */
.btn-primary {
    display: inline-block;
    padding: 12px 25px;
    background-color: #A4D037;
    color: #000000;
    font-weight: bold;
    text-decoration: none;
    border-radius: 5px;
    transition: background 0.3s;
}

.btn-primary:hover {
    background-color: #8BB72C;
}

/* Form Styling */
form {
    background: #ffffff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    width: 50%;
    margin: 20px auto;
}

input, select {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #A4D037;
    border-radius: 5px;
}

/* Footer */
.footer {
    text-align: center;
    padding: 20px;
    background: #A4D037;
    color: #000000;
}

    
    </style>
</head>
<body style="background-image: url('img_girl.jpg');">
    <div class="container">
        <h2>Welcome, Admin</h2>
        
        <nav>
            <ul>
                <li><a href="AssignRideServlet">Assign Ride to Driver</a></li>
                <li><a href="ManageCustomersServlet">Manage Customers</a></li>
                <li><a href="ManageDriversServlet">Manage Drivers</a></li>
                <li><a href="ViewTotalEarningsServlet">View Total Earnings</a></li>
                <li><a href="ViewAllRideHistoryServlet">View All Ride History</a></li>
                <li><a href="LogoutServlet">Logout</a></li>
            </ul>
        </nav>
        
        <div class="dashboard-content">
            <p>Select an option from the menu to manage ride requests, customers, drivers, and earnings.</p>
        </div>
    </div>
</body>
</html>
