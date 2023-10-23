<!DOCTYPE html>
<html>
<head>
    <title>Delete Records with Confirmation</title>
    <style>
        /* Style for the modal dialog */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
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
        }
    </style>
</head>
<body>
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
        }
    </script>
</body>
</html>
