<%@ page import="model.entity.*"%>
<%@ page import="model.service.user.UserData"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <meta charset="utf-8" />

    <link rel="stylesheet" href="../CSS/view_patient.css" />
    <title>Home</title>
</head>
<body>
<div class="element-home-logopedista">
    <div class="div">
        <div class="pop-up">
            <div class="table-container" id="tableContainer">
                <%@ include file="exerciseRecommendation.jsp" %>
            </div>
        </div>
        <!--NAVBAR-->
        <div class="overlap-3">
            <div class="rectangle-2"></div>
            <div class="group-2">
                <div class="iconly-light-outline"><img class="home" src="../images/homeTherapist/home.png" /></div>
                <img class="iconly-bold-calendar" src="../images/homeTherapist/iconly-bold-calendar.svg" />
                <img class="iconly-light-message" src="../images/homeTherapist/iconly-light-message.svg" />
                <img class="iconly-light-profile" src="../images/homeTherapist/iconly-light-profile.svg" />
            </div>
            <img class="img" src="../images/homeTherapist/doctor.png" />
            <img class="logovettoriale" src="../images/homeTherapist/logovettoriale-1.png" />
            <img class="line" src="../images/homeTherapist/line-9.svg" />
            <div class="text-wrapper-3">Dr.</br> <%=(String) session.getAttribute("name") + " " + (String)session.getAttribute("surname")%></div>
            <img class="ellipse-2" src="../images/homeTherapist/ellipse-94.svg" />
        </div>
    </div>
</div>
<script src="../JS/homeTherapist.js"></script>
</body>
</html>