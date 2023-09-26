<%-- 
    Document   : profile
    Created on : Sep 26, 2023, 10:59:38 PM
    Author     : tanki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="all_component/allCss.jsp" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <style>

    </style>
    <body>






        <section class="vh-100" style="background-color: #f4f5f7;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-lg-6 mb-4 mb-lg-0">
                        <div class="card mb-3" style="border-radius: .5rem;">
                            <div class="row g-0">
                                <div class="col-md-4 gradient-custom text-center text-white"
                                     style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                                    <img src="https://t4.ftcdn.net/jpg/05/49/98/39/360_F_549983970_bRCkYfk0P6PP5fKbMhZMIb07mCJ6esXL.jpg"
                                         alt="Avatar" class="img-fluid my-5" style="border-radius: 50%;width: 100px;" />
                                    <h5 style="color:black">${sessionScope.user.name}</h5>






                                </div>
                                <div class="col-md-8 ">
                                    <div class="card-body p-4 profile-info" id="profile-info" >
                                        <h6>Information</h6>
                                        <hr class="mt-0 mb-4">
                                        <div class="row pt-1">
                                            <div class="col-7 mb-3">
                                                <h6>Email</h6>
                                                <p class="text-muted">${sessionScope.user.email}</p>
                                            </div>
                                            <div class="col-5 mb-3">
                                                <h6>Phone</h6>
                                                <p class="text-muted">${sessionScope.user.phoneNumber}</p>
                                            </div>
                                        </div>
                                        <div class="row pt-1">
                                            <div class="col-12 mb-3">
                                                <h6>Name</h6>
                                                <p class="text-muted">${sessionScope.user.name}</p>
                                            </div>                                   
                                        </div>
                                        <div class="row pt-1">
                                            <div class="col-12 mb-3">
                                                <h6>Status</h6>
                                                <p class="text-muted">${sessionScope.user.status}</p>
                                            </div>                                   
                                        </div>
                                        <div class="row pb-3">
                                            <div class="mx-auto">
                                                <a href="changePassword.jsp"> <button id="change-password-button" class="btn btn-outline-primary">
                                                    Change password 
                                                    </button> </a>
                                                <button id="edit-button" class="btn btn-outline-danger">
                                                    Edit 
                                                </button>
                                            </div>
                                        </div>


                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

</body>

<script>
    $(document).ready(function () {

        let status = false;
        $('#edit-button').click(function () {
            if (status == false) {
                $('#profile-info').hide()

                $('#profile-edit').show();
                status = true;
                $(this).text("Back");
            } else {
                $('#profile-info').show()

                $('#profile-edit').hide();
                status = false;
                $(this).text("Edit");

            }
        }
        )
    });
</script>        
</html>
