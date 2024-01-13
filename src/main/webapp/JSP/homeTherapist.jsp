<%@ page import="model.entity.*"%>
<%@ page import="model.service.user.UserData"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="../CSS/homeTherapistGuide.css" />
    <link rel="stylesheet" href="../CSS/homeTherapist.css" />
    <title>Home</title>
</head>
<body>
<div class="element-home-logopedista">
    <div class="div">
        <div class="pop-up">
            <div class="text-wrapper-2">PAZIENTI</div>
            <div class="overlap-group">
                <div class="table-container" id="tableContainer">

                    <table>
                        <thead class="table-header">
                        <tr>
                            <th></th> <!--icon-->
                            <th></th> <!--full name-->
                            <th>Inizio Terapia</th> <!--start date of therapy-->
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            if(session.getAttribute("type")!=null && session.getAttribute("type").equals("therapist")){
                                @SuppressWarnings("unchecked")
                                ArrayList<UserInfo> list_user= new UserData().getUsersAndPersonalInfoByIdTherapist((Integer) session.getAttribute("id"));
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                                for(UserInfo u: list_user){
                        %>
                        <!-- <form action="../view_patientServlet" method="post" -->
                        <tr class="hoverable-row">
                            <td><svg xmlns="http://www.w3.org/2000/svg" height="16" width="18" viewBox="0 0 496 512"><path d="M248 8C111 8 0 119 0 256s111 248 248 248 248-111 248-248S385 8 248 8zm0 96c48.6 0 88 39.4 88 88s-39.4 88-88 88-88-39.4-88-88 39.4-88 88-88zm0 344c-58.7 0-111.3-26.6-146.5-68.2 18.8-35.4 55.6-59.8 98.5-59.8 2.4 0 4.8 .4 7.1 1.1 13 4.2 26.6 6.9 40.9 6.9 14.3 0 28-2.7 40.9-6.9 2.3-.7 4.7-1.1 7.1-1.1 42.9 0 79.7 24.4 98.5 59.8C359.3 421.4 306.7 448 248 448z"/></svg></td>
                            <td><%=u.getFirstname()%> <%=u.getLastname()%></td>
                            <td><%= sdf.format(u.getActivationDate()) %></td>
                            <td>
                                <input type="hidden" name="userId" value="<%= u.getId() %>">
                                <!-- <button type="submit" class="button">Visualizza</button> -->
                                <buttom onclick="viewPatient(<%=u.getId()%>)" class="button">Visualizza</buttom>
                            </td>
                        </tr>
                        <!-- </form> -->
                        <%
                                }
                            }
                        %>
                        </tbody>
                    </table>
                </div>

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
        <div class="material-symbols-wrapper">
            <input type="text" id="searchInput" placeholder="Cerca per nome o cognome">
            <img class="material-symbols" src="../images/homeTherapist/material-symbols-search-rounded.svg" />
        </div>
        <div class="overlap">
            <div class="button-only-text"><button class="button" onclick="openInvitePopup()">Invita Paziente</button></div>
        </div>
    </div>
</div>
<!--POPUP INVITE PATIENT-->
<div id="invitePopup" style="display: none;">
    <%@include file="invitePatient.jsp" %>
</div>
<script src="../JS/homeTherapist.js"></script>
</body>
</html>