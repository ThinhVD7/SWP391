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
            <div class="w3-top">
                <div class="w3-bar w3-white w3-card w3-left-align w3-large">
                    <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
                    <a href="#" class="w3-bar-item w3-button w3-padding-large">Home</a>       
                </div>
    
                <!-- Navbar on small screens -->
    
            </div>
    
            <!-- Header -->
            <header class="w3-container w3-blue w3-center " style="padding:128px 16px">
                <h1 class="w3-margin w3-jumbo">QUIZ PRACTICE</h1>
                <p class="w3-xlarge">You haven't signed in</p>
                <form action="Login">
                    <button class="w3-button w3-black w3-padding-large w3-large w3-margin-top">Log In</button>
                </form>
            </header>
            <div class="w3-container w3-black w3-center w3-opacity w3-padding-64">
                <h1 class="w3-margin w3-xlarge">Quote of the day: live life</h1>
            </div>