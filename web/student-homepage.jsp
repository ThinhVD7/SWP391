<%@page import = "java.util.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Student</title>
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
        </style>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
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
                                <!--
                                <li>
                                    <a href="#">
                                        <i class="bx bx-bookmark"></i>
                                        <span class="title">Bookmarks</span>
                                    </a>
                                    <span class="tooltip">Bookmarks</span>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="bx bx-wallet-alt"></i>
                                        <span class="title">Wallet</span>
                                    </a>
                                    <span class="tooltip">Wallet</span>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="bx bxs-devices"></i>
                                        <span class="title">Devices</span>
                                    </a>
                                    <span class="tooltip">Devices</span>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="bx bx-cog"></i>
                                        <span class="title">Setting</span>
                                    </a>
                                    <span class="tooltip">Setting</span>
                                </li>-->
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




                
            <div class="container-fluid mt-3">
                <div class="row mt-5">
                    <c:forEach items="${requestScope.classes}" var="c">
                    <%--<c:forEach var="i" begin="1" end="30" step="1">--%>
                        <div class='col col-sm-4 mt-4'>
                            <div class="card">
                                <div class="card-body"> 
                                    <a href="studentExamList?classID=${c.classID}">
                                        <h4 class="card-title">${c.courseID}</h4>  
                                    </a>
                                        <h6 class="card-subtitle mb-2 text-muted">Class: ${c.className}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">Semester: ${requestScope.courseInfo[c.courseID].semester}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">From: ${courseInfo[c.courseID].startDate}&nbsp;To: ${courseInfo[c.courseID].endDate}</h6>  
                                    <a style ="position: absolute; bottom: 15px; right: 20px;" href="studentExamList?classID=${c.classID}" class="card-link">Enter course</a>
                                    <a href="#" class="card-link"></a>
                                </div>
                            </div>
                        </div>
                    <%--</c:forEach>--%>
                    </c:forEach>
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
