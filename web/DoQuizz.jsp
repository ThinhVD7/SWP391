
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Do Exam</title>
        <%@include file="Components/AllAccess.jsp"%>
        <!-- Custom scripts -->
        <script type="text/javascript"></script>
        <link rel="stylesheet" href="assets/css/doquiz.css">
        <style>
            .contentExam{
                display: flex;
                width: 100%;
            }
            .questionBtn{
                padding: 8px 12px;
                margin: 2px;
                background-color: #ccc;
                cursor: pointer;
                transition: .4s;
                border-radius: 4px;
                position: relative;
                border: 4px solid transparent;
                width: 65px;
                height: 55px;
            }
            .flag{
                margin: 0 10px;
                padding: 4px 12px;
                border: 1px solid black;
                cursor: pointer;
                border-radius: 4px;
            }
            .flagIcon{
                color: red;
                opacity: 0;
            }
        </style>

    </head>
    <body class="sb-sidenav-toggled" onload="timerfunction()">
        <c:set var="hour" scope="session" value="${requestScope.hours}"/>
        <c:set var="minute" scope="session" value="${requestScope.minutes}"/>
        <c:set var="second" scope="session" value="${requestScope.seconds}"/>

        <header class="sb-nav-fixed"><nav class="sb-topnav navbar navbar-expand navbar-light py-3" style=" background-image: url(''); background-color: #FFC533; height: 70px">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <div class="row p-2 d-flex" style="font-weight:bold">
                        <div>Total Time Left: </div><p class="p-1 form-control text-center bg-light text-dark my-auto ms-2" style="width: 100px" <span style="font-weight:bold"id="time">${hour}:${minute}:${second}</span>><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path d="M12.5 7.25a.75.75 0 00-1.5 0v5.5c0 .27.144.518.378.651l3.5 2a.75.75 0 00.744-1.302L12.5 12.315V7.25z"></path><path fill-rule="evenodd" d="M12 1C5.925 1 1 5.925 1 12s4.925 11 11 11 11-4.925 11-11S18.075 1 12 1zM2.5 12a9.5 9.5 0 1119 0 9.5 9.5 0 01-19 0z"></path></svg></p>
                    </div>
                    <div class="row p-5 d-flex">
                        <div hidden>Elapse Time: </div><p hidden class="p-1 form-control text-center bg-light text-dark my-auto ms-2" style="width: 100px" <span id="elapsed-time"></span>><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path d="M12.5 7.25a.75.75 0 00-1.5 0v5.5c0 .27.144.518.378.651l3.5 2a.75.75 0 00.744-1.302L12.5 12.315V7.25z"></path><path fill-rule="evenodd" d="M12 1C5.925 1 1 5.925 1 12s4.925 11 11 11 11-4.925 11-11S18.075 1 12 1zM2.5 12a9.5 9.5 0 1119 0 9.5 9.5 0 01-19 0z"></path></svg></p>
                    </div>
                </div>




                </div>
            </nav>
        </header>
        <div class="col-lg-4">
            <!-- Side widgets-->

        </div>
        <jsp:useBean id="choice" class="Dal.DAO" scope="request"></jsp:useBean>
        <c:set var="displayQuestion" value="${requestScope.defaultQuestion}"/>
        <c:forEach var="questionMap" items="${requestScope.questions}" varStatus="index">
            <input name="flag${questionMap.answer}" id="flag${questionMap.answer}" value="false" type="hidden"  />
        </c:forEach>
        <input id="oldQuestion" value="${displayQuestion}" type="hidden" />

        <div id="layoutSidenav" class="mb-4">
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <p class="fs-1 fw-bold" style="font-size:30px; font-weight:600;color:black ">Exam Name: ${requestScope.name}</p><br>
                    <b class="fs-2 fw-light"style="font-size:20px">Total Question: ${requestScope.questions.size()}</b><br>

                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">

                                <!-- Blog entries-->
                                <form action="studentExamDetail" method="post" id="myForm">
                                    <input type="hidden" name="examID" value="${requestScope.examID}" />
                                    <input type="hidden" name="subId" />
                                    <input type="hidden" name="attempt" />
                                    <input name="hourElapse" id="hourElapse" type="hidden"/>
                                    <input name="minuteElapse" id="minuteElapse" type="hidden"/>
                                    <input name="secondElapse" id="secondElapse" type="hidden"/>
                                    <div class="row contentExam">
                                        <div class="col-8">
                                            <div>
                                                <ol type="1">
                                                    <c:forEach var="questionMap" items="${requestScope.questions}" varStatus="index">
                                                        <c:if test="${questionMap.answer == requestScope.defaultQuestion }">
                                                            <div id="${questionMap.answer}" >
                                                                <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded"  
                                                                     style="padding: 10px 10px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                                                                        <span style="font-size:18px; font-weight:600">Question ${index.index+1}:</span>
                                                                        <span style="font-size:18px">${questionMap.content} </span>
                                                                        <div style="float: right">
                                                                            <c:forEach items="${choice.getChoice(questionMap.questionID)}" var="ch" varStatus="index1">
                                                                                <c:if test="${index1.index == 1}">
                                                                                    <span style="color: red">        (Choose ${ch.answer} only!)</span>
                                                                                </c:if>

                                                                            </c:forEach>
                                                                            <span class="flag" onclick="setFlag(${questionMap.answer})" ><i class="fa-solid fa-flag" style="color: red"></i></span>   
                                                                        </div>

                                                                        <ol type="A" class="mt-3">
                                                                            <c:forEach items="${choice.getChoice(questionMap.questionID)}" var="c" varStatus="index">


                                                                                <!--                                                                            <li class="d-flex mb-1">
                                                                                                                                                                    <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                                                                                                </li>-->
                                                                                <c:if test="${c.answer == 1}">
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="radio" name="q${questionMap.questionID}" id="question${questionMap.questionID}"
                                                                                               onchange="changeCheck(${questionMap.answer})" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 14px"/>
                                                                                        <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                        <br/>
                                                                                    </li>
                                                                                </c:if>
                                                                                <c:if test="${c.answer != 1}">
                                                                                    <li class="d-flex mb-1 row">
                                                                                        <input type="checkbox" name="q${questionMap.questionID}" id="question${questionMap.questionID}"
                                                                                               onchange="changeCheck(${questionMap.answer})" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 14px"/>
                                                                                        <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                        <br/>
                                                                                    </li>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </ol>
                                                                    
                                                                </div>
                                                                <br>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${questionMap.answer != requestScope.defaultQuestion }">
                                                            <div id="${questionMap.answer}" style="display: none;">
                                                                <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded"  
                                                                     style="padding: 10px 10px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">

                                                                        <span>Question ${index.index+1}: </span>
                                                                        <span>${questionMap.content}</span>
                                                                        <div style="float: right">
                                                                            <c:forEach items="${choice.getChoice(questionMap.questionID)}" var="ch" varStatus="index1">

                                                                                <c:if test="${index1.index == 1}">
                                                                                    <span style="color: red">        (Choose ${ch.answer} correct!)</span>
                                                                                    <span class="flag" onclick="setFlag(${questionMap.answer})"><i class="fa-solid fa-flag" style="color: red"></i></span>
                                                                                </c:if>

                                                                            </c:forEach>
                                                                        </div>          
                                                                        <ol type="A" class="mt-3">
                                                                            <c:forEach items="${choice.getChoice(questionMap.questionID)}" var="c" varStatus="index">


                                                                                <!--                                                                            <li class="d-flex mb-1">
                                                                                                                                                                    <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                                                                                                </li>-->
                                                                                <c:if test="${c.answer == 1}">
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="radio" name="q${questionMap.questionID}" id="question${questionMap.questionID}"
                                                                                               onchange="changeCheck(${questionMap.answer})" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 14px"/>
                                                                                        <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                        <br/>
                                                                                    </li>
                                                                                </c:if>
                                                                                <c:if test="${c.answer != 1}">
                                                                                    <li class="d-flex mb-1 row">
                                                                                        <input type="checkbox" name="q${questionMap.questionID}" id="question${questionMap.questionID}"
                                                                                               onchange="changeCheck(${questionMap.answer})" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 14px"/>
                                                                                        <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" readonly></span>
                                                                                        <br/>
                                                                                    </li>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </ol>
                                                                </div>
                                                                <br>
                                                            </div>
                                                        </c:if>
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
                                        <div class="checkQuetion col-lg-4">
                                            <h4>Quiz Navigation</h4>
                                            <c:forEach var="questionMap" items="${requestScope.questions}" varStatus="i">
                                                <button type="button" class="questionBtn" onclick="changeQuestion(${questionMap.answer})" id="q${i.index+1}" >${i.index+1}                                                 
                                                    <i id="flagflag${questionMap.answer}"  class="flagIcon" ><i class="fa-solid fa-flag"></i></i>
                                                </button>
                                            </c:forEach>

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
            function setFlag(id) {
                var num = id;
                var string = `flag` + num.toString();
                var string2 = 'flagflag' + num.toString();
                var flag = document.getElementById(string).value;
                if (flag === "false") {
                    document.getElementById(string).value = true;
                } else {
                    document.getElementById(string).value = false;
                }
                if (flag === "true") {
                    document.getElementById(string2).style.opacity = 0;
                } else {
                    document.getElementById(string2).style.opacity = 1;
                }
            }

            function changeQuestion(id) {
                var oldQuestion = document.getElementById("oldQuestion").value;
                var num = id;
                var string = `q` + num.toString();
                var oldBtn = `q` + oldQuestion.toString()

                document.getElementById(string).style.border = '4px solid #000';
                document.getElementById(oldBtn).style.border = '4px solid transparent';

                if (parseInt(id) !== parseInt(oldQuestion)) {
                    var idQuestion = id;
                    const currentQuestion = document.getElementById(idQuestion)
                    currentQuestion.style.display = 'block';

                    const hiddenQuestion = document.getElementById(oldQuestion);
                    hiddenQuestion.style.display = 'none';
                    document.getElementById("oldQuestion").value = id;
                }
            }

            function changeCheck(id) {
                var num = id;
                var string = `q` + num.toString();
                var string2 = `question` + num.toString();
                const checkbox = document.getElementById(string);
                var inputSelector = 'input[name="' + string.toString() + '"]';
                if (checkbox.type !== "checkbox") {
                    document.getElementById(string).style.backgroundColor = "blue";
                    document.getElementById(string).style.color = "white";
                } else {
                    var checkboxes = document.querySelectorAll(inputSelector);
                    var atLeastOneChecked = false;
                    checkboxes.forEach(function (checkbox) {
                        if (checkbox.checked) {
                            atLeastOneChecked = true;
                        } 
                    });
                    if (atLeastOneChecked) {
                        document.getElementById(string).style.backgroundColor = "blue";
                        document.getElementById(string).style.color = "white";
                    } else {
                        document.getElementById(string).style.backgroundColor = "#ccc";
                        document.getElementById(string).style.color = "black";
                    }
                }
            }

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