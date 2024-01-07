<%@ page import="model.entity.User" %>
<%@ page import="model.service.user.UserData" %>
<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.service.user.UserRegistry" %>
<%@ page import="model.service.message.MessageManager" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="globals.css" />
    <link rel="stylesheet" href="styleguide.css" />
    <link rel="stylesheet" href="../CSS/profiloLogopeditsaVistaP.css" />
</head>
<body>
<%
    Object sess = session.getAttribute("therapist");
    int therapistId = 0;
    if (sess != null) {
        therapistId = Integer.parseInt(sess.toString());
    }
    else {
        response.sendRedirect("../errorPage/403.html");
    }
    User therapist = new UserData().getUser(therapistId);
    PersonalInfo registry = new UserRegistry().getPersonalInfo(therapistId);
    int numberOfPatient = new MessageManager().retrieveAllTheContacts(therapistId).size();

%>
<div class="element-profilo">
    <div class="div">
        <div class="overlap">
            <div class="bg"></div>
            <div class="text-wrapper">Email:</div>
            <a href="mailto:<%=therapist.getEmail()%>" target="_blank" rel="noopener noreferrer"
            ><div class="text-wrapper-2"><%=therapist.getEmail()%></div></a>
            <div class="text-wrapper-3">Orari:</div>
            <div class="text-wrapper-4"><%=therapist.getNotificationTime()%></div>
            <div class="text-wrapper-5"><%=registry.getAddress()%></div>
            <div class="button-book">
                <div class="overlap-group"><div class="text-wrapper-6">Modifica profilo</div></div>
            </div>
            <div class="text-wrapper-7">Modifica profilo</div>
            <div class="overlap-wrapper">
                <div class="div-wrapper"><div class="text-wrapper-8">Log Out</div></div>
            </div>
            <img class="rectangle" src="img/rectangle-15.svg" />
            <div class="group">
                <div class="iconly-light-outline">
                    <div class="home-wrapper"><img class="home" src="img/home.png" /></div>
                </div>
                <img class="iconly-bold-calendar" src="img/calendar.svg" />
                <img class="iconly-light-message" src="img/message.svg" />
                <div class="iconly-light-profile"><div class="profile"></div></div>
            </div>
            <div class="text-wrapper-9">Posizione:</div>
            <div class="rectangle-2"></div>
            <div class="rectangle-3"></div>
        </div>
        <div class="overlap-2">
            <div class="profile-2"><div class="text-wrapper-10">Dr. <%=registry.getFirstname()%> <%=registry.getLastname()%></div></div>
            <div class="text-wrapper-11">+<%=registry.getPhone()%></div>
            <div class="button"><img class="icon-chevron-left" src="img/chevron-left.svg" /></div>
            <img class="image" src="img/image.png" />
            <img class="pexels-cedric" src="img/pexels-cedric-fauntleroy-4270371.png" />
        </div>
        <div class="div-2">
            <div class="div-2">
                <div class="battery">
                    <div class="capacity-wrapper"><div class="capacity"></div></div>
                    <img class="cap" src="img/cap.svg" />
                </div>
                <img class="wifi" src="img/wifi.svg" />
                <img class="cellular-connection" src="img/cellular-connection.svg" />
                <div class="time-style">
                    <p class="time"><span class="span">9:4</span> <span class="text-wrapper-12">1</span></p>
                </div>
            </div>
            <div class="component">
                <div class="ellipse"></div>
                <div class="ellipse-2"></div>
                <div class="ellipse-3"></div>
            </div>
        </div>
        <p class="pazienti">
            <span class="text-wrapper-13">Pazienti<br /></span> <span class="text-wrapper-14"><%=numberOfPatient%></span>
        </p>
        <p class="abbonamento-standard">
            <span class="text-wrapper-13">Abbonamento<br /></span> <span class="text-wrapper-14">Standard</span>
        </p>
        <img class="img" src="img/rectangle-40.svg" />
    </div>
</div>
</body>
</html>