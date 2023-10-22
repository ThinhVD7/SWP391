<%-- 
    Document   : updateQuestion
    Created on : Oct 20, 2023, 9:02:45 AM
    Author     : tanki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Question</title>
        <%@include file="all_component/allCss.jsp" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="all_component/studentStyle.css" />

        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />

        <style>
            .overlay {

                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0); /* Semi-transparent black background */
                backdrop-filter: blur(4px); /* Adjust the blur intensity as needed */
                z-index: 1;
            }

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
                width: 75%;
                height: 75%;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                /*box-shadow: 0 1px 1px rgba(0,0,0,.05);*/
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
                height: 800px;
                overflow: auto;
            }
            .table-title {
                padding-bottom: 15px;
                background: #6c757d;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
            }
            .popup-button {
                background-color: #299be4;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 5px 10px;
                font-size: 20px;
                cursor: pointer;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
                position: fixed;
                top: 3%;
                right:5%;
            }

            .popup-button:hover{
                background-color: #0073e6;
            }


            @import url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap');

            *{
                margin: 0;
                padding: 0;
                list-style: none;
                font-family: 'Josefin Sans', sans-serif;
            }

            body{
                background: #292929;
            }

            .wrapper{
                width: 100%    ;
                background: #fff;
                margin: 20px auto;
                border-radius: 3px;
                font-family: 'Josefin Sans', sans-serif;
                font-weight: bold;

            }

            .wrapper .tabs ul{
                width: 1200px;
                height: 40px;
                display: flex;

            }

            .wrapper .tabs ul li{
                width: 33%;
                height: 40px;
                padding: 0 20px;
                text-align: center;
                background: #dbe3eb;
                cursor: pointer;
                text-transform: uppercase;
                color: #8b9393;
                font-size: 14px;
                letter-spacing: 2px;
                transition: all 0.3s ease;
            }

            .wrapper .tabs ul li:first-child{
                border-top-left-radius: 3px;
            }

            .wrapper .tabs ul li:last-child{
                border-top-right-radius: 3px;
            }

            /*            .wrapper .tabs ul li img{
                            width: 35px;
                            height: 35px;
                            display: block;
                            margin: 0 auto 3px;
                        }*/

            .wrapper .content{
                padding: 30px;
                height: 800px;
                border-bottom: 6px solid #44c8fe;
                border-radius: 3px;
            }

            .wrapper .content .tab_content{
                font-size: 14px;
                line-height: 22px;
            }

            .wrapper .tabs ul li.active{
                border-top: 6px solid #44c8fe;
                margin-top: -15px;
                background: #fff;
                color: #44c8fe;
                padding-top: 9px;
            }
            @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: "Poppins", sans-serif;
                --color-hover: rgba(32, 59, 232);
                --transition: all 0.2s ease;
            }
            #my-form {
                height: 600px;
                align-content: center;
                justify-items: center;
            }

            input[type="text"]{
                padding: 10px;
                margin: 10px auto;
                display: block;
                border-radius: 5px;
                border: 1px solid lightgrey;
                background: none;
                width: 274px;
                color: black;
            }

            input[type="text"]:focus {
                outline: none;
            }

            .controls {
                width: 294px;
                margin: 15px auto;
            }
            .controls .li {
                cursor: pointer;
            }

            #remove_fields {
                float: right;
            }
            .controls a i.fa-minus {
                margin-right: 5px;
            }
            a {
                color: black;
                text-decoration: none;
            }

            #score {
                display: block;
            }


        </style>        
    </head>
    <body>



        <div class="popup-container" onclick="event.stopPropagation()">
            <h2>Update Question
            </h2>
            <h4 class="text-danger">${err}</h4>

            <a href="lecturerEditExam?tId=${sessionScope.exam.examID}"> <button class ="popup-button">Back</button> </a>


            <div class="container-xl">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-5">             
                                </div>
                                <div class="col-sm-7">                             
                                </div>
                            </div>
                        </div>

                        <div class="wrapper">                      
                            <div class="content">
                                <div class="tab_content tab_1">
                                    <!--<form id="addManually" action="" method="post">-->

                                    <form id="my-form" action="lecturerUpdateQuestion" method="post">

                                        <div class="wrapper">
                                            <div class="form-inline">

                                                <div class="form-group col-sm-7">
                                                    <input type="text" name="title" class="survey_options" placeholder="Title" required="" value="${question.title}">

                                                </div>
                                                <div class="form-group col-sm-5">

                                                    Question Mark:
                                                    <input type="number" name="mark" class="survey_options" placeholder="Question mark" required="" value="${question.mark}">                     

                                                </div>

                                            </div>
                                            <div class="form-inline">
                                                <div class="form-group col-sm-7">
                                                    <input type="text" name="content" class="survey_options" placeholder="Question Content" required="" value="${question.content}">                     
                                                </div>
                                                <div class="form-group col-sm-3 mr-4">

                                                    Question Type:
                                                    <select name="questionType" class="survey_options ml-3" id="questionType">
                                                        <option ${question.type == '1'?"selected":""} value="1">Multiple Choice</option>
                                                        <option ${question.type == '0'?"selected":""} value="0">One Choice</option>
                                                    </select>
                                                </div>


                                            </div>

                                            <div id="container">

                                                <c:forEach items="${requestScope.listChoice}" var="c" varStatus="index">
                                                    <div class="row mt-3">
                                                        <input type="hidden" name="${index.index}_choiceId" value="${c.choiceId}">
                                                        <div class="form-group col-sm-7 index">
                                                            <input type="text" name="${index.index}_survey_options[]" class="survey_options" size="50" placeholder="Answer:" required="" value="${c.choiceContent}">
                                                        </div>
                                                        <div class="form-group col-sm-3">
                                                            Score Percentage:
                                                            <select name="${index.index}_score" class="survey_options ml-3" id="${index.index}_score">
                                                                <c:if test="${question.type == '1'}">
                                                                    <option ${c.choicePercentage == 0?"selected":""} value="0">0</option>
                                                                    <option ${c.choicePercentage == 20?"selected":""} value="20">20</option>
                                                                    <option${c.choicePercentage == 25?"selected":""} value="25">25</option>
                                                                    <option ${c.choicePercentage == 33?"selected":""} value="33">33</option>
                                                                    <option ${c.choicePercentage == 50?"selected":""} value="50">50</option>
                                                                    <option ${c.choicePercentage == 100?"selected":""} value="100">100</option>
                                                                </c:if>
                                                                <c:if test="${question.type == '0'}">
                                                                    <option ${c.choicePercentage == 0?"selected":""} value="0">0</option>
                                                                    <option ${c.choicePercentage == 100?"selected":""} value="100">100</option>
                                                                </c:if>

                                                            </select>
                                                        </div>
                                                    </div>
                                                </c:forEach>

                                            </div>

                                        </div>

                                        <input type="hidden" id="hiddenInput" name="newDivCount">
                                        <div class="form-group row">
                                            <input class="btn btn-primary btn-sm align-items-center col-3" style="margin: 0 auto; display: block;border-radius:20px " type="submit" value="Update Question" />
                                        </div>
                                    </form>


                                </div>                                         
                            </div>
                        </div>




                    </div>
                </div>
            </div>

            <!--<input type="text" id="newClassName" placeholder="New Class Name">-->
            <!--                <button class ="popup-button" onclick="editClassName()">Save</button>-->
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <script>
            document.addEventListener("DOMContentLoaded", function () {
                var container = document.getElementById("container"); // The container for your loop
                var hiddenInput = document.getElementById("hiddenInput");

                // Initialize the count to 0
                var count = 0;

                if (container) {
                    // Get all the rows within the container
                    var rows = container.getElementsByClassName("row");

                    // Update the count based on the number of rows
                    count = rows.length;

                    // Set the value of the hidden input field to the count
                    hiddenInput.value = count;
                }
            });



            var count = 0;
            document.getElementById('add_more_fields').addEventListener('click', function (event) {
                event.preventDefault();
                var x = document.getElementById('questionType');
                var container = document.getElementById('container');

                // Create a new div element
                var newDiv = document.createElement('div');
                newDiv.className = 'form-inline count';

                // Generate a unique ID for the new div
                newDiv.id = count;


                // Define the default options for the count_score select
                var countScoreOptions = `
        <option value="0">0</option>
        <option value="20">20</option>
        <option value="25">25</option>
        <option value="33">33</option>
        <option value="50">50</option>
        <option value="100">100</option>
    `;

                if (x.value == 0) {
                    // For "Multiple Choice"
                    countScoreOptions = `
            <option value="0">0</option>
            <option value="100">100</option>
        `;
                }

                // Add the HTML content to the new div
                newDiv.innerHTML = `
        <div class="form-group col-sm-7 index">
            <input type="text" name="` + count + `_survey_options[]" class="survey_options" size="50" placeholder="Answer:" required="">
        </div>
        <div class="form-group col-sm-3">
        Score Percentage:
            <select name="` + count + `_score" class="survey_options ml-3" id="` + count + `_score">
        
            ` + countScoreOptions + `
            </select>
        </div>
  
    `;

                // Append the new div to the container
                container.appendChild(newDiv);
                count = container.children.length;

                var hiddenInput = document.getElementById('hiddenInput');
                hiddenInput.value = count;


            });

            document.getElementById('remove_fields').addEventListener('click', function () {
                var container = document.getElementById('container');
                // Get the last added div in the container
                var lastDiv = container.lastChild;
                // Check if the last div exists and remove it
                if (lastDiv) {
                    container.removeChild(lastDiv);
                    // Update the count based on the number of newDivs in the container
                    count = container.children.length;

                    // Update the hidden input value with the count
                    var hiddenInput = document.getElementById('hiddenInput');
                    hiddenInput.value = count;
                }
            });
            document.getElementById('my-form').addEventListener('submit', function (event) {
                // Get all the "Score Percentage" select elements
                var scoreElements = document.querySelectorAll('select[id$="_score"]');
                var totalScore = 0;

                // Calculate the total score
                scoreElements.forEach(function (scoreElement) {
                    totalScore += parseInt(scoreElement.value);
                });

                // Check if the total score is greater than 100
                if (totalScore > 100) {
                    // Prevent the form from being submitted
                    event.preventDefault();
                    alert('The total score cannot exceed 100%.');
                }
            });


    </script>
</html>
