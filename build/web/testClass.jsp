<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Centered and Bottom-Right Form Buttons</title>
    <style>
        /* Container div */
        .container {
            width: 300px; /* Set a width for the container */
            height: 200px; /* Set a height for the container */
            border: 1px solid #ccc;
            position: relative; /* Needed for absolute positioning */
        }

        /* Centered form button */
        .centered-button {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        /* Bottom-right form button */
        .bottom-right-button {
            position: absolute;
            bottom: 10px;
            right: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="#" method="post">
            <button class="centered-button" type="submit">Centered Button</button>
        </form>
        
        <form action="#" method="post">
            <button class="bottom-right-button" type="submit">Bottom-Right Button</button>
        </form>
    </div>
</body>
</html>
