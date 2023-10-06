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
        <title>Manager</title>
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
            
            .home{
                position: relative;
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
                <!--                <li>
                                    <a href="#">
                                        <i class="bx bx-phone-call"></i>
                                        <span class="title">Calls</span>
                                    </a>
                                    <span class="tooltip">Calls</span>
                                </li>
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

            <div class="row">
                <div class="col-md-9">

                </div>
                <div class=" col-md-2 input-group" style="border: solid wheat; border-radius: 25px;background-color: #869791">
                    <a id="show" onclick="openPopUp()">  <div>
                            <i class="fa fa-plus-circle fa-xl" aria-hidden="true"></i> <p>Add Course</p>
                        </div> </a>
                </div>


                <!--                <div class="col-md-3 mw-20 mh-20 p-3" style="border: solid wheat;border-radius: 20px">
                                    <a href="#">  <div>
                                            <i class="fa fa-plus-circle fa-xl" aria-hidden="true"></i> <p>Add Course</p>
                                        </div> </a>
                                </div>-->
            </div>



            <div class="container-fluid mt-3">
                <div class="row mt-5">

                    <c:forEach items="${requestScope.course}" var="c">
                        <!--                        <div class='course-item col-md-4 col-md-6 col-lg-3 pt-3'>
                                                    <div class="card">
                                                        <div class="card-body"> 
                                                            <a href="">
                                                                <h4 class="card-title">${c.courseName}</h4>  
                                                            </a>
                                                            <h7 class="card-subtitle mb-2 text-muted">Semester:${c.semester}</h7>
                                                            <h6 class="card-subtitle mb-2 text-muted">From:${c.startDate} To: ${c.endDate}</h6>
                                                            <a href="#" class="card-link">Enter course</a>
                                                            <a href="#" class="card-link"></a>
                                                        </div>
                                                    </div>
                                                </div>-->
                        <div class="col col-sm-4 mt-4">
                            <div class="card">
                                <div class="card-body">


                                    <a style="text-decoration: none;"  href="#">   <h5 class="card-title pt-2">${c.courseID}</h5> </a>
                                    <h6 class="card-subtitle mb-2 text-muted">Name: ${c.courseName}</h6>
                                    <span style="float: right"> <a onclick="openEdit(11,22,3,4,5)"><i class="fa-solid fa-pen-to-square" style="color: #0be50e;"></i> </span>
                                    <h6 class="card-subtitle mb-2 text-muted">Semester: ${c.semester}</h6>
                                    <h6 class="card-subtitle mb-2 text-muted">From:${c.startDate} To: ${c.endDate}</h6> 
                                    <span style="float: right"> <a onclick="openDelete()"><i  class="fa fa-times-circle fa-xl" aria-hidden="true" style="color:red;text-align: end"></i></a> </span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </div>
            <div class="addCourse" id="addCourse" style="display: none;">
                <div class="addCourseContent">
                    <h3 class="addCourseTitle" id="titleCourse">Add course</h3> 
                    <div class="form-field d-flex align-items-center">
                        <!--<span class="far fa-user"></span>-->
                        <input type="text" name="courseId" id="courseId" placeholder="course-ID" required="">
                    </div>
                    <div class="form-field d-flex align-items-center">
                        <!--<span class="far fa-user"></span>-->
                        <input type="text" name="courseName" id="courseName" placeholder="Course Name" required="">
                    </div>
                    <div class="form-field d-flex align-items-center">
                        <!--<span class="far fa-user"></span>-->
                        <input type="text" name="semester" id="semseter" placeholder="Semester" required="">
                    </div>
                    <div class="form-field d-flex align-items-center">
                        <!--<span class="far fa-user"></span>-->
                        <p>Start Day</p>
                        <input type="date" name="courseId" id="courseId" placeholder="course-ID" required="">
                    </div>
                    <div class="form-field d-flex align-items-center">
                        <!--<span class="far fa-user"></span>-->
                        <p>End Day</p>
                        <input type="date" name="courseId" id="courseId" placeholder="course-ID" required="">
                    </div>
                    <button class="closePopUp" type="submit" >Submit</button>
                    <button class="closePopUp" onclick="closePopUp1()">Close</button>
                </div>
            </div>
            <div class="addCourse" id="deleterCouse" style="display: none;">
                <div class="addCourseContent">
                    <h3 class="addCourseTitle" id="titleCourse">Delete course</h3> 
                    <h6 class="addCourseTitle1" id="titleCourse1">You may want to delete ?</h6>
                    <button class="closePopUp1" type="submit" >Yes</button>
                    <button class="closePopUp1" type="submit" >No</button>
                    <button class="closePopUp" onclick="closePopUp()">Close</button>
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
            
            function openPopUp(){
                const divPopUp = document.querySelector("#addCourse");
                document.getElementById('titleCourse').innerHTML = 'Add Course';
                divPopUp.style.display = 'block';
                document.getElementById("courseId").value = '';
                document.getElementById("courseName").value = '';
                document.getElementById("semseter").value = '';
            }
            
            function closePopUp(){
                const divPopUp = document.querySelector("#addCourse");
                divPopUp.style.display = 'none';
            }

            function openEdit(id, name, semester, start, end){
                 const divPopUp = document.querySelector("#addCourse");
                const showBtn = document.querySelector("show");
                divPopUp.style.display = 'block';
                document.getElementById('titleCourse').innerHTML = 'Edit Course';
                document.getElementById("courseId").value = id;
                document.getElementById("courseName").value = name;
                document.getElementById("semseter").value = semester;
                
            }
            function closePopUp1(){
                 const divPopUp = document.querySelector("#addCourse");
                divPopUp.style.display = 'none';
            }
            function openDelete(){
                const divPopUp = document.querySelector("#deleterCouse");
                const showBtn = document.querySelector("show");
                divPopUp.style.display = 'block';
                
            }
            function closePopUp(){
                 const divPopUp = document.querySelector("#deleterCouse");
                divPopUp.style.display = 'none';
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
