<%-- 
    Document   : Login
    Created on : Sep 24, 2023, 6:18:25 PM
    Author     : ROG
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
        <title>Snippet</title>
        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css' rel='stylesheet'>
        <link href='https://use.fontawesome.com/releases/v5.7.2/css/all.css' rel='stylesheet'>
        <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <style>::-webkit-scrollbar {
                width: 8px;
            }
            /* Track */
            ::-webkit-scrollbar-track {
                background: #f1f1f1;
            }

            /* Handle */
            ::-webkit-scrollbar-thumb {
                background: #888;
            }

            /* Handle on hover */
            ::-webkit-scrollbar-thumb:hover {
                background: #555;
            } /* Importing fonts from Google */
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

            /* Reseting */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Poppins', sans-serif;
            }

            body {
                background: #ecf0f3;
            }

            .wrapper {
                max-width: 400px;
                min-height: 500px;
                margin: 80px auto;
                padding: 40px 30px 30px 30px;
                background-color: #ecf0f3;
                border-radius: 15px;
                box-shadow: 13px 13px 20px #cbced1, -13px -13px 20px #fff;
            }

            .logo {
                width: 80px;
                margin: auto;
            }

            .logo img {
                width: 100%;
                height: 80px;
                object-fit: cover;
                border-radius: 50%;
                box-shadow: 0px 0px 3px #5f5f5f,
                    0px 0px 0px 5px #ecf0f3,
                    8px 8px 15px #a7aaa7,
                    -8px -8px 15px #fff;
            }

            .wrapper .name {
                font-weight: 600;
                font-size: 1.4rem;
                letter-spacing: 1.3px;
                padding-left: 10px;
                color: #555;
            }

            .wrapper .form-field input {
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

            .wrapper .form-field {
                padding-left: 10px;
                margin-bottom: 20px;
                border-radius: 20px;
                box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
            }

            .wrapper .form-field .fas {
                color: #555;
            }

            .wrapper .btn {
                box-shadow: none;
                width: 100%;
                height: 40px;
                background-color: #03A9F4;
                color: #fff;
                border-radius: 25px;
                box-shadow: 3px 3px 3px #b1b1b1,
                    -3px -3px 3px #fff;
                letter-spacing: 1.3px;
            }

            .wrapper .btn:hover {
                background-color: #039BE5;
            }

            .wrapper a {
                text-decoration: none;
                font-size: 0.8rem;
                color: #03A9F4;
            }

            .wrapper a:hover {
                color: #039BE5;
            }

            @media(max-width: 380px) {
                .wrapper {
                    margin: 30px 20px;
                    padding: 40px 15px 15px 15px;
                }
            }</style>
    </head>
    <div class="container p-5">    

        <body className='snippet-body'>
            <div class="wrapper">                               
                <div class="row">
                    <div class="col col-sm-3"></div>
                    <div class="col col-sm-5" style="margin-left: 10px">
                        <a href="lecturerEditExam?tId=${sessionScope.eId}">   <input class="btn btn-primary" value="Back" type="submit"> </a>
                    </div>
                </div>
                <div class="text-center mt-4 name">
                    Import question bank from excel

                </div>
                <!--                <form class="p-3 mt-3" action ="getQuestionCSV" method ="post" enctype="multipart/form-data" onclick="(event) => {
                                    event.preventDefault();"} >-->

                <form class="p-3 mt-3" id='submit-form' action="getQuestionCSV" method="POST" enctype="multipart/form-data" onclick="(event) => {
                            event.preventDefault();
                        }"><!--
                                                            <input type="file" name="file" />
                                                            <input type="submit" value="Upload" />
                                                        </form>        -->
                    <input type="hidden" name="eId" value="${sessionScope.eId}">
                    <input type="hidden" name="cId" value="${sessionScope.cId}">

                    <div class="form-field d-flex align-items-center">
                        <input type="file" name="file"  >
                    </div>
                    <input class="btn mt-3" type="submit" value="Upload" />
                    <br>                    <br>

                    <p class="text-success">${requestScope.success}</p>
                    <br>
                    <br>

                    <p class="text-danger">${sessionScope.err}</p>
                    <br>

                    <!--<button class="btn mt-3">Login</button>-->
                </form>

            </div>
            <script type='text/javascript' src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
            <script type='text/javascript' src='#'></script>
            <script type='text/javascript' src='#'></script>
            <script type='text/javascript' src='#'></script>
            <script type='text/javascript'>#</script>
            <script type='text/javascript'>var myLink = document.querySelector('a[href="#"]');
                myLink.addEventListener('click', function (e) {
                    e.preventDefault();
                });</script>

        </body>
</html>
