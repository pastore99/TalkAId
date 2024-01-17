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
    <link rel="stylesheet" href="../CSS/profiloLogopedistaVistaP.css" />
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
                <button class="overlap-group">
                    <div class="text-wrapper-6">Prenota appuntamento</div>
                </button>
            </div>

            <div class="overlap-wrapper">
                <button class="div-wrapper">
                    <div class="text-wrapper-8">Messaggia</div>
                </button>
            </div>


            <div class="text-wrapper-9">Posizione:</div>
            <div class="rectangle-2"></div>
            <div class="rectangle-3"></div>
        </div>

        <div class="overlap-2">
            <div class="profile-2"><div class="text-wrapper-10">Dr. <%=registry.getFirstname()%> <%=registry.getLastname()%></div></div>
            <div class="text-wrapper-11">+<%=registry.getPhone()%></div>

            <img class="pexels-cedric" src="../Image/profilo_utente.png" />
        </div>



        <p class="pazienti">
            <span class="text-wrapper-13">Pazienti<br /></span> <span class="text-wrapper-14"><%=numberOfPatient%></span>
        </p>
        <p class="abbonamento-standard">
            <span class="text-wrapper-13">Abbonamento<br /></span> <span class="text-wrapper-14">Standard</span>
        </p>
    </div>
</div>
</body>
</html>