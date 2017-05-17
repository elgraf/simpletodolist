<%-- 
    Document   : index
    Created on : May 9, 2017, 4:40:15 PM
    Author     : nich
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TODO LIST</title>
        
    </head>
    <body>
        <h1>To Do list present:</h1>
        <c:if test="${todo == null}">
            <p>Nu este TODO ACUM</p>
        </c:if>
        <c:if test="${todo != null}">
            <c:forEach var="item" items="${todo.items}">
                ${item.title} || ${item.date}
                <form action="index" method="post">
                    <input type="hidden" name="date" value="${item.rawDate}">
                    <input type="submit" name="action" value="remove">
                    
                </form>
                <form action="index" method="post">
                    <input type="hidden" name="date" value="${item.rawDate}">
                    <input type="submit" name="action" value="details">
                </form>
                
            </c:forEach>
        </c:if>  
    
    
    
            <form action="index" method="post">
                <input type="hidden" name="action" value="add">
                <input type="text" name="text">
                <input type="submit" value="Add To DO">
                
            </form>
    
    </body>
</html>
