

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
    </head>
    <body class="sb-sidenav-toggled">
         <div class="wrapper">

            <!-- Sidebar  -->
            <div style="" id="header">
                <%@include file="Components/Sidebar.jsp" %>
            </div>

            <!-- Page Content  -->
            <div id="content">

                <%@include file="Components/header.jsp" %>
        <div id="layoutSidenav" class="mb-4">
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <div class="container">
                        <h1>Your attempt: ${requestScope.attempt}</h1>

                        <h1>Your Score: ${requestScope.SCORE}</h1>
                        <h1>Number of correct answer: ${requestScope.numtrue}</h1>

                        <div>
                            <a href="quiz-list" class="btn btn-danger" style="float: left"/>Back</a>
                        </div>

                        <c:if test="${requestScope != null}">
                            <a href="QuizHandle?quizzId=${requestScope.quizzId}&subId=${requestScope.subId}&method=get" class="btn btn-danger"/>Do Again</a>
                            <a href="ReviewQuizzServlet?quizzId=${requestScope.quizzId}&attempt=${requestScope.attempt}&subId=${requestScope.subId}" class="btn btn-success"/>Review</a>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>
        </div>
        </div>

        <div class="footer"> <%@include file="Components/footer.jsp" %></div>




    </body>
</html>
