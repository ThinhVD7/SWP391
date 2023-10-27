<%-- 
    Document   : admin-Homepage
    Created on : Sep 27, 2023, 12:56:44 AM
    Author     : tanki
--%>
<%@page import = "java.util.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="all_component/allCss.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Account Management Data Table</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
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
            .popup-button {
            background-color: #299be4;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            font-size: 20px;
            cursor: pointer;
            margin-right: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
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
        }
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
        <div id="deletePopup" class="overlay" onclick="closePopup()">
            <div class="popup-container" onclick="event.stopPropagation();">
                <h2>Are you sure you want to delete this account with ID:</h2>
                <p> <span id="deleteIDplace"></span></p>
                    <button class ="popup-button" onclick="deleteAccount()">Yes</button>                    
             
                <button class ="popup-button" onclick="closePopup()">No</button>
            </div>
        </div>    
        
        <div class="container-xl">
         
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-5">
                                <h2> <a style="text-decoration: none;color: red" href="home"><i class="fa-solid fa-house "></i> </a><b>Account Management</b></h2>
                            </div>
                            <div class="col-sm-7">
                                <a href="addAccount" class="btn btn-secondary"><i class="material-icons">&#xE147;</i> <span>Add New Account</span></a>
                                <a href="Logout" class="btn btn-secondary"> <span>Logout</span></a>
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
                                <th>Role</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${requestScope.listA}" var="u" varStatus="x" >
                                <tr>
                                    <td>${x.count}</td>
                                    <td><a href="#">${u.accountID}</a></td>
                                    <td>${u.name}</td>                        
                                    <td>${u.email} </td>
                                    <c:if test = "${u.roleID == 0}">
                                        <td>Admin</td>
                                    </c:if>
                                    <c:if test = "${u.roleID == 1}">
                                        <td>Manager</td>
                                    </c:if>
                                    <c:if test = "${u.roleID == 2}">
                                        <td>Lecturer</td>
                                    </c:if>
                                    <c:if test = "${u.roleID == 3}">
                                        <td>Student</td>
                                    </c:if>
                                    <c:if test = "${u.status ==1}">
                                        <td><span class="status text-success">&bull;</span> Active</td>
                                    </c:if>
                                    <c:if test = "${u.status ==0}">
                                        <td><span class="status text-danger">&bull;</span> Inactive</td>
                                    </c:if>
                                    <td>
                                        <a href="adminEditAccount?targetID=${u.accountID}" class="settings" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
                                        <a  class="delete" onclick="openPopup('${u.accountID}')" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <<script>
            // Function to open pop-up
            function openPopup(deleteID) {
//                var dk = confirm(deleteID);
                document.getElementById('deleteIDplace').textContent = deleteID;
                document.getElementById('deletePopup').style.display = "block";
                document.getElementById('deletePopup').setAttribute("deletedID", deleteID);
//                overlay.style.display = 'block';
            }
            // Function to close the pop-up
            function closePopup() {
                const overlay = document.getElementById('deletePopup');
                overlay.style.display = 'none';
            }
            
            function deleteAccount()
            {
                var deletedID = document.getElementById("deletePopup").getAttribute("deletedID");
//                var dk = confirm("This account will be deleted, and you have to take full responsibility");
//                var dk = confirm(deletedID);
//                $.ajax({
//                        url: contextPath + "/adminDeteleAccount",
//                        type: "POST",
//                        data: {
//                            deletedID1: deletedID
//                        },
//                        success: function (response) {
//                            window.location.reload();
//                        },
//                        error: function (xhr, status, error) {
//                            closePopup();
//                        }
//                    });
//                closePopup();
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "adminDeleteAccount", true);
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
                xhr.send("deletedID=" + deletedID);
            }
        </script>
    </body>
</html>
