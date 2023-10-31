
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       
    </head>
    <body class="sb-sidenav-toggled" onload="timerfunction()">
                <div id="layoutSidenav_content">
                    <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                        <h1 class="fs-2 fw-bold">${requestScope.QUIZZ.title}</h1>
                        <h2 class="fs-4">Your Point: ${requestScope.QUIZZ_POINT.point} </h2>                      
                                
                                
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">

                                    <!-- Blog entries-->
                                    <form action="doQuiz" method="post" id="myForm">
                                        <input type="hidden" name="quizzId" value="${requestScope.quizzId}" />
                                        <div class="row">
                                            <div class="col-12">
                                                <div>
                                                    <ol type="1">
                                                        <c:forEach var="questionMap" items="${sessionScope.REVIEW_QUIZZ}">
                                                            <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 10px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                                                                <li class="ms-3">
                                                                    <span>${questionMap.getContent()} </span>
                                                                    <input type="hidden" name="questionId" value="${questionMap.getQuestionId()}">
                                                                    <ol type="A">
                                                                        <c:if test="${questionMap.isIsMultipleChoice() == true}">  <!-- Là Multiple choice -->
                                                                            <c:forEach var="ans" items="${questionMap.getAnswer()}">
                                                                                <c:if test="${ans.correct == true}"> <!-- Là câu đúng -->
                                                                                    <c:if test="${ans.userCheck == true}"> <!-- Được ng dùng chọn -->
                                                                                        <li class="d-flex mb-1">
                                                                                            <input type="checkbox" checked="check" name="answer_${questionMap.questionId}" value="${ans.answerId}" class="col-1" style="width: 10px" disabled>
                                                                                            <span class="col-11"><input class="bg-success col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly=""></span>
                                                                                        </li>   
                                                                                    </c:if>
                                                                                    <c:if test="${ans.userCheck != true}"> <!-- Không được ng dùng chọn -->
                                                                                        <li class="d-flex mb-1">
                                                                                            <input type="checkbox" name="answer_${questionMap.questionId}" value="${ans.answerId}" class="col-1" style="width: 10px" disabled>
                                                                                            <span class="col-11"><input class="bg-primary  col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly=""></span>
                                                                                        </li>    
                                                                                    </c:if>
                                                                                </c:if>
                                                                                <c:if test="${ans.correct != true}"><!-- Là câu sai -->
                                                                                    <c:if test="${ans.userCheck == true}"><!-- Được ng dùng chọn -->
                                                                                        <li class="d-flex mb-1">
                                                                                            <input type="checkbox" checked="check" name="answer_${questionMap.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                            <span class="col-11"><input class="bg-danger col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly=""></span>
                                                                                        </li>   
                                                                                    </c:if>
                                                                                    <c:if test="${ans.userCheck != true}"><!-- Không được ng dùng chọn -->
                                                                                        <li class="d-flex mb-1">
                                                                                            <input type="checkbox" name="answer_${questionMap.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                            <span class="col-11"><input class=" col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly=""></span>
                                                                                        </li>   
                                                                                    </c:if>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                        <c:if test="${questionMap.isIsMultipleChoice() != true}"> <!-- Không phải câu Multiple choice -->
                                                                            <c:forEach var="ans" items="${questionMap.getAnswer()}">
                                                                                <c:if test="${ans.correct == true}">

                                                                                    <c:if test="${ans.userCheck != true}">
                                                                                        <li class="d-flex mb-{ans.userCheck != true}1">
                                                                                            <input type="radio" name="answer_${questionMap.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                            <span class="col-11 muahahaha"><input class="bg-primary col-6 form-control ms-2" type="text" name="name" value="${ans.content}"  checked="check" readonly=""></span>
                                                                                        </li>    
                                                                                    </c:if>
                                                                                    <c:if test="${ans.userCheck == true}">
                                                                                        <li class="d-flex mb-1">
                                                                                            <input type="radio" checked="check" name="answer_${questionMap.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                            <span class=" muachuchuchu col-11"><input class="bg-success col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly=""></span>
                                                                                        </li>   
                                                                                    </c:if>

                                                                                </c:if>
                                                                                <c:if test="${ans.correct != true}">
                                                                                    <c:if test="${ans.userCheck == true}">
                                                                                        <li class="d-flex mb-1">
                                                                                            <input type="radio" checked="check" name="answer_${questionMap.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                            <span class="col-11"><input class="bg-danger col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly=""> </span>
                                                                                        </li>   
                                                                                    </c:if>


                                                                                    <c:if test="${ans.userCheck != true}">
                                                                                        <li class="d-flex mb-1">
                                                                                            <input type="radio" name="answer_${questionMap.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                            <span class="col-11"><input class="  col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly=""></span>
                                                                                        </li>   
                                                                                    </c:if>

                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </c:if>
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
                                        <div class="btn btn-primary"><a href="QuizHistoryServlet">return to quiz history</a></div>

                        </div>
                    </div>
                                        
                </div>
            </div>

        </div>





    </body>
</html>

