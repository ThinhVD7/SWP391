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

        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />

        <style>
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

            /* Main container */
            .main-container
            {
                /*            display: flex;*/
                /*            justify-content: space-between;*/
                padding: 20px;
                flex:1;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 15px;
                margin-left: 30px;
                margin-right: 30px;
                /*margin-top: 30px;*/
                overflow: hidden;
                /*margin-bottom: 30px;*/
            }

            .class-detail
            {
                flex:1;
                background-color: #fff;
                border-radius: 10px;
                /*box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);*/
                padding: 10px;
                margin-left: 30px;
                margin-right: 30px;
                margin-top: 30px;
                display: flex;
                overflow: hidden;
            }

            /* Left div (list of exams) */
            .left-div
            {
                flex: 1;
                padding: 10px;
                border-radius: 10px;
                /*box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);*/
                /*border: 2px solid #ccc;*/
                margin-right: 10px;
                /*                height: 540px;
                                overflow: auto;*/
                /*width: 50%;*/
                /*display:block;*/

            }
            .exam-list
            {
                flex: 1;
                padding: 10px;
                border-radius: 10px;
                /*border: 2px solid #ccc;*/
                margin-top: 10px;
                margin-right: 10px;
                height: 540px;
                overflow: auto;
                /*width: 50%;*/
                /*display:block;*/
            }

            .right-div
            {
                /*flex: 1;*/
                padding: 15px;
                border-radius: 10px;
                /*border: 2px solid #ccc;*/
                position: relative;
                margin-right: 10px;
                overflow: hidden;
                /*display:block;*/
            }

            .exam-container
            {
                flex:1;
                margin-bottom: 20px;
                margin-top: 30px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.7);
                margin-bottom: 20px;
                padding: 15px;
                overflow: hidden;
                /*display: block;*/
            }
            .exam-container:hover
            {
                background-color: #f5f5f5;
            }


            .info-container
            {
                position: relative;
                top: 20px;
                border-radius: 10px;
                /*border: 2px solid #ccc;*/
                background-color: #fff;
                padding: 10px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
                overflow: hidden;
            }

            .edit-exam-button
            {
                /*position: static;*/
                background-color: #299be4;
                color: #fff;
                border: 2px solid #ccc;
                border-radius: 5px;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                margin-top: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
                align-self: center;
                margin-top: auto;
                position: absolute;
                bottom: 10px;
                right: 30%;
            }
            .edit-exam-button:hover{
                background-color: #0073e6;
            }


            .bottom-right-p {
                position: absolute;
                bottom: 0;
                right: 0;
                margin: 0;
            }

            .popup-button {
                background-color: #299be4;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 5px 10px;
                font-size: 20px;
                cursor: pointer;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
                position: fixed;
                top: 3%;
                right:5%;
            }

            .popup-button:hover{
                background-color: #0073e6;
            }

            /* Style for the pop-up */
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
                width: 75%;


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

            .add-exam-button {
                background-color: #299be4;
                color: #fff;
                border: 2px solid #ccc;
                border-radius: 5px;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                margin-bottom: 500px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
                align-self: center;
                /*margin-top: auto;*/
                position: static;
                top: 150px;
                right: 600px;
            }
            .add-exam-button:hover{
                background-color: #0073e6;
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
            <div><br></div>

            <!--            <div class="row pt-2">
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
                        </div>-->


            <!--            <div class="class-list">
            <c:forEach items="${requestScope.classList}" var="c">
                <div class="class-containder">
                    <a style="text-decoration: none;"  href="#"> 
                    <h3>${c.className}</h3>
                </div>
                </div>
            </c:forEach>
        </div>-->



            <div class="main-container">
                <!--pop up start----------------------------------------------------------------------------------------------->
                <!-- Blurred overlay -->
                <div id="overlay" class="overlay"></div>
                <div id="viewStudentListPopup" class="overlay" onclick="closePopup()">
                    <div class="popup-container" onclick="event.stopPropagation();">
                        <h2>Student List</h2>
                        <button class ="popup-button" onclick="closePopup()">Close</button>


                        <div class="container-xl">
                            <div class="table-responsive">
                                <div class="table-wrapper">
                                    <div class="table-title">
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <h2> <b>${sessionScope.sessionThisClass.className}</b></h2>
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
                                                <th>#</th>
                                                <th>ID</th>						
                                                <th>Name</th>
                                                <th>Email</th>
                                                <th>View Profile</th>
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
                                            <c:forEach items = "${requestScope.studentList}" var="student" varStatus="x">
                                                <tr>
                                                    <td>${x.count}</td>
                                                    <td><a href="#">${student.accountID}</a></td>
                                                    <td>${student.name}</td>                        
                                                    <td>${student.email}</td>
                                                    <td><a href="viewProfileFromList?targetID=${student.accountID}"> <i class='bx bxs-user-account'></i> </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <!--<input type="text" id="newClassName" placeholder="New Class Name">-->
                        <!--                <button class ="popup-button" onclick="editClassName()">Save</button>-->
                    </div>
                </div>
                <!--pop up end------------------------------------------------------------------------------------------------->

                <div style="font-size: 36px; margin-bottom: 10px;">
                    ${sessionScope.sessionPageTitle}
                </div>
                <div>
                    <a style ="padding: 5px;"
                       href="home">Home</a> / 
                    <a style ="padding: 5px;" 
                       href="lecturerClasslist?courseID=${sessionScope.sessionThisCourse.courseID}">${sessionScope.sessionThisCourse.courseID}</a> /
                    <a style ="padding: 5px;" 
                       href="#">${sessionScope.sessionThisClass.className}</a>
                </div>

                <div class ="class-detail" >

                    <!--            <div>
                                    <a style ="padding:5px;">
                                        
                                </div>-->
                    <!-- Right div (Class information) -->

                    <div class="left-div">


                        <h2>Exam</h2>

                        <div class ="exam-list">
                            <c:if test="${sessionScope.lecturerX!= null}">
                                <a style="text-decoration: none;color: white" href="lecturerAddNewExam" class="add-exam-button" value="">Add Exam</a>

                            </c:if>

                            <c:forEach items = "${requestScope.examList}" var="exam">
                                <div class="exam-container">
                                    <a style="text-decoration: none;"  href="lecturerExamDetail?examID=${exam.examID}"> 
                                        <h3>${exam.examName}</h3>
                                        <br>
                                        Start Date: ${requestScope.examStartDate[exam.examID]} to ${requestScope.examEndDate[exam.examID]}<br>
                                        Click to view details
                                    </a>
                                    <div class="float-right" style= ${requestScope.deleteNotAllowMap[exam.examID]?"display:none":""}>
                                        <c:if test="${sessionScope.lecturerX  != null}">

                                            <a class="delete" onclick="confirmDelete(${exam.examID})" title="Delete" data-toggle="tooltip">
                                                <i class="fa fa-trash" aria-hidden="true"></i>
                                            </a>
                                        </c:if>

                                    </div>
                                </div>
                            </c:forEach>



                            <!--            <div class="exam-container">
                                            <a style="text-decoration: none;"  href="#"> 
                                            <h3>Final Trial</h3>
                                            <br>
                                            Start Date: 16/10/2023 to 21/10/2023<br>
                                            Click to view details
                                        </div>-->
                        </div>




                    </div>
                    <div class="right-div">
                        <!-- Information container (fixed position) -->
                        <div class="info-container">
                            <a style="font-size: 24px; font-style: inherit"></a>
                            <h2>Class Information</h2>
                            <p>Start Date: ${sessionThisCourse.startDate} to ${sessionThisCourse.endDate}</p><br>
                            <p>Lecturer: ${requestScope.lecturer.name}</p><br>
                            <p>Email: ${requestScope.lecturer.email}</p><br>
                            <br><br>
                            <!--                <form action="action"> -->
                            <button class="edit-exam-button" onclick="openPopup()">View Students</button>
                            <!--                </form>-->
                        </div>
                    </div>




                </div>
            </div>



        </section>

        <script>
            // Function to open the edit class name pop-up
            function openPopup() {
                const overlay = document.getElementById('viewStudentListPopup');
//                const newClassNameInput = document.getElementById('newClassName');
//                newClassNameInput.value = className;
                overlay.style.display = 'block';
            }

            // Function to close the pop-up
            function closePopup() {
                const overlay = document.getElementById('viewStudentListPopup');
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

            function changebtn() {
                if (side_bar.classList.contains("expand")) {
                    btn_menu.classList.replace("bx-menu", "bx-menu-alt-right");
                } else {
                    btn_menu.classList.replace("bx-menu-alt-right", "bx-menu");
                }
            }

            function confirmDelete(examID) {
                var confirmDelete = confirm("Are you sure you want to delete this exam?");
                if (confirmDelete) {
                    // Make an AJAX request to delete the exam
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "lecturerDeleteExam?examID=" + examID, true);

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            // Handle the response from the servlet
                            var response = xhr.responseText;
                            alert("Exam with ID " + examID + " deleted.");
                            location.reload();
                        }
                    };

                    // Send the request
                    xhr.send();
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