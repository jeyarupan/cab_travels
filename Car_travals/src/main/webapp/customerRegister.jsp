<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Registration</title>
    <link rel="stylesheet" href="Css/style.css"> <!-- Global CSS -->
</head>
<body>
    <div class="container" style="padding-top:100px">
        <h2 style="text-align:center">Customer Registration</h2>
        <form action="CustomerRegisterServlet" method="post" style="width:30%;margin:auto">
            <label for="fullName">Full Name:</label>
            <input type="text" name="fullName" required>

            <label for="email">Email:</label>
            <input type="email" name="email" required>

            <label for="phone">Phone:</label>
            <input type="text" name="phone" required>

            <label for="password">Password:</label>
            <input type="password" name="password" required>

            <button type="submit" class="btn-primary">Register</button>
        </form>
    </div>
  
</body>
</html>
