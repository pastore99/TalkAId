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
    <form action="<%=request.getContextPath()%>/ScheduleServlet" id="dateForm">
        <input type="hidden"  name="idTherapist" value="0">
        <input type="hidden" id="selectedDate" name="date">
        <input type="hidden" id="selectedTime" name="timeslot">
        <input type="submit" name="action" value="createNewSchedule">
        <input type="submit" name="action" value="deleteSchedule">
    </form>
</div>
<script src="../JS/schedule.js"></script>
</body>
</html>
