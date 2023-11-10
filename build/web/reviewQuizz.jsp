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
        <title>JSP Page</title>
        <script type="text/javascript"></script>
        <link rel="stylesheet" href="assets/css/doquiz.css">
        <%@include file="Components/AllAccess.jsp"%>
    </head>
    <body class="sb-sidenav-toggled" onload="timerfunction()">
        <div class="wrapper">

            <div class="content" style="width: 100%">
                
                <div id="layoutSidenav_content">
                    <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                        <h1 class="fs-2 fw-bold">${requestScope.name}</h1>
                        <h2 class="fs-4">Total Score: ${requestScope.totalMark}</h2>                      
                        <h2 class="fs-4">Time: ${requestScope.totalTime}</h2>         
                        
                        <jsp:useBean id="choice" class="Dal.DAO" scope="request"></jsp:useBean>
                        <c:set var="examID" value="${requestScope.examID}"/>
                        <c:set var="StudentID" value="${requestScope.StudentID}" />
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form action="studentExamDetail" method="post" id="myForm">
                                    <input type="hidden" name="examID" value="${requestScope.examID}" />    
                                    <input type="hidden" name="StudentID" value="${requestScope.StudentID}" />
                                    <div class="row">
                                        <div class="col-12">
                                            <div>
                                                <ol type="1"> 
                                                    <c:set var="examID" value="${requestScope.examID}" />
                                                    <c:set var="StudentID" value="${requestScope.StudentID}" />
                                                    <c:forEach var="questionMap" items="${requestScope.questions}">
                                                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 10px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                                                            <li class="ms-3">
                                                                <span>${questionMap.content}</span> <span style="float: right"> Your point: ${(((choice.getPointEachQuestionOfStudentAnswer(questionMap.questionID)*questionMap.mark)/100)/choice.getExamScore(examID))*10}/${(questionMap.mark/choice.getExamScore(examID))*10} point</span>
                                                                <ol type="A" class="mt-3">
                                                                    <c:forEach items="${choice.getChoice(questionMap.questionID)}" var="c" varStatus="index">
                                                                        <c:if test="${c.answer == 1}">
                                                                            <c:if test="${c.choicePercentages > 0}">
                                                                                    <c:if test="${choice.userCheck(c.choices, examID, StudentID) == true}">
                                                                                        <li class="d-flex mb-1">
                                                                                            <input type="radio" checked="check" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 50px; height: 50px" disabled/>
                                                                                            <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" checked="check" readonly="" style="color: #fff;font-weight: bold;background-color: green"><span style="float: right">${c.choicePercentages}%</span></span>
                                                                                            <br/>
                                                                                        </li>
                                                                                    </c:if>
                                                                                    <c:if test="${choice.userCheck(c.choices, examID, StudentID) == false}">
                                                                                        <li class="d-flex mb-1">
                                                                                            <input type="radio" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 50px; height: 50px" disabled/>
                                                                                            <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" checked="check" readonly="" style="color: #fff;font-weight: bold;background-color: green"><span style="float: right">${c.choicePercentages}%</span></span>
                                                                                            <br/>
                                                                                        </li>
                                                                                    </c:if>
                                                                             </c:if>
                                                                             <c:if test="${c.choicePercentages == 0}">
                                                                                    <c:if test="${choice.userCheck(c.choices, examID, StudentID) == true}">
                                                                                       <li class="d-flex mb-1">
                                                                                           <input type="radio" checked="check" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 50px; height: 50px" disabled/>
                                                                                           <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" checked="check" readonly="" style="color: #fff;font-weight: bold;background-color: red"><span style="float: right">${c.choicePercentages}%</span></span>
                                                                                           <br/>
                                                                                       </li>
                                                                                   </c:if> 
                                                                                   <c:if test="${choice.userCheck(c.choices, examID, StudentID) == false}">
                                                                                       <li class="d-flex mb-1">
                                                                                           <input type="radio" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 50px; height: 50px" disabled/>
                                                                                           <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" checked="check" readonly=""><span style="float: right">${c.choicePercentages}%</span></span>
                                                                                           <br/>
                                                                                       </li>
                                                                                   </c:if>
                                                                             </c:if>   
                                                                        </c:if>
                                                                        <c:if test="${c.answer != 1}">
                                                                            <c:if test="${c.choicePercentages > 0}">
                                                                                <c:if test="${choice.userCheck(c.choices, examID, StudentID) == true}">
                                                                                    <li class="d-flex mb-1 row">
                                                                                        <input type="checkbox" checked="check" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 50px; height: 50px" disabled/>
                                                                                        <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" checked="check" readonly="" style="color: #fff;font-weight: bold;background-color: green"><span style="float: right">${c.choicePercentages}%</span></span>
                                                                                        
                                                                                        <br/>
                                                                                     </li>
                                                                                 </c:if>
                                                                                 <c:if test="${choice.userCheck(c.choices, examID, StudentID) == false}">
                                                                                    <li class="d-flex mb-1 row">
                                                                                        <input type="checkbox" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 50px; height: 50px" disabled/>
                                                                                        <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" checked="check" readonly="" style="color: #fff;font-weight: bold;background-color: green"><span style="float: right">${c.choicePercentages}%</span></span>
                                                                                        
                                                                                        <br/>
                                                                                     </li>
                                                                                 </c:if>
                                                                            </c:if>
                                                                            <c:if test="${c.choicePercentages == 0}">
                                                                                <c:if test="${choice.userCheck(c.choices, examID, StudentID) == true}">
                                                                                    <li class="d-flex mb-1 row">
                                                                                        <input type="checkbox" checked="check" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 50px; height: 50px" disabled/>
                                                                                        <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" checked="check" readonly="" style="color: #fff;font-weight: bold;background-color: red"><span style="float: right">${c.choicePercentages}%</span></span>
                                                                                        <br/>
                                                                                    </li>
                                                                                </c:if> 
                                                                                <c:if test="${choice.userCheck(c.choices, examID, StudentID) == false}">
                                                                                    <li class="d-flex mb-1 row">
                                                                                        <input type="checkbox" name="q${questionMap.questionID}" value="${c.choicePercentages}:${c.choices}" class="col-1" style="width: 50px; height: 50px" disabled/>
                                                                                        <span class="col-11"><input type="text" name="name"class="col-6 form-control ms-2" value="${c.content}" checked="check" readonly=""><span style="float: right">${c.choicePercentages}%</span></span>
                                                                                        <br/>
                                                                                    </li>
                                                                                </c:if>     
                                                                            </c:if>     
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </ol>
                                                            </li>
                                                        </div>
                                                        <br>
                                                    </c:forEach>
                                                </ol>                                               
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                </div>
                            </div>
                                        <div class="btn btn-primary"><a href="studentExamDetail?examID=${requestScope.examID}">return to exam detail</a></div>

                        </div>
                    </div>
                                        
                </div>
            </div>

        </div>





    </body>
</html>
