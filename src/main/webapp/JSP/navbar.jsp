<!DOCTYPE html>
<html lang="it">
<body>
<% if(session.getAttribute("type")==null){
    response.sendRedirect("../errorPage/403.html");
}%>
    <div id="navbarDiv" >
        <div id="homeNavbar" class="divButtons">
            <img id="homeIcon" class="navbarIcon" src="../images/home.svg" alt="homeIcon">
            <img id="homeIconSelected" class="navbarIcon" src="../images/homeSelected.svg" alt="homeIconSelected">
        </div>
        <div id="messageNavbar" class="divButtons">
            <img id="messageIcon" class="navbarIcon" src="../images/message.svg" alt="messageIcon">
            <img id="messageIconSelected" class="navbarIcon" src="../images/messageSelected.svg" alt="messageIconSelected">
        </div>
        <div id="calendarNavbar" class="divButtons">
            <img id="calendarIcon" class="navbarIcon" src="../images/calendar.svg" alt="calendarIcon">
            <img id="calendarIconSelected" class="navbarIcon" src="../images/calendarSelected.svg" alt="calendarIconSelected">
        </div>
        <div id="profileNavbar" class="divButtons">
            <img id="profileIcon" class="navbarIcon" src="../images/profile.svg" alt="profileIcon">
            <img id="profileIconSelected" class="navbarIcon" src="../images/profileSelected.svg" alt="profileIconSelected">
        </div>
    </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
<script src="../JS/navbar.js"></script>
<link rel="stylesheet" href="../CSS/navbar.css">
</html>
