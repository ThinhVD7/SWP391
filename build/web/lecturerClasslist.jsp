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
            .class-list {
                            /*display: flex;*/

            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
/*            margin-left: 200px;
            margin-right: 200px;*/
            margin-top: 30px;
            margin: 20px auto;
            width:55%;
/*            align-self: center;
            justify-content: center;*/
            overflow: hidden;
            }
            
            .left-div 
            {
                flex: 1;
                padding: 10px;
                border-radius: 10px;
                /*box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);*/
                border: 2px solid #ccc;
                margin-right: 10px;
/*                height: 540px;
                overflow: auto;*/
                /*width: 50%;*/
                /*display:block;*/
            }
            .left-div{
                background-color: #f5f5f5;
            }
            .right-div 
            {
                /*flex: 1;*/
                background-color: #299be4;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
                color: #fff;
                padding: 15px;
                border-radius: 10px;
                /*border: 2px solid #ccc;*/
                position: relative;
                /*margin-right: 10px;*/
                overflow: hidden;
                /*display:block;*/
                display: flex;
                justify-content: center; /* Center horizontally */
                align-items: center; /* Center vertically */
/*                height: 100vh;*/
                
            }

            .class-container {
                margin-bottom: 20px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);
                /*width: 50%;*/
                margin-bottom: 20px;
                padding: 15px;
                /*display: block;*/
            }
            
            .class-container:hover{
                background-color: #f5f5f5;
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

            
<!--            <div class="class-list">
                <c:forEach items="${requestScope.classList}" var="c">
                    <div class="class-containder">
                        <a style="text-decoration: none;"  href="#"> 
                        <h3>${c.className}</h3>
                    </div>
                    </div>
                </c:forEach>
            </div>-->




        <div class="main">
            <div>
                <a style="font-size: 36px; margin-bottom: 10px;">
                    ${sessionScope.sessionPageTitle}
            </div>
            <div>
                <a href="home" style ="padding: 5px;" >Home</a> / <a style ="padding: 5px;" href="#">${sessionScope.sessionThisCourse.courseID}</a>
            </div>
            <div>
                <a style ="padding:5px;"><h2>Your classes of ${sessionScope.sessionPageTitle}</h2></a>
            </div>
            <div class="class-list">
                <c:forEach items="${requestScope.classList}" var="clas">
                    <div class ="class-container">
                    <div style="font-size: 36px; margin-bottom: 10px;">
                    ${clas.className}
                    </div>
                    <div class="class-detail">
                        <div class="left-div">
                                <h5>Exam Created: ${requestScope.examNumber[clas.classID]}</h5>
                                <h5>Number of Students: ${requestScope.studentNumber[clas.classID]}</h5>
                        </div>
                        <div class="right-div">
                    <form action="lecturerExamList"> -->
                        <button class="edit-exam-button" name="classID" value=${clas.classID}>Enter</button>
                    </form>
                        </div>
                    </div>
                </div>
                </c:forEach>
<!--                <div class ="class-container">
                    <div style="font-size: 36px; margin-bottom: 10px;">
                    SE1732
                    </div>
                    <div class="class-detail">
                        <div class="left-div">
                                <h5>Exam Created: 6</h5>
                                <h5>Number of Students: 30</h5>
                        </div>
                        <div class="right-div">
                             <form action="action"> 
                    <button class="edit-exam-button">Enter</button>
                </form>
                        </div>
                    </div>
                </div>-->
<!--                <div class ="class-container">
                    <div style="font-size: 36px; margin-bottom: 10px;">
                    SE1744
                    </div>
                    <div class="class-detail">
                        <div class="left-div">
                                <h5>Exam Created: 3</h5>
                                <h5>Number of Students: 30</h5>
                        </div>
                        <div class="right-div">
                             <form action="action"> 
                    <button class="edit-exam-button">Enter</button>
                </form>
                        </div>
                    </div>
                </div>-->
<!--                <div class ="class-container">
                    <div style="font-size: 36px; margin-bottom: 10px;">
                    SE1802
                    </div>
                    <div class="class-detail">
                        <div class="left-div">
                                <h5>Exam Created: 2</h5>
                                <h5>Number of Students: 30</h5>
                        </div>
                        <div class="right-div">
                             <form action="action"> 
                    <button class="edit-exam-button">Enter</button>
                </form>
                        </div>
                    </div>
                </div>-->
                
                
            </div>
                
           

            
        </div>


                </div>
            </div>








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
