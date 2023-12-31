<!DOCTYPE html>
<html>
<head>
    <title>Simple Calendar</title>
    <link rel="stylesheet" type="text/css" href="../CSS/schedule.css">
</head>
<body>
<div id="calendar">
    <h2 id="calendarTitle"></h2>
    <button id="nextMonth">Next Month</button>
    <button id="prevMonth">Previous Month</button>
    <table id="calendarTable"></table>
    <table id="timeTable"></table>
    <form id="dateForm">
        <input type="hidden" id="selectedDate" name="selectedDate">
        <input type="hidden" id="selectedTime" name="selectedTime">
        <input type="submit" value="Submit">
    </form>
</div>
<script src="../JS/schedule.js"></script>
</body>
</html>
