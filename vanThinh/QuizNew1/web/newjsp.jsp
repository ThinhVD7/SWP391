<%-- 
    Document   : newjsp
    Created on : Sep 27, 2023, 11:25:58 PM
    Author     : msi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <c:set var="num1" value="${sessionScope.roleId}"/>
        <h1>đây là sô ${sessionScope.otp}</h1>
        <h1>Hello World!</h1>
    </body>
</html>
