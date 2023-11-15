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
                bottom: 20px;
                left: 20px;
            }
            .edit-exam-button-bottom:hover{
                background-color: #0073e6;
            }
            .stat-exam-button
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
                bottom: 20px;
                left: 190px;

            }
            .stat-exam-button:hover{
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
    </head>
    <body>

        <!--side bar------------------------------------------------------------------------------------------------------->        
        <section class="sidebar">
            <div class="nav-header">
                <p class="logo">Quiz Practice</p>
                <i class="bx bx-menu btn-menu"></i>
            </div>
            <ul class="nav-links">




<!--                <li>
                    <i class="bx bx-search search-btn"></i>
                    <input type="text" placeholder="Search" />
                    <span class="tooltip">Search</span>
                </li>-->
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

        <!--end of side bar------------------------------------------------------------------------------------------->


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
                    <a style ="padding: 5px;" >${sessionScope.sessionThisExam.examName}</a>
                </div>
                <div>
                    <a style ="padding:5px;">
                </div>
                <div>

                    <h1>${sessionScope.sessionThisExam.examName} </h1>     
                    <div style="overflow:auto; border-top: 3px solid #000;padding-top: 10px; margin-left: 10px">
                        <p>${sessionScope.sessionThisExam.examDetail}</p>           
                    </div>
                </div>
                <div class="exam-detail">
                    <p>Start Date: ${requestScope.startDate}</p>
                    <br>
                    <p>End Date: ${requestScope.endDate}</p>
                    <br>
                    <p>Time limit: ${requestScope.timeLimit} </p>
                    <br>
                    <p>Review Permission: ${sessionScope.sessionThisExam.permission==1?"Absolutely":"Not Permitted"} </p>
                    <br>
                    <!--                <form action="action">
                                        <button class="edit-exam-button">Edit Exam</button> 
                                    </form>-->
                    <c:if test="${requestScope.notAllowToEdit1==null}">
                        <c:if test="${sessionScope.lecturerX!= null}">

                            <a href="lecturerEditExam?tId=${sessionThisExam.examID}">
                                <button class="edit-exam-button" ${requestScope.notAllowToEdit!=null?"hidden":""}>Edit Exam</button> 
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.lecturerX== null}">
                               <p class="text-warning">Can not access</p>

                        </c:if>
                    </c:if>
                    
                    <!--                &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;-->
                </div>
                <div><br></div>
                <div style="overflow:auto; border-top: 3px solid #000;padding-top: 10px; margin-left: 10px; margin-right: 10px;">
                </div>
                <div style= "display:none">
                    <form action="lecturerStatisticExam">
                        <input value="${sessionThisExam.examID}" type="hidden" name="examId"/>
                        <button class="edit-exam-button-bottom" type="submit">Preview Exam</button>
                    </form>
                    <!--                    <form action="lecturer">
                                            <button class="edit-exam-button-bottom" onclick ="alert('Button clicked!')"${2==1?"":"hidden"}>Review Exam</button>
                                        </form>-->
                </div>
                <div>
                    <form action="lecturerStatisticExam">
                        <input value="${sessionThisExam.examID}" type="hidden" name="examId"/>
                        <button class="stat-exam-button" ${requestScope.statisticNotAllow!=null?"hidden":""} type="submit">View Statistics</button>
                    </form>
                </div>
                <br><br>
            </div>

            <!--end of main block------------------------------------------------------------------------------------------------->

        </section>

        <script>
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
        </script>
    </body>
</html>
