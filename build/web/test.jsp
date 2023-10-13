<!DOCTYPE html>
<<<<<<< HEAD
<<<<<<< HEAD
<html>
<head>
    <title>Delete Records with Confirmation</title>
    <style>
        /* Style for the modal dialog */
        .modal {
=======
<html lang="en">
=======
<html>
>>>>>>> b809fd8 (one half lecturer and std)
<head>
    <title>Delete Records with Confirmation</title>
    <style>
<<<<<<< HEAD
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
>>>>>>> 1e16890 (yellow completed)
=======
        /* Style for the modal dialog */
        .modal {
>>>>>>> b809fd8 (one half lecturer and std)
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
<<<<<<< HEAD
<<<<<<< HEAD
            background-color: rgba(0, 0, 0, 0.5);
        }
        .modal-content {
            background-color: #fff;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            border: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
=======
            background-color: rgba(0, 0, 0, 0); /* Semi-transparent black background */
            backdrop-filter: blur(4px); /* Adjust the blur intensity as needed */
            z-index: 1;
=======
            background-color: rgba(0, 0, 0, 0.5);
>>>>>>> b809fd8 (one half lecturer and std)
        }
        .modal-content {
            background-color: #fff;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
<<<<<<< HEAD
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
            z-index: 2;
>>>>>>> 1e16890 (yellow completed)
=======
            padding: 20px;
            border: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
>>>>>>> b809fd8 (one half lecturer and std)
        }
    </style>
</head>
<body>
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b809fd8 (one half lecturer and std)
    <table>
        <thead>
            <tr>
                <th>Record ID</th>
                <th>Record Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${records}" var="record">
                <tr>
                    <td>${record.id}</td>
                    <td>${record.name}</td>
                    <td>
                        <button onclick="openConfirmationPopup(${record.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<<<<<<< HEAD

    <!-- Modal Confirmation Dialog -->
    <div id="confirmationPopup" class="modal">
        <div class="modal-content">
            <p>Are you sure you want to delete this record?</p>
            <button onclick="confirmDelete()">Yes</button>
            <button onclick="closeConfirmationPopup()">No</button>
        </div>
    </div>

    <script>
        // JavaScript functions to show and hide the modal dialog
        function openConfirmationPopup(recordId) {
            document.getElementById("confirmationPopup").style.display = "block";
            document.getElementById("confirmationPopup").setAttribute("data-record-id", recordId);
        }

        function closeConfirmationPopup() {
            document.getElementById("confirmationPopup").style.display = "none";
        }

        function confirmDelete() {
            var recordId = document.getElementById("confirmationPopup").getAttribute("data-record-id");
            // Perform the record deletion here (e.g., via Ajax)
            // var xhr = new XMLHttpRequest();
            xhr.open("POST", "DeleteRecordServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () 
            {
                if (xhr.readyState === 4 && xhr.status === 200) 
                {
                    // Handle the response from the server (if needed)
                    var response = xhr.responseText;
                    // Reload or update the page as necessary
                    location.reload();
                }
            };
            xhr.send("recordId=" + recordId);
            // After successful deletion, you can remove the row from the table
            // and close the confirmation popup
            // ...

            // Close the confirmation popup
            closeConfirmationPopup();
=======
    <div class="subject-name">
        Subject Name
    </div>
=======
>>>>>>> b809fd8 (one half lecturer and std)

    <!-- Modal Confirmation Dialog -->
    <div id="confirmationPopup" class="modal">
        <div class="modal-content">
            <p>Are you sure you want to delete this record?</p>
            <button onclick="confirmDelete()">Yes</button>
            <button onclick="closeConfirmationPopup()">No</button>
        </div>
    </div>

    <script>
        // JavaScript functions to show and hide the modal dialog
        function openConfirmationPopup(recordId) {
            document.getElementById("confirmationPopup").style.display = "block";
            document.getElementById("confirmationPopup").setAttribute("data-record-id", recordId);
        }

        function closeConfirmationPopup() {
            document.getElementById("confirmationPopup").style.display = "none";
        }

        function confirmDelete() {
            var recordId = document.getElementById("confirmationPopup").getAttribute("data-record-id");
            // Perform the record deletion here (e.g., via Ajax)
            // var xhr = new XMLHttpRequest();
            xhr.open("POST", "DeleteRecordServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () 
            {
                if (xhr.readyState === 4 && xhr.status === 200) 
                {
                    // Handle the response from the server (if needed)
                    var response = xhr.responseText;
                    // Reload or update the page as necessary
                    location.reload();
                }
            };
            xhr.send("recordId=" + recordId);
            // After successful deletion, you can remove the row from the table
            // and close the confirmation popup
            // ...

<<<<<<< HEAD
            // Update the class name in your data or display
            console.log('Updated Class Name:', updatedClassName);

            // Close the pop-up
            closePopup();
>>>>>>> 1e16890 (yellow completed)
=======
            // Close the confirmation popup
            closeConfirmationPopup();
>>>>>>> b809fd8 (one half lecturer and std)
        }
    </script>
</body>
</html>
