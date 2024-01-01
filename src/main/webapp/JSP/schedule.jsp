<!DOCTYPE html>
<html>
<head>
    <title>schedule</title>
    <link rel="stylesheet" type="text/css" href="../CSS/schedule.css">
</head>
<body>
<div id="calendar">
    <h1>aggiunta calendario prenotazione</h1>
    <h2 id="calendarTitle"></h2>
    <button id="prevMonth">Previous Month</button>
    <button id="nextMonth">Next Month</button>
    <table id="calendarTable"></table>
    <table id="timeTable"></table>
    <form action="<%=request.getContextPath()%>/ScheduleServlet" id="dateForm" method="post">
        <input type="hidden"  name="idTherapist" value="0">
        <input type="hidden" id="selectedDate" name="date">
        <input type="hidden" id="selectedTime" name="timeslot">
        <input type="submit" name="action" value="createNewSchedule">
        <input type="submit" name="action" value="deleteSchedule">
    </form>

    <h1>paziente</h1>
    <h2>prenotazioni disponibili</h2>
    <table id="newTable">
        <thead>
        <tr>
            <th>Terapista</th>
            <th>Data</th>
            <th>Orario</th>
            <th>Paziente</th>
        </tr>
        </thead>
        <tbody>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        </tbody>
    </table>
    <h2>le mie prenotazioni</h2>
    <table id="newTable">
        <thead>
        <tr>
            <th>Terapista</th>
            <th>Data</th>
            <th>Orario</th>
            <th>Paziente</th>
        </tr>
        </thead>
        <tbody>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        </tbody>
    </table>
    <h1>logopedista</h1>
    <h2>le mie prenotate</h2>
    <table id="newTable">
        <thead>
        <tr>
            <th>Terapista</th>
            <th>Data</th>
            <th>Orario</th>
            <th>Paziente</th>
        </tr>
        </thead>
        <tbody>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        </tbody>
    </table>
    <h2>le mie schedule</h2>
    <table id="newTable">
        <thead>
        <tr>
            <th>Terapista</th>
            <th>Data</th>
            <th>Orario</th>
            <th>Paziente</th>
        </tr>
        </thead>
        <tbody>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        <th>row 1</th>
        </tbody>
    </table>

</div>
<script src="../JS/schedule.js"></script>
</body>
</html>
