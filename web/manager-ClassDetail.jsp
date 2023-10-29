<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Lecturer</title>
        <%@include file="all_component/allCss.jsp" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="all_component/studentStyle.css" />

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />

        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #299be4;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }
            form.nosubmit {
                padding: 0;
                border: none;
            }

            input.nosubmit {
                border: 1px solid #555;
                border-radius: 25px;
                width: 100%;
                padding: 9px 4px 9px 40px;
                background: transparent url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' class='bi bi-search' viewBox='0 0 16 16'%3E%3Cpath d='M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z'%3E%3C/path%3E%3C/svg%3E") no-repeat 13px center;
            }
            .card-title {
                text-decoration: none;

            }
            .class-list {
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 15px;
                margin-left: 30px;
                margin-right: 30px;
                margin-top: 30px;
            }

            .class-container {
                margin-bottom: 20px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
                padding: 15px;
                display: block;
                width: 50%;
            }
            .exam-detail {
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 15px;
                margin: 20px auto;
                width:80%;
                text-align: center;

            }

            .edit-exam-button {
                background-color: #3366CC;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                margin-top: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.9);
            }


            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;

            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                /*box-shadow: 0 1px 1px rgba(0,0,0,.05);*/
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);

            }
            .table-title {
                padding-bottom: 15px;
                background: #299be4;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }

            .edit-button {
                background-color: #299be4;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 5px 10px;
                font-size: 14px;
                cursor: pointer;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
            }
            .popup-button {
                background-color: #299be4;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 5px 10px;
                font-size: 10px;
                cursor: pointer;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
            }
            /* Style for the pop-up */
            .popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 15px;
                z-index: 1;
            }
            .overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0); /* Semi-transparent black background */
                backdrop-filter: blur(4px); /* Adjust the blur intensity as needed */
                z-index: 1;
            }
            .addCourse{
                position: absolute;
                top: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                width: 100%;
                height: 100%;

            }

            .addCourseContent{
                padding: 60px;
                border: 2px solid #ccc;
                border-radius: 5px;
                background-color: #fff;
                width: 50%;
                display: flex;
                flex-direction: column;
                text-align: center;
                margin: auto;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);

            }

            .addCourseTitle{
                color: blue;
                font-family: sans-serif;
                letter-spacing: 2px;
                word-spacing: 3px;
                font-weight: bold;
                font-size: 2.4rem;
                margin-bottom: 20px;
            }
            .form-field {
                padding-left: 10px;
                margin-bottom: 20px;
                border-radius: 20px;
                box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
            }

            .form-field input {
                width: 100%;
                display: block;
                border: none;
                outline: none;
                background: none;
                font-size: 1.2rem;
                color: #666;
                padding: 10px 15px 10px 10px;
                /* border: 1px solid red; */
            }
            .closePopUp{
                padding: 4px 12px;
                background-color: orange;
                border: 1px solid transparent;
                color: #fff;
                font-weight: 600;
                transition: .4s;
                margin-top: 10px;
                border-radius: 5px;

                &:hover{
                    transform: scale(1.05);
                }
            }
            .closePopUp1{
                padding: 4px 12px;
                background-color: green;
                border: 1px solid transparent;
                color: #fff;
                font-weight: 600;
                transition: .4s;
                margin-top: 10px;
                border-radius: 5px;

                &:hover{
                    transform: scale(1.05);
                }
            }

            /* Style for the clear pop-up container */
            .popup-container {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 15px;
                z-index: 2;
                overflow: auto;
                height: 500px;
                width: 75%;



            }
        </style>
    </head>
    <body>
        <section class="sidebar">
            <div class="nav-header">
                <p class="logo">Quiz Practice</p>
                <i class="bx bx-menu btn-menu"></i>
            </div>
            <ul class="nav-links">

                <li>
                    <i class="bx bx-search search-btn"></i>
                    <input type="text" placeholder="Search" />
                    <span class="tooltip">Search</span>
                </li>
                <li>
                    <a href="profile">
                        <i class='bx bxs-user-account'></i>
                        <span class="title">Profile</span>
                    </a>
                    <span class="tooltip">${sessionScope.user.name}</span>
                </li>
                <li>
                    <a href="home">
                        <i class="bx bx-home-alt-2"></i>
                        <span class="title">Home</span>
                    </a>
                    <span class="tooltip">Home</span>
                </li>
                <li>
                    <a href="Logout">
                        <i class="bx bxs-devices"></i>
                        <span class="title">Logout</span>
                    </a>
                    <span class="tooltip">Logout</span>
                </li>

            </ul>
            <div class="theme-wrapper">
                <i class="bx bxs-moon theme-icon"></i>
                <p>Dark Theme</p>
                <div class="theme-btn">
                    <span class="theme-ball"></span>
                </div>
            </div>
        </section>
        <section class="home">

            <div class="row pt-2">
                <div class="col-md-9 ml-2">
                    <div class="input-group rounded">
                        <form class="nosubmit">
                            <input class="nosubmit" type="search" placeholder="Search">
                        </form>
                    </div>
                </div>

                <div class="col-md-2 ml-auto">
                    <a href="profile" style="text-decoration: none;"><div class="align-self-end"><i class="fa-solid fa-user fa-xl"></i></div></a>
                </div>
            </div>


            <div class="class-list">
                <!--pop up--------------------------------------------------------------------------------------------------->
                <!--<form action="managerViewLecturer" method="post">-->

                <div id="classEditPopup" class="overlay" onclick="closePopup()">
                    <div class="popup-container" onclick="event.stopPropagation();">
                        <h2>Edit Class Name</h2>
<!--                        <input name="classID" value="${requestScope.classID}" style="display: none"/>
                        <input name="className" value="${requestScope.className}" style="display: none"/>
                        <input name="courseID" value="${requestScope.courseID}" style="display: none"/>-->  
                        <form action="managerEditClass" method="post">
                            <input name="cid" value="${requestScope.cid}" hidden="">
                            <input name="courseID" value="${requestScope.courseID}" hidden=""/>   
                            <input name="className" type="text" id="newClassName" placeholder="Hello">
                            <button class ="popup-button" onclick="editClassName()" type="submit">Save</button>
                        </form>            
                            <button  class ="popup-button" onclick="closePopup()">Cancel</button>
                    </div>
                </div>
                <!--Assign Lecturer-->
                <form action="managerViewLecturer" method="post">
                    <div id="assignLecturerPopup" class="overlay" onclick="closeAssignLecturerPopup()"> 
                        <div class="popup-container" onclick="event.stopPropagation();">
                            <h2>Assign Lecturer</h2>
                            <input type="text" id="search" placeholder="New Class Name">
                            <div class="container-xl">
                                <div class="table-responsive">
                                    <div class="table-wrapper">
                                        <div class="table-title">
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <h2> </a><b>Lecturer</b></h2>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table table-striped table-hover">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>ID</th>						
                                                    <th>Name</th>
                                                    <th>Email</th>

                                                    <th>Add</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                            <input name="classID" value="${requestScope.classID}" style="display: none"/>
                                            <input name="courseID" value="${requestScope.courseID}" style="display: none"/>
                                            <input name="status" value="1" style="display: none"/>

                                            <c:forEach items="${requestScope.addlecturer}" var="u" varStatus="x" >
                                                <tr>
                                                    <td>${x.count}</td>
                                                    <td><a href="#">${u.accountID}</a></td>
                                                    <td>${u.name}</td>                        
                                                    <td>${u.email} </td>

                                                    <td>
                                                        <input type="checkbox" name="add" value=${u.accountID} />
                                                    </td>
                                                </tr>
                                            </c:forEach>


                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <input name="cid" value="${requestScope.cid}" hidden="">
                            <button class ="popup-button" onclick="editClassName()">Save</button>
                            <span class ="popup-button" onclick="closeAssignLecturerPopup()">Cancel</span>
                        </div>
                    </div>
                </form>
                <!--Assign Student------------------------->
                <form action="managerViewLecturer" method="post">
                    <div id="assignStudentrPopup" class="overlay" onclick="closeAssignStudentPopup()"> 
                        <div class="popup-container" onclick="event.stopPropagation();">
                            <h2>Assign Student</h2>              
                            <input type="text" id="search" placeholder="New Class Name">
                            <div class="container-xl">
                                <div class="table-responsive">
                                    <div class="table-wrapper">
                                        <div class="table-title">
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <h2> </a><b>Student</b></h2>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table table-striped table-hover">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>ID</th>						
                                                    <th>Name</th>
                                                    <th>Email</th>

                                                    <th>Add</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                            <input name="classID" value="${requestScope.classID}" style="display: none"/>
                                            <input name="courseID" value="${requestScope.courseID}" style="display: none"/>
                                            <input name="status" value="0" style="display: none"/>

                                            <c:forEach items="${requestScope.addstudent}" var="u" varStatus="x" >
                                                <tr>
                                                    <td>${x.count}</td>
                                                    <td><a href="#">${u.accountID}</a></td>
                                                    <td>${u.name}</td>                        
                                                    <td>${u.email} </td>

                                                    <td>
                                                        <input type="checkbox" name="add" value="${u.accountID}" />
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <input name="cid" value="${requestScope.cid}" hidden="">
                            <button class ="popup-button" onclick="editClassName()">Save</button>
                            <span class ="popup-button" onclick="closeAssignStudentPopup()">Cancel</span>
                        </div>
                    </div>
                </form>

                <!--pop up--------------------------------------------------------------------------------------------------->

                <div> 
                    <a style="font-size: 36px; margin-bottom: 10px; width:50%">
                        ${requestScope.classInfo.className}
                        <button class="edit-button" onclick="openPopup()">Edit Name</button>

                </div>

                <div>
                    <a style ="padding: 5px;"
                       href="#">Home</a> / 
                    <a style ="padding: 5px;" 
                       href="managerViewClassList?courseID=${requestScope.courseID}">${requestScope.courseID}</a> /
                    <a style ="padding: 5px;" 
                       >${requestScope.classInfo.className}</a>
                </div>
                <%
                    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
                    response.setHeader("Pragma","no-cache"); //HTTP 1.0
                    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
                %>
                <div class="container-xl">
                    <div class="table-responsive">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-5">
                                        <h2> </a><b>Lecturer</b></h2>
                                    </div>
                                    <c:if test="${requestScope.enableAdd <= 0}">
                                        <div class="col-sm-7">
                                            <button onclick="openAssignLecturerPopup()"class="btn btn-secondary" style ="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);"><i class="material-icons">&#xE147;</i> <span>Add Lecturer</span></button>

                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>ID</th>						
                                        <th>Name</th>
                                        <th>Email</th>

                                        <th>Action</th>
                                    </tr>
                                </thead>


                                <tbody>

                                    <c:forEach items="${requestScope.lecturer}" var="u" varStatus="x" >
                                        <tr>
                                            <td>${x.count}</td>
                                            <td><a href="#">${u.accountID}</a></td>
                                            <td>${u.name}</td>                        
                                            <td>${u.email} </td>

                                            <td>
                                                <a href="#" class="deleterCouse" title="Delete" data-toggle="tooltip"><a onclick="delLecturer('${u.accountID}')"> <i class="material-icons">&#xE5C9;</i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>


                                </tbody>
                            </table>
                            <!--                    <div class="clearfix">
                                                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                                                    <ul class="pagination">
                                                        <li class="page-item disabled"><a href="#">Previous</a></li>
                                                        <li class="page-item"><a href="#" class="page-link">1</a></li>
                                                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                                                        <li class="page-item active"><a href="#" class="page-link">3</a></li>
                                                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                                                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                                                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                                                    </ul>
                                                </div>-->
                        </div>
                    </div>
                </div>     
                <div class="container-xl">
                    <div class="table-responsive">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-5">
                                        <h2> </a><b>Student</b></h2>
                                    </div>
                                    <div class="col-sm-7">
                                        <button onclick="openAssignStudentPopup()"class="btn btn-secondary" style ="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);"><i class="material-icons">&#xE147;</i> <span>Add Student</span></button>

                                    </div>
                                </div>
                            </div>
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>ID</th>						
                                        <th>Name</th>
                                        <th>Email</th>

                                        <th>Action</th>
                                    </tr>
                                </thead>

                                <c:forEach items="${requestScope.student}" var="u" varStatus="x" >
                                    <tr>
                                        <td>${x.count}</td>
                                        <td><a href="#">${u.accountID}</a></td>
                                        <td>${u.name}</td>                        
                                        <td>${u.email} </td>


                                        <td>
                                            <a href="#" class="deleterCouse" title="Delete" data-toggle="tooltip"><a onclick="delStudent('${u.accountID}')"> <i class="material-icons">&#xE5C9;</i></a>
                                        </td>
                                    </tr>
                                </c:forEach>


                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>     
            </div>
        </section>
        <!-- Blurred overlay -->
        <div id="overlay" class="overlay"></div>
        <script>


            if ('${sessionScope.messOfClass}' === '1') {
                alert("Id Class này đã tồn tại");
            }




            // Function to open the edit class name pop-up
            function openPopup(className) {
                const overlay = document.getElementById('classEditPopup');
                const newClassNameInput = document.getElementById('newClassName');
                newClassNameInput.value = className;
                overlay.style.display = 'block';
            }
            function openDelete() {
                const divPopUp = document.querySelector("#deleterCouse");
                const showBtn = document.querySelector("show");
                divPopUp.style.display = 'block';

            }
            function openAssignLecturerPopup(className) {
                const overlay = document.getElementById('assignLecturerPopup');
                const newClassNameInput = document.getElementById('search');
                newClassNameInput.value = className;
                overlay.style.display = 'block';
            }
            function openAssignStudentPopup(className) {
                const overlay = document.getElementById('assignStudentrPopup');
                const newClassNameInput = document.getElementById('search');
                newClassNameInput.value = className;
                overlay.style.display = 'block';
            }

            // Function to close the pop-up
            function closePopup() {
                const overlay = document.getElementById('classEditPopup');
                overlay.style.display = 'none';
            }
            function closePopUp() {
                const divPopUp = document.querySelector("#deleterCouse");
                divPopUp.style.display = 'none';
            }
            function closeAssignLecturerPopup() {
                const overlay = document.getElementById('assignLecturerPopup');
                overlay.style.display = 'none';
            }
            function closeAssignStudentPopup() {
                const overlay = document.getElementById('assignStudentrPopup');
                overlay.style.display = 'none';
            }

            // Function to save the edited class name
            function editClassName() {
                const newClassNameInput = document.getElementById('newClassName');
                const updatedClassName = newClassNameInput.value;

                // Update the class name in your data or display
                console.log('Updated Class Name:', updatedClassName);
                // Close the pop-up
                closePopup();
            }

            const btn_menu = document.querySelector(".btn-menu");
            const side_bar = document.querySelector(".sidebar");

            btn_menu.addEventListener("click", function () {
                side_bar.classList.toggle("expand");
                changebtn();
            });
            function delLecturer(lecturerId) {
                var dk = confirm('Bạn có muốn xóa không ?');
                var lecturerId = lecturerId;
                if (dk) {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "managerDeleteLecturer", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    xhr.onreadystatechange = function ()
                    {
                        if (xhr.readyState === 4 && xhr.status === 200)
                        {
                            // Handle the response from the server (if needed)
                            var response = xhr.responseText;
                            // Reload or update the page as necessary
                            location.reload();
                        }
                    };
                    xhr.send("lecturerId=" + lecturerId);
                } else {

                }

            }
            function delStudent(studentID) {
                var dk = confirm('Bạn có muốn xóa không ?');
                var studentID = studentID;
                if (dk) {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "managerDeleteStudent", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    xhr.onreadystatechange = function ()
                    {
                        if (xhr.readyState === 4 && xhr.status === 200)
                        {
                            // Handle the response from the server (if needed)
                            var response = xhr.responseText;
                            // Reload or update the page as necessary
                            location.reload();
                        }
                    };
                    xhr.send("studentID=" + studentID);
                } else {

                }

            }
            function changebtn() {
                if (side_bar.classList.contains("expand")) {
                    btn_menu.classList.replace("bx-menu", "bx-menu-alt-right");
                } else {
                    btn_menu.classList.replace("bx-menu-alt-right", "bx-menu");
                }
            }

            const btn_theme = document.querySelector(".theme-btn");
            const theme_ball = document.querySelector(".theme-ball");

            const localData = localStorage.getItem("theme");

            if (localData == null) {
                localStorage.setItem("theme", "light");
            }

            if (localData == "dark") {
                document.body.classList.add("dark-mode");
                theme_ball.classList.add("dark");
            } else if (localData == "light") {
                document.body.classList.remove("dark-mode");
                theme_ball.classList.remove("dark");
            }

            btn_theme.addEventListener("click", function () {
                document.body.classList.toggle("dark-mode");
                theme_ball.classList.toggle("dark");
                if (document.body.classList.contains("dark-mode")) {
                    localStorage.setItem("theme", "dark");
                } else {
                    localStorage.setItem("theme", "light");
                }
            });
        </script>
    </body>
</html>