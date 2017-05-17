<%-- 
    Document   : register
    Created on : May 12, 2017, 12:38:38 PM
    Author     : nich
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register Page</h1>
        <p>${message}</p>
        <form action="index" method="post" >
            <input type="email" name="email" required>
            <input type="text" name="firstName"required >
            <input type="text" name="lastName" required >
            <input type="submit" name="action" value="Register">
            <input type="submit" name="action" value="Signup">
        </form>
        
    </body>
</html>
