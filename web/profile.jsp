<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->

        <!-- PAGE TITLE HERE ============================================= -->
        <title>Profile </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">

    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Profile</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="${requestScope.prevPage}">Home</a></li>
                            <li>Profile</li>
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <!-- inner page banner END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <div class="profile-bx text-center">
                                        <div class="user-profile-thumb">
                                            <img src="assets/images/profile/pic1.jpg" alt=""/>
                                        </div>
                                        <div class="profile-info">
                                            <h4>${sessionScope.user.name}</h4>
                                            <span>${sessionScope.user.email}</span>
                                        </div>
                                        <div class="profile-social">
                                            <ul class="list-inline m-a0">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="profile-tabnav">
                                            <ul class="nav nav-tabs">
                                                <li class="nav-item">
                                                    <a class="nav-link active" data-toggle="tab" href="#courses"><i class="ti-book"></i>My profile</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="tab" href="#quiz-results"><i class="ti-bookmark-alt"></i>Quiz Results </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="tab" href="#edit-profile"><i class="ti-pencil-alt"></i>Edit Profile</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="" href="ChangePassword"><i class="ti-lock"></i>Change Password</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="courses">
                                                <div class="profile-head">

                                                    <div class="feature-filters style1 ml-auto">

                                                    </div>
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
                                                            <div class="col-7 mb-3">
                                                                <h6>Email</h6>
                                                                <p class="text-muted">${sessionScope.user.name}</p>
                                                            </div>
                                                            <div class="col-5 mb-3">
                                                                <h6>Phone</h6>
                                                                <p class="text-muted">${sessionScope.user.gender}</p>
                                                            </div>
                                                        </div>

                                                        <div class="row pb-3">
                                                        </div>


                                                    </div>

                                                </div>
                                            </div>
                                            <div class="tab-pane" id="quiz-results">
                                                <div class="profile-head">
                                                    <h3>Quiz Results</h3>
                                                </div>
                                                <div class="courses-filter">
                                                    <div class="row">
                                                        <div class="col-md-6 col-lg-6">
                                                            <ul class="course-features">
                                                                <li><i class="ti-book"></i> <span class="label">Lectures</span> <span class="value">8</span></li>
                                                                <li><i class="ti-help-alt"></i> <span class="label">Quizzes</span> <span class="value">1</span></li>
                                                                <li><i class="ti-time"></i> <span class="label">Duration</span> <span class="value">60 hours</span></li>
                                                                <li><i class="ti-stats-up"></i> <span class="label">Skill level</span> <span class="value">Beginner</span></li>
                                                                <li><i class="ti-smallcap"></i> <span class="label">Language</span> <span class="value">English</span></li>
                                                                <li><i class="ti-user"></i> <span class="label">Students</span> <span class="value">32</span></li>
                                                                <li><i class="ti-check-box"></i> <span class="label">Assessments</span> <span class="value">Yes</span></li>
                                                            </ul>
                                                        </div>
                                                        <div class="col-md-6 col-lg-6">
                                                            <ul class="course-features" id="myTabs">
                                                                <li><i class="ti-book"></i> <span class="label">Lectures</span> <span class="value">8</span></li>
                                                                <li><i class="ti-help-alt"></i> <span class="label">Quizzes</span> <span class="value">1</span></li>
                                                                <li><i class="ti-time"></i> <span class="label">Duration</span> <span class="value">60 hours</span></li>
                                                                <li><i class="ti-stats-up"></i> <span class="label">Skill level</span> <span class="value">Beginner</span></li>
                                                                <li><i class="ti-smallcap"></i> <span class="label">Language</span> <span class="value">English</span></li>
                                                                <li><i class="ti-user"></i> <span class="label">Students</span> <span class="value">32</span></li>
                                                                <li><i class="ti-check-box"></i> <span class="label">Assessments</span> <span class="value">Yes</span></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="edit-profile">
                                                <div class="profile-head">
                                                    <h3>Edit Profile</h3>
                                                </div>
                                                <form class="edit-profile">
                                                    <div class="">
                                                        <div class="form-group row">
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-10 ml-auto">
                                                                <h3>1. Personal Details</h3>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Full Name</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="${sessionScope.user.name}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Occupation</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="CTO">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Company Name</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="EduChamp">
                                                                <span class="help">If you want your invoices addressed to a company. Leave blank to use your full name.</span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Phone No.</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="+120 012345 6789">
                                                            </div>
                                                        </div>

                                                        <div class="seperator"></div>

                                                        <div class="form-group row">
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-10 ml-auto">
                                                                <h3>2. Address</h3>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Address</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="5-S2-20 Dummy City, UK">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">City</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="US">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">State</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="California">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Postcode</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="000702">
                                                            </div>
                                                        </div>

                                                        <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space-2x"></div>

                                                        <div class="form-group row">
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-10 ml-auto">
                                                                <h3 class="m-form__section">3. Social Links</h3>
                                                            </div>
                                                        </div>

                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Linkedin</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="www.linkedin.com">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Facebook</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="www.facebook.com">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Twitter</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="www.twitter.com">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Instagram</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" type="text" value="www.instagram.com">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="">
                                                        <div class="">
                                                            <div class="row">
                                                                <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                                </div>
                                                                <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                    <button type="reset" class="btn">Save changes</button>
                                                                    <button type="reset" class="btn-secondry">Cancel</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="tab-pane" id="change-password">
                                                <div class="profile-head">
                                                    <h3>Change Password</h3>
                                                </div>
                                                <form class="edit-profile" action="ChangePassword" method="POST">
                                                    <div class="">
                                                        <div class="form-group row">
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-9 ml-auto">
                                                                <h3>Password</h3>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Current Password</label>
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                                <input class="form-control" type="password" value="" name ="opass">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">New Password</label>
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                                <input class="form-control" type="password" value="" name ="npass" required="" onchange="onChange()" >
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Re Type New Password</label>
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                                <input class="form-control" type="password" value="" name ="rnpass" required="" onchange="onChange()" >
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <p class="text-danger">${msg}</p>
                                                    <p class="text-danger">${fmsg}</p>
                                                    <div class="row">
                                                        <div class="col-12 col-sm-4 col-md-4 col-lg-3">
                                                        </div>
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                            <button type="Submit" class="btn" onclick="showTab()">Save changes</button>
                                                            <button type="reset" class="btn-secondry">Cancel</button>
                                                        </div>
                                                    </div>

                                                </form>
                                            </div>
                                        </div> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- contact area END -->
            </div>
            <!-- Content END-->

            <!-- Footer END ==== -->
            <button class="back-to-top fa fa-chevron-up" ></button>
        </div>
        <!-- External JavaScripts -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="assets/vendors/counter/waypoints-min.js"></script>
        <script src="assets/vendors/counter/counterup.min.js"></script>
        <script src="assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="assets/vendors/masonry/masonry.js"></script>
        <script src="assets/vendors/masonry/filter.js"></script>
        <script src="assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
        <script src='assets/vendors/switcher/switcher.js'></script>


        <script>

                                                                function onChange() {
                                                                    const password = document.querySelector('input[name=npass]');
                                                                    const confirm = document.querySelector('input[name=rnpass]');
                                                                    if (confirm.value === password.value) {
                                                                        confirm.setCustomValidity('');
                                                                    } else {
                                                                        confirm.setCustomValidity('Passwords do not match');
                                                                    }
                                                                }



        </script>

    </body>

</html>
