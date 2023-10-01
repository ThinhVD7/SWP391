<!DOCTYPE html>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 Page Not Found</title>
    <style>
        body {
            background-color: #E6F7FF; /* Baby Blue background color */
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 100px;
        }

        h1 {
            color: #3366CC; /* Blue text color */
            font-size: 3rem;
        }

        p {
            color: #666;
            font-size: 1.2rem;
        }

        a {
            color: #3366CC;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>404 - Page Not Found</h1>
    <p>Oops, the page you're looking for doesn't exist.</p>
    <p><a href="javascript:history.go(-1);" style="color:#002DF7; text-decoration: none;">Return to previous page</a> or go to <a href="home"> home page</a> of the web.</p></h3>

</body>
</html>