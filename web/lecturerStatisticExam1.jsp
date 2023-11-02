<%-- 
    Document   : lecturerStatisticExam
    Created on : Oct 30, 2023, 2:47:34 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/data.js"></script>
        <script src="https://code.highcharts.com/modules/drilldown.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script src="https://code.highcharts.com/modules/export-data.js"></script>
        <script src="https://code.highcharts.com/modules/accessibility.js"></script>
        <title>Lecturer Statistic</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Lecturer</title>
        <%@include file="all_component/allCss.jsp" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="all_component/studentStyle.css" />

        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />

        <style>
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
                height: 420px;
                overflow: auto;
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
            .main-container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
            margin-left: 30px;
            margin-right: 30px;
            margin-bottom: 30px;
            /*margin-top: 10px;*/
            position: relative;
            }
            
            .exam-detail {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);
            padding: 15px;
            margin: 20px auto;
            width:55%;
            text-align: center; 
            font-size: 1em;
            overflow: hidden;
            position: relative;
/*            margin-left: 300px;
            margin-right: 300px;*/
        }

        .edit-exam-button 
        {
            background-color: #299be4;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.9);
        }
        .edit-exam-button:hover
        {
            background-color: #0073e6;
        }
        .edit-exam-button-bottom
        {
            background-color: #299be4;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.9);
            position: absolute;
            bottom: 25px;
            left: 20%;
        }
        .edit-exam-button-bottom:hover{
            background-color: #0073e6;
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
        
         .bottom-right-p {
            position: absolute;
            bottom: 0;
            right: 0;
            margin: 0;
        }
        
        
        </style>
        <style>
              .highcharts-credits {
display: none !important;
}
            .highcharts-figure,
            .highcharts-data-table table {
                min-width: 310px;
                max-width: 800px;
                margin: 1em auto;
            }

            #container {
                height: 400px;
            }

            .highcharts-data-table table {
                font-family: Verdana, sans-serif;
                border-collapse: collapse;
                border: 1px solid #ebebeb;
                margin: 10px auto;
                text-align: center;
                width: 100%;
                max-width: 500px;
            }

            .highcharts-data-table caption {
                padding: 1em 0;
                font-size: 1.2em;
                color: #555;
            }

            .highcharts-data-table th {
                font-weight: 600;
                padding: 0.5em;
            }

            .highcharts-data-table td,
            .highcharts-data-table th,
            .highcharts-data-table caption {
                padding: 0.5em;
            }

            .highcharts-data-table thead tr,
            .highcharts-data-table tr:nth-child(even) {
                background: #f8f8f8;
            }

            .highcharts-data-table tr:hover {
                background: #f1f7ff;
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
<!--seach and profile icon------------------------------------------------------------------------------------>
<div> <br> </div>

<!--main block------------------------------------------------------------------------------------------------->
        <div class="main-container">
            <div>
                <a style="font-size: 36px; margin-bottom: 10px;">
                    ${sessionScope.sessionPageTitle}
            </div>
            <div>
                <a style ="padding: 5px;"
                    href="home">Home</a> / 
                    <a style ="padding: 5px;" 
                       href="lecturerClasslist?courseID=${sessionScope.sessionThisCourse.courseID}">${sessionScope.sessionThisCourse.courseID}</a> /
                        <a style ="padding: 5px;" 
                           href="lecturerExamList?classID=${sessionScope.sessionThisClass.classID}">${sessionScope.sessionThisClass.className}</a> /
                            <a style ="padding: 5px;" 
                               href="lecturerExamDetail?examID=${sessionScope.sessionThisExam.examID}">${sessionScope.sessionThisExam.examName}</a>
            </div>
            <div>
                <a style ="padding:5px;">
            </div>
            <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper" style = "margin-left: 50px;
            margin-right: 50px;">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-5">
                                <h2> <b>${sessionScope.sessionThisExam.examName}</b></h2>
                            </div>
                            <div class="col-sm-7">
<!--                                <a href="managerAssignLecturer" class="btn btn-secondary" style ="box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);"> <span>Submit</span></a>
                                -->
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>						
                                <th>Score</th>
                                <th>Time</th>
                                <th>View Log</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" begin="1" end="30" step="1">
<!--                            <tr>
                                <td>${i}</td>
                                <td><a href="#">ThanhDT59</a></td>
                                <td>Do Tien Thanh</td>                        
                                <td>thanhdt59@fe.edu.vn </td>
                                <td><a href="#"> <i class='bx bxs-user-account'></i> </td>
                            </tr>-->
                            </c:forEach>
                            <c:forEach items="${requestScope.resultTable}" var="s">
                                <tr>
                                    <th>${s.studentID}</th>
                                    <td>${s.totalScore}</td>
                                    <td>${s.totalTime}</td>
                                    <td><a href="viewProfileFromList?targetID=${student.accountID}"> <i class='bx bxs-user-account'></i></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <figure class="highcharts-figure">
            <div id="container" ></div>
            <table id="datatable" style ="display:none">
                <thead>
                    <tr>
                        <th></th>
                        <th>Number Student</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.studentResult}" var="s">
                        <tr>
                            <th>${s.resultID}</th>
                            <td>${s.state}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form action="lecturerStatisticExam">
                <input value="${requestScope.examId}" name="examId" type="hidden"/>
                <button type="submit" class="edit-exam-button-bottom">
                    Change Chart
                </button>
            </form>
        </figure>
                       </section>


    </body>

    <script>
        Highcharts.chart('container', {
            data: {
                table: 'datatable'
            },
            chart: {
                type: 'column'
            },
            title: {
                text: 'Score of ${requestScope.examName}'
            },
            xAxis: {
                type: 'category'
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: 'Score'
                }
            },
//                    credits:
//              {
//            enabled: false
//              }

        exporting:{
            enabled: false
            }
        });

    </script>
</html>
