<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="all_component/allCss.jsp" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="all_component/studentStyle.css" />
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />
    <title>Class List</title>
    <style>
        body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #299be4;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
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
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }
/*        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }*/

        .subject-name {
            font-size: 24px; /* Adjust the font size as needed */
            margin-bottom: 10px;
        }

        .subject-info {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
            width: 50%; /* Take up half of the screen */
            margin: 20px auto; /* Add margin for distance from top and center horizontally */
            text-align: center; /* Center text in the subject info div */
        }

        .view-material-button {
            background-color: #3366CC;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }

        .class-list {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
            width: 50%; /* Take up half of the screen */
            margin: 20px auto; /* Add margin for distance from top and center horizontally */
        }

        .class-container {
            /* Each class container takes the full width of the .class-list */
        }

        /* Style links for class containers */
        .class-link {
            text-decoration: none;
            color: #333;
        }

        /* Add hover effect for links */
        .class-link:hover {
            color: #3366CC;
        }

        /* Shrink text in <p> tag relative to the parent <div> */
        .subject-info p {
            font-size: 100%; /* Initial font size */
        }
        .edit-button {
            background-color: #66CC33;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            font-size: 14px;
            cursor: pointer;
        }

        /* Style for the pop-up */
        .popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
            z-index: 1;
        }
        .overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0); /* Semi-transparent black background */
            backdrop-filter: blur(4px); /* Adjust the blur intensity as needed */
            z-index: 1;
        }

        /* Style for the clear pop-up container */
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
        }
    </style>
</head>
<body>
    <div class="subject-name">
        Subject Name
    </div>

    <div class="subject-info">
        <p>Subject Information Text</p>
        <button class="view-material-button">View Material</button>
    </div>

    <div class="class-list">
        <!--<a href="https://example.com/class1" class="class-link">-->
            <div class="class-container">
                <h1>Class Name 1</h1>
                <button class="edit-button" onclick="openPopup('class91')">Edit</button>
            </div>
        <div class="class-container">
            <h1 class="class-name" onclick="openPopup('Class Name 1')">Class Name 1</h1>
        </div>
        
        

        <!-- Add more class containers with edit buttons as needed -->
    </div>

    <div class="subject-path">
        <a href="#">Home</a> / <a href="#">This Subject</a>
    </div>

        <!-- Edit Class Name Pop-up -->
    <div id="classEditPopup" class="overlay" onclick="closePopup()">
        <div class="popup-container" onclick="event.stopPropagation();">
            <h2>Edit Class Name</h2>
            <input type="text" id="newClassName" placeholder="New Class Name">
            <button onclick="editClassName()">Save</button>
            <button onclick="closePopup()">Cancel</button>
             <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-5">
                                <h2> </a><b>Lecturer</b></h2>
                            </div>
                            <div class="col-sm-7">
                                <a href="managerAssignLecturer" class="btn btn-secondary"> <span>Submit</span></a>
                                
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>ID</th>						
                                <th>Name</th>
                                <th>Email</th>
                                <th>View Profile</th>
                                <th>Add</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <tr>
                                    <td>1</td>
                                    <td><a href="#">ThanhDT59</a></td>
                                    <td>Do Tien Thanh</td>                        
                                    <td>thanhdt59@fe.edu.vn </td>
                                    <td><a href="#"> <i class='bx bxs-user-account'></i> </td>
                                    <td>
                                        <input type="checkbox" name="add" value="ON" />
                                    </td>
                                </tr>

                            <c:forEach items="${requestScope.listA}" var="u" varStatus="x" >
                                <tr>
                                    <td>${x.count}</td>
                                    <td><a href="#">${u.accountID}</a></td>
                                    <td>${u.name}</td>                        
                                    <td>${u.email} </td>
                                    <td><a href="#"> icon </td>
                                    <td>
                                         <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>
                                <div class="clearfix">
                                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                                    <ul class="pagination">
                                        <li class="page-item disabled"><a href="#">Previous</a></li>
                                        <li class="page-item"><a href="#" class="page-link">1</a></li>
                                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                                        <li class="page-item active"><a href="#" class="page-link">3</a></li>
                                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                                    </ul>
                                </div>
                </div>
            </div>
        </div>   
        </div>
    </div>
    
    <!-- Blurred overlay -->
    <div id="overlay" class="overlay"></div>

    <script>
        // Function to open the edit class name pop-up
        function openPopup(className) {
            const overlay = document.getElementById('classEditPopup');
            const newClassNameInput = document.getElementById('newClassName');
            newClassNameInput.value = className;
            overlay.style.display = 'block';
        }

        // Function to close the pop-up
        function closePopup() {
            const overlay = document.getElementById('classEditPopup');
            overlay.style.display = 'none';
        }

        // Function to save the edited class name
        function editClassName() {
            const newClassNameInput = document.getElementById('newClassName');
            const updatedClassName = newClassNameInput.value;

            // Update the class name in your data or display
            console.log('Updated Class Name:', updatedClassName);

            // Close the pop-up
            closePopup();
        }
    </script>
</body>
</html>
