<%-- 
    Document   : pageNotFound
    Created on : Oct 1, 2023, 10:11:11 PM
    Author     : ROG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="robots" content="noindex,nofollow" />
<title>Error 404 (Not Found)</title>
<style>body {background: #2b2b2b; margin: 5px; color: #dadada} p {margin: 0; line-height: 1.5; overflow: hidden;} a {text-decoration:none;color:#ea4335}a:hover{color:#cf2415}</style>
</head>
<body>
<p><b>Error 404.</b> <i>Page not found.</i></p>
<p>Can not find wanted link</p>
<p><a href="javascript:history.go(-1);" style="color:#f90; text-decoration: none;">Return to previous page</a> or go to <a href="home"> home page</a> of the web.</p>
</body>
</html>