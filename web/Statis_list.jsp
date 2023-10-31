<%-- 
    Document   : lectuer-homepage
    Created on : Sep 27, 2023, 2:49:56 AM
    Author     : tanki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Side Navbar - Tivotal</title>
        <%@include file="all_component/allCss.jsp" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="all_component/studentStyle.css" />

        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />
            <li>
                <i class="bx bx-search search-btn"></i>
                <input type="text" placeholder="Search" />
                <span class="tooltip">Search</span>
            </li>
            <li>
                <a href="#">
                    <i class='bx bxs-user-account'></i>
                    <span class="title">Profile</span>
                </a>
                <span class="tooltip">${user.name}</span>
            </li>
            <li>
                <a href="#">
                    <i class="bx bx-home-alt-2"></i>
                    <span class="title">Home</span>
                </a>
                <span class="tooltip">Home</span>
            </li>