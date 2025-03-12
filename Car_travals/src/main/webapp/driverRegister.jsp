<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Registration</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>

<div class="container">
    <h2>Driver Registration</h2>
    <form action="DriverRegisterServlet" method="post" style="width:30%;margin:auto">
        <label>Full Name:</label>
        <input type="text" name="fullName" required>
        
        <label>Email:</label>
        <input type="email" name="email" required>
        
        <label>Phone:</label>
        <input type="text" name="phone" required>
        
        <label>Password:</label>
        <input type="password" name="password" required>
        
        <button type="submit" class="btn-primary">Register</button>
    </form>

    <p>Already
