
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="Components/AllAccess.jsp"%>
        <!-- Custom scripts -->
        <script type="text/javascript"></script>
        <link rel="stylesheet" href="assets/css/doquiz.css">
    </head>
    <body class="sb-sidenav-toggled" onload="timerfunction()">
        <c:set var="hour" scope="session" value="${requestScope.hours}"/>
        <c:set var="minute" scope="session" value="${requestScope.minutes}"/>
        <c:set var="second" scope="session" value="${requestScope.seconds}"/>

        <header class="sb-nav-fixed"><nav class="sb-topnav navbar navbar-expand navbar-light py-3" style=" background-image: url(''); background-color: #FFC533; height: 70px">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <div class="row p-2 d-flex">
                        <div>Your Time: </div><p class="p-1 form-control text-center bg-light text-dark my-auto ms-2" style="width: 100px" <span id="time">${hour}:${minute}:${second}</span>><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path d="M12.5 7.25a.75.75 0 00-1.5 0v5.5c0 .27.144.518.378.651l3.5 2a.75.75 0 00.744-1.302L12.5 12.315V7.25z"></path><path fill-rule="evenodd" d="M12 1C5.925 1 1 5.925 1 12s4.925 11 11 11 11-4.925 11-11S18.075 1 12 1zM2.5 12a9.5 9.5 0 1119 0 9.5 9.5 0 01-19 0z"></path></svg></p>
                    </div>
                    <div class="row p-5 d-flex">
                        <div>Elapse Time: </div><p class="p-1 form-control text-center bg-light text-dark my-auto ms-2" style="width: 100px" <span id="elapsed-time"></span>><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path d="M12.5 7.25a.75.75 0 00-1.5 0v5.5c0 .27.144.518.378.651l3.5 2a.75.75 0 00.744-1.302L12.5 12.315V7.25z"></path><path fill-rule="evenodd" d="M12 1C5.925 1 1 5.925 1 12s4.925 11 11 11 11-4.925 11-11S18.075 1 12 1zM2.5 12a9.5 9.5 0 1119 0 9.5 9.5 0 01-19 0z"></path></svg></p>
                    </div>
                </div>




                </div>
            </nav>
        </header>
        <div class="col-lg-4">
            <!-- Side widgets-->

        </div>
        <jsp:useBean id="choice" class="Dal.DAO" scope="request"></jsp:useBean>

            <div id="layoutSidenav" class="mb-4">
                <div id="layoutSidenav_content">
                    <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                        <p class="fs-1 fw-bold">Exam Name: ${requestScope.name}</p>
<<<<<<< HEAD
                    <p class="fs-2 fw-light">Total Question: ${requestScope.questions.size()}</p>
=======
                        <p class="fs-2 fw-light">Total Question: ${requestScope.questions.size()}</p>
>>>>>>> 3830c74 (update lecturer/student)

                    <div class="container">
                        <div class="row">
                            <div class="col-lg-10">

                                <!-- Blog entries-->
                                <form action="studentExamDetail" method="post" id="myForm">
                                    <input type="hidden" name="examID" value="${requestScope.examID}" />
                                    <input type="hidden" name="subId" />
                                    <input type="hidden" name="attempt" />
                                    <input name="hourElapse" id="hourElapse" type="hidden"/>
                                    <input name="minuteElapse" id="minuteElapse" type="hidden"/>
                                    <input name="secondElapse" id="secondElapse" type="hidden"/>
                                    <div class="row">
                                        <div class="col-12">
                                            <div>
                                                <ol type="1">
                                                    <c:forEach var="questionMap" items="${requestScope.questions}">
                                                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 10px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                                                            <li class="ms-3">
                                                                <span>${questionMap.content}</span>
<<<<<<< HEAD
                                                                <c:forEach items="${choice.getChoice(questionMap.questionID)}" var="ch" varStatus="index1">
                                                                    <c:if test="${index1.index == 1}">
                                                                        <span style="color: red">        (Choose ${ch.answer} correct!)</span>
                                                                    </c:if>

                                                                </c:forEach>
                                                                <ol type="A" class="mt-3">
                                                                    <c:forEach items="${choice.getChoice(questionMap.questionID)}" var="c" varStatus="index">


                                                                        <!--                                                                            <li class="d-flex mb-1">
                                                                                                                                                            <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                                                                                        </li>-->
                                                                        <c:if test="${c.answer == 1}">
=======
                                                                <ol type="A" class="mt-3">
                                                                    <c:forEach items="${choice.getChoice(questionMap.questionID)}" var="c" varStatus="index">

                                                                        <!--                                                                            <li class="d-flex mb-1">
                                                                                                                                                            <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                                                                                        </li>-->
                                                                        <c:if test="${c.answer == 2}">
>>>>>>> 3830c74 (update lecturer/student)
                                                                            <li class="d-flex mb-1">
                                                                                <input type="radio" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 14px"/>
                                                                                <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                <br/>
<<<<<<< HEAD
                                                                            </li>
                                                                        </c:if>
                                                                        <c:if test="${c.answer != 1}">
=======
                                                                             </li>
                                                                        </c:if>
                                                                        <c:if test="${c.answer == 1}">
>>>>>>> 3830c74 (update lecturer/student)
                                                                            <li class="d-flex mb-1 row">
                                                                                <input type="checkbox" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 14px"/>
                                                                                <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                <br/>
<<<<<<< HEAD
                                                                            </li>
=======
                                                                             </li>
>>>>>>> 3830c74 (update lecturer/student)
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </ol>
                                                            </li>
                                                        </div>
                                                        <br>
                                                    </c:forEach>
                                                </ol>
                                                <div class="col-12">
                                                    <div class="d-flex justify-content-center">
                                                        <!--<button type="submit" class="btn btn-primary px-4 py-2 fw-bold">check</button>-->
                                                        <button type="submit" name="btnAction" value="submitQuizz" class="btn btn-primary px-4 py-2 fw-bold">Submit</button> 
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>



                        </div>

                    </div>
                </div>
            </div>
        </div>

        <script>
//            function timerfunction() {
//                var timeoutDuration = ${requestScope.seconds} * 1000 + ${requestScope.minutes} * 60 * 1000 + ${requestScope.hours} * 60 * 60 * 1000;
//                var hours = ${requestScope.hours}
//                var minute = ${requestScope.minutes};
//                minute--;
//                var sec = 59;
//                setInterval(function () {
//                    document.getElementById("time").innerHTML = hours + " : " minute + " : " + sec;
//                    sec--;
//                    if (sec == 00) {
//                        minute--;
//                        sec = 60;
//                        if (minute == 0) {
//                            hours--;
//                        }
//                    }
//                    if (hours == 0 && minute == 0 && sec == 1) {
//                        alert("Hết thời gian! Tự động submit hoặc thực hiện hành động khác.");
//                        document.getElementById("myForm").submit();
//                    }
//                }, 1000);
//            }
//            timeoutDuration()

            var countdownElement = document.getElementById("time");
            var elapsedTimeElement = document.getElementById("elapsed-time");
            var remainingTime = ${requestScope.seconds} + ${requestScope.minutes} * 60 + ${requestScope.hours} * 60 * 60;
            var elapsedTime = 0;
            function updateCountdown() {
                if (remainingTime > 0) {
                    remainingTime--;
                    elapsedTime++;
                    var hours = Math.floor(remainingTime / 3600).toString().padStart(2, '0');
                    var minutes = Math.floor((remainingTime % 3600) / 60).toString().padStart(2, '0');
                    var seconds = (remainingTime % 60).toString().padStart(2, '0');
                    var timeString = hours + ":" + minutes + ":" + seconds;
                    var elapseHour = Math.floor(elapsedTime / 3600).toString().padStart(2, '0');
                    var elapseMinute = Math.floor((elapsedTime % 3600) / 60).toString().padStart(2, '0');
                    var elapseSecond = (elapsedTime % 60).toString().padStart(2, '0');
                    var timeElapsed = elapseHour + " : " + elapseMinute + " : " + elapseSecond

                    document.getElementById("hourElapse").value = elapseHour;
                    document.getElementById("minuteElapse").value = elapseMinute;
                    document.getElementById("secondElapse").value = elapseSecond;


                    countdownElement.innerText = timeString;
                    elapsedTimeElement.innerText = timeElapsed;
                } else {
                    clearInterval(intervalID);
                    countdownElement.innerText = "Hết thời gian!";
                    document.getElementById("myForm").submit();
                }
            }

            // Cập nhật đồng hồ ban đầu
            updateCountdown();
            // Tạo đồng hồ đếm ngược và đồng hồ theo giây bằng setInterval
            var intervalID = setInterval(function () {
                updateCountdown();
            }, 1000); // Cập nhật đồng hồ mỗi giây (1000 miligiây)
        </script>



    </body>
</html>