
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="Css/style.css">
</head>
<body>

<div class="container" style="width:35% ;Margin:auto; margin-top:100px">
    <h2 style="text-align:center">Login</h2>
    <form action="LoginServlet" method="post">
        <label>Email:</label>
        <input type="email" name="email" required>
        
        <label>Password:</label>
        <input type="password" name="password" required>
        
        <button type="submit" class="btn-primary">Login</button>
    </form>
    
    <p>Don't have an account? <a href="customerRegister.jsp">Register as Customer</a> | <a href="driverRegister.jsp">Register as Driver</a></p>
</div>

</body>
</html>
