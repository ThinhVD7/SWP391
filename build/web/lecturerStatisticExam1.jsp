<%-- 
    Document   : lecturerStatisticExam1
    Created on : Oct 30, 2023, 3:15:48 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/data.js"></script>
        <script src="https://code.highcharts.com/modules/drilldown.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script src="https://code.highcharts.com/modules/export-data.js"></script>
        <script src="https://code.highcharts.com/modules/accessibility.js"></script>
        <title>Score Statistic</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <style>
            .highcharts-figure,
            .highcharts-data-table table {
                min-width: 310px;
                max-width: 800px;
                margin: 1em auto;
            }

            #container {
                height: 400px;
            }

            .highcharts-data-table table {
                font-family: Verdana, sans-serif;
                border-collapse: collapse;
                border: 1px solid #ebebeb;
                margin: 10px auto;
                text-align: center;
                width: 100%;
                max-width: 500px;
            }

            .highcharts-data-table caption {
                padding: 1em 0;
                font-size: 1.2em;
                color: #555;
            }

            .highcharts-data-table th {
                font-weight: 600;
                padding: 0.5em;
            }

            .highcharts-data-table td,
            .highcharts-data-table th,
            .highcharts-data-table caption {
                padding: 0.5em;
            }

            .highcharts-data-table thead tr,
            .highcharts-data-table tr:nth-child(even) {
                background: #f8f8f8;
            }

            .highcharts-data-table tr:hover {
                background: #f1f7ff;
            }
        </style>
    </head>
    <body>
        <figure class="highcharts-figure">
            <div id="container"></div>
            <table id="datatable">
                <thead>
                    <tr>
                        <th></th>
                        <th>Number Student</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.studentResult}" var="s">
                        <tr>
                            <th>${s.resultID}</th>
                            <td>${s.state}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form action="lecturerStatisticExam">
                <input value="${requestScope.examId}" name="examId" type="hidden"/>
                <button type="submit" class="edit-exam-button-bottom">
                    Change Chart
                </button>
            </form>
        </figure>
    </body>

    <script>
        Highcharts.chart('container', {
            data: {
                table: 'datatable'
            },
            chart: {
                type: 'column'
            },
            title: {
                text: 'Number Student achieve in ${requestScope.examName}'
            },
            xAxis: {
                type: 'category'
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: 'Number'
                }
            }
        });

    </script>
</html>
