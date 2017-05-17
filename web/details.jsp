<%-- 
    Document   : details.jsp
    Created on : May 10, 2017, 9:14:40 PM
    Author     : nich
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail page!</title>
    </head>
    <body>
        <h1>Details about your todo...</h1>
        <c:if test="${item != null}">
            ${item.title}
        </c:if>
    </body>
</html>
