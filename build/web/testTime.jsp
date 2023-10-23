<!DOCTYPE html>
<html>
<head>
    <title>Time Duration Input</title>
    <style>
        /* Style for the input containers */
        .input-container {
            display: inline-block;
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <form>
         <!--Input for hours--> 
<!--        <div class="input-container">
            <label for="hours">Hours:</label>
            <input type="number" id="hours" name="hours" min="0" max ="59 "placeholder="0">
        </div>
        -->
        <!-- Input for minutes -->
        <div class="input-container">
            <label for="hour">Hour:</label>
            <input type="number" id="hour" name="hours" min="0" placeholder="0">
        </div>
        <!-- Input for minutes -->
        <div class="input-container">
            <label for="minutes">Minutes:</label>
            <input type="number" id="minutes" name="minutes" min="0" max="59" placeholder="0">
        </div>
        
        <!-- Input for seconds -->
        <div class="input-container">
            <label for="seconds">Seconds:</label>
            <input type="number" id="seconds" name="seconds" min="0" max="59" placeholder="0">
        </div>
        
        <!-- Submit button (you can use this to submit the form) -->
        <input type="submit" value="Submit">
    </form>
</body>
</html>
