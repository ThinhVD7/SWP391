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
            <footer class="w3-container w3-padding-64 w3-center w3-opacity">  
                <div class="w3-xlarge w3-padding-32">
                    <i class="fa fa-facebook-official w3-hover-opacity"></i>
                    <i class="fa fa-instagram w3-hover-opacity"></i>
                    <i class="fa fa-snapchat w3-hover-opacity"></i>
                    <i class="fa fa-pinterest-p w3-hover-opacity"></i>
                    <i class="fa fa-twitter w3-hover-opacity"></i>
                    <i class="fa fa-linkedin w3-hover-opacity"></i>
                </div>
                <p>Sign in  <a href="Login">here</a></p>
            </footer>
    
            <script>
                // Used to toggle the menu on small screens when clicking on the menu button
                function myFunction() {
                    var x = document.getElementById("navDemo");
                    if (x.className.indexOf("w3-show") == -1) {
                        x.className += " w3-show";
                    } else {
                        x.className = x.className.replace(" w3-show", "");
                    }
                }
            
    /* Track */
    ::-webkit-scrollbar-track {
      background: #f1f1f1; 
    }

    /* Handle */
    ::-webkit-scrollbar-thumb {
      background: #888; 
    }

    /* Handle on hover */
    ::-webkit-scrollbar-thumb:hover {
      background: #555; 
    } /* Importing fonts from Google */
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

/* Reseting */
* 
            </script>
    
        </body>
    </html>
    <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        <!-- PAGE TITLE HERE ============================================= -->
        <title>Forget Password </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">

    </head>
    <body id="bg">
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
        
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(https://i.ibb.co/jTh7KHj/web-design-HN-min.png)">
                    <a href="home"><img src="assets/images/icon.png" alt="" width="150px" height="150px"></a>
                </div>
                <div class="account-form-inner">
                    <div class="account-container">
                        <div class="heading-bx left">
                            <h2 class="title-head">Change <span>Password</span></h2>
                            <p class="text-danger">${msg}</p>
                            <p class="text-success">${fmsg}</p>


                        </div>	
                        <form method="post" id="passwordForm" action="ChangePassword">
                            <input type="password" class="input-lg form-control" name="password0" id="password0" placeholder="Old Password" autocomplete="off" required="">

                            <br>
                            <input type="password" class="input-lg form-control" name="password1" id="password1" placeholder="New Password" autocomplete="off" required="">
                            <div class="row">
                                <div class="col-sm-6">
                                    <span id="8char" class="glyphicon glyphicon-remove" style="color:#FF0004;font-size: 10px">8 Characters Long</span> <br>
                                    <span id="ucase" class="glyphicon glyphicon-remove" style="color:#FF0004;font-size: 10px">One Uppercase Letter</span> 
                                </div>
                                <div class="col-sm-6">
                                    <span id="lcase" class="glyphicon glyphicon-remove" style="color:#FF0004;font-size: 10px">One Lowercase Letter</span> <br>
                                    <span id="num" class="glyphicon glyphicon-remove" style="color:#FF0004;font-size: 10px">One Number</span> 
                                </div>
                            </div>
                            <input type="password" class="input-lg form-control" name="password2" id="password2" placeholder="Repeat Password" autocomplete="off" required="">
                            <div class="row">
                                <div class="col-sm-12">
                                    <span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;font-size: 10px">Passwords Match</span> 
                                </div>
                            </div>
                            <input type="submit" class="col-xs-12 btn btn-primary btn-load btn-lg" data-loading-text="Changing Password..." value="Change Password">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Profile</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="home">Home</a></li>
                            <li>Profile</li>
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <!-- inner page banner END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <div class="profile-bx text-center">
                                        <div class="user-profile-thumb">
                                            <img src="assets/images/profile/pic1.jpg" alt=""/>
                                        </div>
                                        <div class="profile-info">
                                            <h4>${target.name}</h4>
                                            <span>${target.email}</span>
                                        </div>
                                        <div class="profile-social">
                                            <ul class="list-inline m-a0">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                            <head>

                                                <!-- META ============================================= -->
                                                <meta charset="utf-8">
                                                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                                                <meta name="keywords" content="" />
                                                <meta name="author" content="" />
                                                <meta name="robots" content="" />
                                        
                                                <!-- DESCRIPTION -->
                                                <meta name="description" content="EduChamp : Education HTML Template" />
                                        
                                                <!-- OG -->
                                                <meta property="og:title" content="EduChamp : Education HTML Template" />
                                                <meta property="og:description" content="EduChamp : Education HTML Template" />
                                                <meta property="og:image" content="" />
                                                <meta name="format-detection" content="telephone=no">
                                        
                                                <!-- FAVICONS ICON ============================================= -->
                                        
                                        
                                                <%@page import = "java.util.*"%>
                                                <%@page contentType="text/html" pageEncoding="UTF-8"%>
                                                <%@include file="all_component/allCss.jsp" %>
                                                <!DOCTYPE html>
                                                <html>
                                                    <head>
                                                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                                                        <title>Add New Account</title>
                                                    </head>
                                                    <body>
                                                <%
                                                    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
                                                    response.setHeader("Pragma","no-cache"); //HTTP 1.0
                                                    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
                                                %>
                                                
                                                
                                                        <style>
                                                            .gradient-custom {
                                                                /* fallback for old browsers */
                                                                background: skyblue;
                                                
                                                                /* Chrome 10-25, Safari 5.1-6 */
                                                                /*background: -webkit-linear-gradient(to bottom right, rgba(99, 191, 255, 0.83), rgba(99, 191, 255, 0.83));*/
                                                
                                                                /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                                                                background: linear-gradient(to bottom right, rgba(99, 191, 255, 0.83), rgba(99, 191, 172, 0.83))
                                                            }
                                                
                                                            .card-registration .select-input.form-control[readonly]:not([disabled]) {
                                                                font-size: 1rem;
                                                                line-height: 2.15;
                                                                padding-left: .75em;
                                                                padding-right: .75em;
                                                            }
                                                            .card-registration .select-arrow {
                                                                top: 13px;
                                                            }
                                                        </style>
                                                
                                                
                                                
                                                        <section class="vh-100 gradient-custom">
                                                            <div class="container py-5 h-100">
                                                                <div class="row justify-content-center align-items-center h-100">
                                                                    <div class="col-12 col-lg-9 col-xl-7">
                                                                        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                                                            <div class="card-body p-4 p-md-5">
                                                                                <a href="admin"><i class="fa-solid fa-house fa-2x"></i> </a>
                                                                                <p class="text-danger">${mess}</p> 
                                                                                <p class="text-success">${mess1}</p> 
                                                
                                                                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Account Information:</h3>
                                                
                                                
                                                                                <form id="form" action="adminEditAccount?" method="post">
                                                
                                                                                    <div class="row">
                                                                                        <div class="col-md-6 mb-4">
                                                
                                                                                            <div class="form-outline">
                                                                                                ID<input type="text" id="id" class="form-control form-control-lg" name="id" required="" value="${id}" />
                                                                                                <label class="form-label" for="id"><p class="text-danger">${idErr}</p></label>
                                                                                            </div>
                                                
                                                                                        </div>
                                                                                        <div class="col-md-6 mb-4">
                                                
                                                                                            <div class="form-outline">
                                                                                                Name<input type="text" id="name" class="form-control form-control-lg"  name="name" required="" value="${name}"/>
                                                                                                <label class="form-label" for="name"><p class="text-danger">${nameErr}</p></label>
                                                                                            </div>
                                                
                                                                                        </div>
                                                                                    </div>
                                                
                                                                                    <div class="row">
                                                                                        <div class="col-md-6 mb-4 d-flex align-items-center">
                                                
                                                                                            <div class="form-outline datepicker w-100">
                                                                                                Email<input type="text" class="form-control form-control-lg" id="email" name="email" required="" value="${email}" />
                                                                                                <label for="email" class="form-label"><p class="text-danger">${emailErr}</p></label>
                                                                                            </div>
                                                
                                                                                        </div>
                                                                                        <div class="col-md-6 mb-4">
                                                
                                                                                            <h6 class="mb-2 pb-1">Gender: </h6>
                                                
                                                                                            <div class="form-check form-check-inline">
                                                                                                <input class="form-check-input" type="radio" name="gender" id="femaleGender"
                                                                                                       value="2" checked />
                                                                                                <label class="form-check-label" for="femaleGender">Female</label>
                                                                                            </div>
                                                
                                                                                            <div class="form-check form-check-inline">
                                                                                                <input class="form-check-input" type="radio" name="gender" id="maleGender"
                                                                                                       value="1" />
                                                                                                <label class="form-check-label" for="maleGender">Male</label>
                                                                                            </div>
                                                                                            <div class="form-check form-check-inline">
                                                                                                <input class="form-check-input" type="radio" name="gender" id="fluidGender"
                                                                                                       value="0" checked />
                                                                                                <label class="form-check-label" for="femaleGender">Others</label>
                                                                                            </div>
                                                
                                                
                                                
                                                                                        </div>
                                                                                    </div>
                                                
                                                                                    <div class="row">
                                                                                        <div class="col-md-6 mb-4 pb-2">
                                                
                                                                                            <div class="form-outline">
                                                                                                Phone Number<input type="text" id="phoneNumber" class="form-control form-control-lg" name="phno" required="" value="${phno}" />
                                                                                                <label class="form-label" for="phoneNumber"><p class="text-danger">${phoneErr}</p> </label>
                                                                                            </div>
                                                
                                                                                        </div>
                                                                                        <div class="col-md-6 mb-4 pb-2">
                                                
                                                                                            <!--                                            <div class="form-outline">
                                                                                                                                            <input type="tel" id="phoneNumber" class="form-control form-control-lg" />
                                                                                                                                            <label class="form-label" for="phoneNumber">Phone Number</label>
                                                                                                                                        </div>-->
                                                                                            Status: <select class="select form-control-md" name="status">
                                                                                                <option value="${status}" disabled>Status</option>
                                                                                                <option value="1">Active</option>
                                                                                                <option value="0">Inactive</option>
                                                                                            </select>
                                                                                        </div>
                                                                                    </div>
                                                
                                                                                    <div class="row">
                                                                                        <div class="col-12">
                                                
                                                                                            Role: <select class="select form-control-md" name="role">
                                                                                                <option value="${role}" disabled>Role</option>
                                                                                                <option value="0">Admin</option>
                                                                                                <option value="1">Manager</option>
                                                                                                <option value="2">Lecturer</option>
                                                                                                <option value="3">Student</option>
                                                
                                                
                                                                                            </select>
                                                                                            <label class="form-label select-label"></label>
                                                
                                                                                        </div>
                                                                                    </div>
                                                
                                                                                    <div class="mt-4 pt-2">
                                                                                        <input class="btn btn-primary btn-lg" type="submit" value="Submit" />
                                                                                    </div>
                                                
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </section>
                                                
                                                    </body>
                                                    <script>