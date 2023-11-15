<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            /* Style for the pop-up */
            .popup {
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
            
            .edit-exam-button
            {
                background-color: #00c5db;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                margin-top: 10px;
                /*box-shadow: 0 4px 6px rgba(0, 0, 0, 0.9);*/
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

                <!--                <li>
                                    <i class="bx bx-search search-btn"></i>
                                    <input type="text" placeholder="Search" name="searchInput"/>
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
        <section class="home">
            <div class="row pt-2">
                <div class="col-md-9 ml-2">
                    <div class="input-group rounded">
                        <form action="managerSearch" method="doGet">
                            <input class="nosubmit" type="search" name="searchInput">
                            <button class ="edit-exam-button"type="submit">Search</button>
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


            </div>



            <div class="container-fluid mt-3">
                <div class="row mt-5">

                    <c:forEach items="${requestScope.course}" var="c" varStatus="loop">
                        <div class="col col-sm-4 mt-4">
                            <div class="card">
                                <div class="card-body">
                                    <form >
                                        <a style="text-decoration: none;"  href="managerViewClassList?courseID=${c.courseID}">   <h5 class="card-title pt-2">${c.courseID}</h5> </a>
                                        <h6 class="card-subtitle mb-2 text-muted">Name: ${c.courseName}</h6>
                                        <span style="float: right"> <a onclick="openEdit('${c.courseID}', '${c.courseName}', '${c.semester}', '4${c.startDate}', '${c.endDate}')"><i class="fa-solid fa-pen-to-square" style="color: #0be50e;"></i></a> </span>
                                        <h6 class="card-subtitle mb-2 text-muted">Semester: ${c.semester}</h6>
                                        <h6 class="card-subtitle mb-2 text-muted">From:${c.startDate} To: ${c.endDate}</h6> 
                                        <span style="float: right" ${requestScope.deleteNotAllowMap[c.courseID]?"hidden":""}> <a onclick="closeEdit()">
                                                <!--<i onclick="delCousera('${c.courseID}')" onclick="openDelete()" class="fa fa-times-circle fa-xl" aria-hidden="true" style="color:red;text-align: end"></i></a> </span>-->
                                                <i onclick="delCousera('${c.courseID}')" class="fa fa-times-circle fa-xl" aria-hidden="true" style="color:red;text-align: end"></i></a> </span>
                                    </form>
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
                        <input type="date" name="startDay" id="startDay" max="2099-01-01" required="">
                    </div>
                    <div class="form-field d-flex align-items-center">
                        <!--<span class="far fa-user"></span>-->
                        <p>End Day</p>
                        <input type="date" name="endDay" id="endDay"  max="2099-01-01" required="" >
                    </div>
                    <button class="closePopUp" onclick="getInfoAddCourse()" type="submit" >Submit</button>
                    <button class="closePopUp" onclick="closePopUp()">Close</button>
                </div>
            </div>

        </section>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <script>
                        var contextPath = "<%= request.getContextPath()%>";

                        if ('${sessionScope.logPrint}' === '1') {
                            alert("Id Course này đã tồn tại");
                        }
                        delMess();
                        const btn_menu = document.querySelector(".btn-menu");
                        const side_bar = document.querySelector(".sidebar");
                        var idCourse;
                        var status;

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
                        function delMess() {
                            console.log('da chay ham quan trong nay');
                            $.ajax({
                                url: contextPath + "/ressetMess",
                                type: "POST",
                                data: {
                                    mess: 1
                                },
                                success: function (response) {
                                    console.log('da thuc hien thanh cong ham xoa mess');
                                },
                                error: function (xhr, status, error) {
                                    console.log('eo thuc hien dc ham xoa mess');
                                }
                            });
                        }

//                        function openDelete(courseId) {
//                            const divPopUp = document.querySelector("#deleteCourse");
//                            const showBtn = document.querySelector("show");
//                            
//                            divPopUp.style.display = 'block';
//                        }



                        function openPopUp() {
                            const divPopUp = document.querySelector("#addCourse");
                            document.getElementById('titleCourse').innerHTML = 'Add Course';
                            divPopUp.style.display = 'block';
                            document.getElementById("courseId").value = '';
                            document.getElementById("courseName").value = '';
                            document.getElementById("semseter").value = '';
                            status = 1;
                        }

                        function formatDate(date) {
                            const d = new Date(date);
                            const month = '' + (d.getMonth() + 1);
                            const day = '' + d.getDate();
                            const year = d.getFullYear();

                            return [year, month.padStart(2, '0'), day.padStart(2, '0')].join('/');
                        }

                        function getInfoAddCourse() {


                            let courseIdMain = document.getElementById('courseId').value;
                            let courseId = document.getElementById('courseId').value;
                            let courseName = document.getElementById("courseName").value;
                            let semseter = document.getElementById("semseter").value;
                            var startDay = document.getElementById("startDay").value;
                            var endDay = document.getElementById("endDay").value;
                            var today = new Date();
                            if (new Date(startDay).getFullYear() > 2099 || new Date(endDay).getFullYear() > 2099) {
                                alert("Please enter a date within the year 2099 or earlier.");
                                return;
                            }
                            let dateStr = "NaN/NaN/NaN";

                            console.log("Start day la:");

                            if (isNaN(courseId) === false) {
                                alert("Bạn chưa nhập course_Id ");
                            } else if (isNaN(courseName) === false) {
                                alert("Bạn chưa nhập course Name ");
                            } else if (isNaN(semseter) === false) {
                                alert("Bạn chưa nhập semseter ");
                            } else if (isNaN(startDay) === false) {
                                alert("Bạn chưa nhập ngày  bắt đầu");
                            } else if (isNaN(endDay) === false) {
                                alert("Bạn chưa nhập ngày kết thúc");

                            } else if (isNaN(endDay)) {
                                if (formatDate(startDay) < formatDate(today)) {
                                    alert("Ngày bắt đầu không hợp lệ");
                                } else if (formatDate(endDay) < formatDate(startDay)) {
                                    alert("Ngày kết thúc lớn hơn ngày bắt đầu");
                                } else {
                                    if (status == 1) {
                                        interacCousera(courseIdMain, courseId, courseName, semseter, formatDate(startDay), formatDate(endDay), 'add');
                                    } else if (status == 2) {
                                        interacCousera(idCourse, courseId, courseName, semseter, formatDate(startDay), formatDate(endDay), 'update');
                                    }
                                }
                            }
                        }

                        var contextPath = "<%= request.getContextPath()%>";

                        function interacCousera(courseIdMain, courseId, courseName, semseter, startDay, endDay, action) {
                            $.ajax({
                                url: contextPath + "/managerHome",
                                type: "POST",
                                data: {
                                    courseIdMain: courseIdMain,
                                    courseId: courseId,
                                    courseName: courseName,
                                    semseter: semseter,
                                    startDay: startDay,
                                    endDay: endDay,
                                    action: action
                                },
                                success: function (response) {
                                    window.location.reload();
                                },
                                error: function (xhr, status, error) {

                                }
                            });
                        }

                        function delCousera(courseId) {
                            var dk = confirm('Bạn có muốn xóa không ?');

                            if (dk) {
                                $.ajax({
                                    url: contextPath + "/managerDeleteCourse",
                                    type: "POST",
                                    data: {
                                        courseId: courseId
                                    },
                                    success: function (response) {
                                        window.location.reload();
                                    },
                                    error: function (xhr, status, error) {

                                    }
                                });
                            } else {

                            }

                        }


                        function closePopUp() {
                            const divPopUp = document.querySelector("#addCourse");
                            divPopUp.style.display = 'none';
                        }
                        function closeDeletePopUp(courseId) {
                            const divPopUp = document.querySelector("#deleteCourse");
                            divPopUp.style.display = 'none';
                        }

                        function openEdit(id, name, semester, start, end) {
                            const divPopUp = document.querySelector("#addCourse");
                            const showBtn = document.querySelector("show");
                            idCourse = id;
                            divPopUp.style.display = 'block';
                            document.getElementById('titleCourse').innerHTML = 'Edit Course';
                            document.getElementById("courseId").value = id;
                            document.getElementById("courseName").value = name;
                            document.getElementById("semseter").value = semester;
                            status = 2;
                        }


                        function closeEdit() {
                            const divPopUp = document.querySelector("#addCourse");
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