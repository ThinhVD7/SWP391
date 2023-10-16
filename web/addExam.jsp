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
            .main {
                /*display: flex;*/
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
                justify-content: center;
            }

            .left-div
            {
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);
                padding: 15px;
                margin: 20px auto;
                width:70%;
                background-color: #f5f5f5;
                flex: 1;
                padding: 10px;
                border-radius: 10px;
                /*box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);*/
                border: 2px solid #ccc;
                /*margin-right: 10px;*/
                /*                height: 540px;
                                overflow: auto;*/
                /*width: 50%;*/
                /*display:block;*/
            }
            .right-div
            {
                flex: 1;
                padding: 10px;
                border-radius: 10px;
                /*box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);*/
                border: 2px solid #ccc;
                margin-right: 10px;
            }

            .question-container
            {
                flex:1;
                margin-bottom: 20px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.7);
                margin-bottom: 20px;
                padding: 15px;
                overflow: hidden;
                /*display: block;*/
            }

            .question-list
            {
                flex: 1;
                padding: 10px;
                border-radius: 10px;
                /*border: 2px solid #ccc;*/
                margin-right: 10px;
                height: 300px;
                overflow: auto;
                /*width: 50%;*/
                /*display:block;*/
            }



            .edit-exam-button
            {
                /*position: static;*/
                background-color: #299be4;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                margin-top: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
                align-self: center;
                margin-top: auto;
                margin-bottom: auto;
            }
            .edit-exam-button:hover{
                background-color: #0073e6;
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


            @import url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap');

            *{
                margin: 0;
                padding: 0;
                list-style: none;
                font-family: 'Josefin Sans', sans-serif;
            }

            body{
                background: #292929;
            }

            .wrapper{
                width: 100%;
                background: #fff;
                margin: 20px auto;
                border-radius: 3px;
            }

            .wrapper .tabs ul{
                width: 100%;
                height: 60px;
                display: flex;
            }

            .wrapper .tabs ul li{
                width: 33%;
                height: 65px;
                padding: 0 20px;
                text-align: center;
                background: #dbe3eb;
                cursor: pointer;
                text-transform: uppercase;
                color: #8b9393;
                font-size: 14px;
                letter-spacing: 2px;
                transition: all 0.3s ease;
            }

            .wrapper .tabs ul li:first-child{
                border-top-left-radius: 3px;
            }

            .wrapper .tabs ul li:last-child{
                border-top-right-radius: 3px;
            }

            .wrapper .tabs ul li img{
                width: 35px;
                height: 35px;
                display: block;
                margin: 0 auto 3px;
            }

            .wrapper .content{
                padding: 30px;
                height: 260px;
                border-bottom: 6px solid #44c8fe;
                border-radius: 3px;
            }

            .wrapper .content .tab_content{
                font-size: 14px;
                line-height: 22px;
            }

            .wrapper .tabs ul li.active{
                border-top: 6px solid #44c8fe;
                margin-top: -15px;
                background: #fff;
                color: #44c8fe;
                padding-top: 9px;
            }
            @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: "Poppins", sans-serif;
                --color-hover: rgba(32, 59, 232);
                --transition: all 0.2s ease;
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
            <div class="main">
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
                       href="lecturerExamList?classID=${sessionScope.sessionThisClass.classID}">${sessionScope.sessionThisClass.className}</a>
                </div>
                <div class ="right-div">
                    <div class="row align-items-center">

                        <div class="mx-auto col-10 col-md-8 col-lg-6">
                            <form action="AddNewExam" method="post" class="">
                                <a style ="padding:5px;"><h2 style="text-align: center">Add New Exam</h2></a>
                                <input hidden="true" value="${sessionScope.sessionThisCourse.startDate}" id="startDate" >
                                <input hidden="true" value="${sessionScope.sessionThisCourse.endDate}" id="endDate" >

                                <div class="form-group row">
                                    <label for="examName" class="col-sm-3 col-form-label" style="font-weight: bold">Exam's Name:</label>
                                    <div class="col-sm-5">
                                        <input type="text" name="examName" class="form-control" id="examName" placeholder="" required="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="timeLim" class="col-sm-3 col-form-label" style="font-weight: bold">Time Limit:</label>
                                    <div class="col-sm-5">
                                        <input type="number" name="timeLimit" class="form-control" id="timeLim" min ="0" placeholder="0" required=""> (minutes)
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="date" class="col-sm-3 col-form-label" style="font-weight: bold">Set exam date:</label>
                                    <div class="col-sm-5">
                                        From: <input type="datetime-local" name="fromDate" class="form-control" id="fromDate" placeholder="" required="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="date" class="col-sm-3 col-form-label" style="font-weight: bold">Set exam date:</label>
                                    <div class="col-sm-5">
                                        To: <input type="datetime-local" name="toDate" class="form-control" id="toDate" placeholder="" required="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="permission" class="col-sm-4 col-form-label" style="font-weight: bold">Set preview permission:</label>
                                    <div class="col-md-6">
                                        <select id="permission" name="permission" class="mt-2 pl-5 pr-5" required="">
                                            <option value="" disabled selected="selected"></option>
                                            <option value="1">Allow</option>
                                            <option value="0">Not Allow</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <input class="btn btn-primary btn-lg align-items-center col-6" style="margin: 0 auto; display: block;border-radius:20px " type="submit" value="Submit" />
                                </div>

                            </form>
                        </div>
                    </div>
                    <div class ="left-div">
                        <div class="row align-items-center">

                            <div class="mx-auto col-10 col-md-8 col-lg-6">
                                <form action="AddNewExam" method="post" class="">
                                    <a style ="padding:5px;"><h3 style="text-align: center">Questions</h3></a>



                                    <a style="text-decoration: none">
                                        <div class="form-group row">
                                            <input class="btn btn-primary btn-lg align-items-center col-6" style="margin: 0 auto; display: block;border-radius:20px" type="button"  value="Add Question(s)" onclick="openPopup('SE1732')" />
                                        </div> </a>


                                </form>
                                <div class ="question-list">

                                    <c:forEach items ="${requestScope.questionList}" var="question">
                                        <div class ="question-container">
                                            yess
                                        </div>

                                    </c:forEach>
                                    <div class ="question-container">
                                        Cau 1
                                    </div>
                                    <div class ="question-container">
                                        Cau 2
                                    </div>
                                    <div class ="question-container">
                                        Cau 3
                                    </div>
                                    <div class ="question-container">
                                        Cau 1
                                    </div>
                                    <div class ="question-container">
                                        Cau 2
                                    </div>
                                    <div class ="question-container">
                                        Cau 3
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>




            <!--pop up start----------------------------------------------------------------------------------------------->
            <!-- Blurred overlay -->
            <div id="overlay" class="overlay"></div>
            <div id="classEditPopup" class="overlay" onclick="closePopup()">
                <div class="popup-container" onclick="event.stopPropagation();">
                    <h2>Add Question</h2>
                    <button class ="popup-button" onclick="closePopup()">Close</button>


                    <div class="container-xl">
                        <div class="table-responsive">
                            <div class="table-wrapper">
                                <div class="table-title">
                                    <div class="row">
                                        <div class="col-sm-5">             
                                        </div>
                                        <div class="col-sm-7">                             
                                        </div>
                                    </div>
                                </div>

                                <div class="wrapper">
                                    <div class="tabs">
                                        <ul>
                                            <li class="active" data-list="tab_1">
                                                Add manually
                                            </li>
                                            <li data-list="tab_2">
                                                Add from Question bank</li>
                                            <li data-list="tab_3">
                                                Import Excel file</li>
                                        </ul>
                                    </div>

                                    <div class="content">
                                        <div class="tab_content tab_1">
                                            <form id="addManually" action="" method="post">
                                                  
                                            </form>


                                        </div>
                                        <div class="tab_content tab_2">
                                            It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                                        </div>
                                        <div class="tab_content tab_3">
                                            Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.
                                        </div>
                                        <div class="tab_content tab_4">
                                            making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics
                                        </div>
                                    </div>
                                </div>




                            </div>
                        </div>
                    </div>

                    <!--<input type="text" id="newClassName" placeholder="New Class Name">-->
                    <!--                <button class ="popup-button" onclick="editClassName()">Save</button>-->
                </div>
            </div>



        </section>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script>


                        function openPopup(className) {
                            const overlay = document.getElementById('classEditPopup');
//                const newClassNameInput = document.getElementById('newClassName');
//                newClassNameInput.value = className;
                            overlay.style.display = 'block';
                        }

                        // Function to close the pop-up
                        function closePopup() {
                            const overlay = document.getElementById('classEditPopup');
                            overlay.style.display = 'none';
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


                        $(document).ready(function () {
                            $(".content .tab_content").hide();
                            $(".content .tab_content:first-child").show();

                            $("ul li").click(function () {

                                $("ul li").removeClass("active");
                                $(this).addClass("active");

                                var current_tab = $(this).attr("data-list");
                                $(".content .tab_content").hide();
                                $("." + current_tab).show();
                            })
                        });

                        /*-------------------------------------------------------------------------------------------*/
//                        document.addEventListener("DOMContentLoaded", function () {
//                            const form = document.querySelector(".exam-form");
//                            form.addEventListener("submit", function (event) {
//                                const fromDateInput = document.getElementById("fromDate");
//                                const toDateInput = document.getElementById("toDate");
//
//                                const fromDate = new Date(fromDateInput.value);
//                                const toDate = new Date(toDateInput.value);
//                                const yDate = new Date(${sessionScope.sessionThisCourse.endDate}); // Replace '2023-12-31T23:59' with your specific xDate.
//                                const xDate = new Date(${sessionScope.sessionThisCourse.startDate});
//                                if (fromDate <= new Date() || toDate < new Date()) {
//                                    alert("From date must be in the future.");
//                                    event.preventDefault();
//                                } else if (toDate >= yDate || fromDate < xDate) {
//                                    alert("Date must in duration of Course");
//                                    event.preventDefault();
//                                }
//                            });
//                        });
                        /*-------------------------------------------------------------------------------------------*/
                        document.querySelector('form').addEventListener('submit', function (event) {
                            event.preventDefault();
                            var fromDate = new Date(document.getElementById('fromDate').value);
                            var toDate = new Date(document.getElementById('toDate').value);
                            var xDate = new Date(document.getElementById('startDate').value);
                            var yDate = new Date(document.getElementById('endDate').value);


                            if (fromDate <= new Date() || toDate <= new Date()) {
                                alert("Date must be in the future.");
                                return;
                            }
                            if(fromDate > toDate) {
                                alert("From Date must before To Date.");
                                return;
                            }

                            if (toDate >= yDate) {
                                alert("To date must be before " + xDate.toDateString());
                                return;
                            }
                            if(fromDate <= xDate) {
                                alert("Start Date must after: " + yDate.toDateString());
                                return;
                            }
                            

                            // If the validation passes, you can submit the form
                            this.submit();
                        });


        </script>
    </body>
</html>