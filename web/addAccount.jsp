<%-- 
    Document   : addAccount
    Created on : Sep 28, 2023, 10:51:41 PM
    Author     : tanki
--%>
<%@page import = "java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="all_component/allCss.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Account</title>
    </head>
    <body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>


        <style>
            .gradient-custom {
                /* fallback for old browsers */
                background: skyblue;

                /* Chrome 10-25, Safari 5.1-6 */
                /*background: -webkit-linear-gradient(to bottom right, rgba(99, 191, 255, 0.83), rgba(99, 191, 255, 0.83));*/

                /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                background: linear-gradient(to bottom right, rgba(99, 191, 255, 0.83), rgba(99, 191, 172, 0.83))
            }

            .card-registration .select-input.form-control[readonly]:not([disabled]) {
                font-size: 1rem;
                line-height: 2.15;
                padding-left: .75em;
                padding-right: .75em;
            }
            .card-registration .select-arrow {
                top: 13px;
            }
        </style>



        <section class="vh-100 gradient-custom">
            <div class="container py-5 h-100">
                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-7">
                        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                            <div class="card-body p-4 p-md-5">
                                <a href="admin"><i class="fa-solid fa-house fa-2x"></i> </a>
                                <p class="text-danger">${mess}</p> 
                                <p class="text-success">${mess1}</p> 

                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Account Information:</h3>


                                <form id="form" action="addAccount" method="post">

                                    <div class="row">
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                ID<input type="text" id="id" class="form-control form-control-lg" name="id" required="" value="${requestScope.id}" />
                                                <label class="form-label" for="id"><p class="text-danger">${idErr}</p></label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                Name<input type="text" id="name" class="form-control form-control-lg"  name="name" required="" value="${requestScope.name}"/>
                                                <label class="form-label" for="name"><p class="text-danger">${nameErr}</p></label>
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 d-flex align-items-center">

                                            <div class="form-outline datepicker w-100">
                                                Email<input type="text" class="form-control form-control-lg" id="email" name="email" required="" value="${requestScope.email}" />
                                                <label for="email" class="form-label"><p class="text-danger">${emailErr}</p></label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <h6 class="mb-2 pb-1">Gender: </h6>

                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="gender" id="femaleGender"
                                                       value="2" checked />
                                                <label class="form-check-label" for="femaleGender">Female</label>
                                            </div>

                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="gender" id="maleGender"
                                                       value="1" />
                                                <label class="form-check-label" for="maleGender">Male</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="gender" id="fluidGender"
                                                       value="0" checked />
                                                <label class="form-check-label" for="femaleGender">Others</label>
                                            </div>



                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 pb-2">

                                            <div class="form-outline">
                                                Phone Number<input type="text" id="phoneNumber" class="form-control form-control-lg" name="phno" required="" value="${requestScope.phno}" />
                                                <label class="form-label" for="phoneNumber"><p class="text-danger">${phoneErr}</p> </label>
                                            </div>

                                        </div>
                                        <div class="col-md-6 mb-4 pb-2">

                                            <!--                                            <div class="form-outline">
                                                                                            <input type="tel" id="phoneNumber" class="form-control form-control-lg" />
                                                                                            <label class="form-label" for="phoneNumber">Phone Number</label>
                                                                                        </div>-->
                                            Status: <select class="select form-control-md" name="status">
                                                <option value="" disabled>Status</option>
                                                <option value="1">Active</option>
                                                <option value="0">Inactive</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-12">

                                            Role: <select class="select form-control-md" name="role">
                                                <option value="" disabled>Role</option>
                                                <option value="0">Admin</option>
                                                <option value="1">Manager</option>
                                                <option value="2">Lecturer</option>
                                                <option value="3">Student</option>


                                            </select>
                                            <label class="form-label select-label"></label>

                                        </div>
                                    </div>

                                    <div class="mt-4 pt-2">
                                        <input class="btn btn-primary btn-lg" type="submit" value="Submit" />
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
    <script>

        document.addEventListener('DOMContentLoaded', function () {
            // Mong muốn của chúng ta
            Validator({
                form: '#form-1',
                formGroupSelector: '.form-group',
                errorSelector: '.form-message',
                rules: [
                    Validator.isRequired('#name', 'Vui lòng nhập tên đầy đủ của bạn'),
                    Validator.isRequired('#name', 'Vui lòng nhập tên đầy đủ của bạn'),
                    Validator.isEmail('#email'),
                    Validator.minLength('#password', 6),
                    Validator.isRequired('#password_confirmation'),
                    Validator.isConfirmed('#password_confirmation', function () {
                        return document.querySelector('#form-1 #password').value;
                    }, 'Mật khẩu nhập lại không chính xác')
                ],
                onSubmit: function (data) {
                    // Call API
                    console.log(data);
                }
            });


            Validator({
                form: '#form-2',
                formGroupSelector: '.form-group',
                errorSelector: '.form-message',
                rules: [
                    Validator.isEmail('#email'),
                    Validator.minLength('#password', 6),
                ],
                onSubmit: function (data) {
                    // Call API
                    console.log(data);
                }
            });
        });

    </script>
</html>
