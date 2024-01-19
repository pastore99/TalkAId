<%@ page import="model.entity.UserInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.service.user.UserData" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
    <title>Homepage</title>
</head>
<body>
    <div id="container">
        <div id="navbar">
            <div id="logoImg">
                <img src="../images/TalkAidLogo.svg" alt="logoImg" id="logoIcon">
            </div>
            <div id="navIcons">
                <div id="home">
                    <img src="../images/homeSelected.svg" alt="homeImg" id="homeSelectedIcon">
                    <img src="../images/home.svg" alt="homeImg" id="homeIcon">
                </div>
                <div id="message">
                    <img src="../images/message.svg" alt="messageImg" id="messageIcon">
                </div>
                <div id="agenda">
                    <img src="../images/calendar.svg" alt="homeImg" id="agendaIcon">
                </div>
                <div id="ia">
                    <img src="../images/AI.png" alt="iaIcon" id="iaIcon">
                    <img src="../images/AISelected.png" alt="iaIcon" id="iaIconSelected">
                </div>
            </div>
            <div id="docInfo">
                <div id="docIconDiv">
                    <img src="../images/homeTherapist/doctor.png" alt="docImg" id="docIcon">
                </div>
                <div id="docName">
                    <p><%=session.getAttribute("name") + " " + session.getAttribute("surname")%></p>
                </div>
            </div>
        </div>
        <div id="rightHalf">
            <div id="topHalf">
                <div id="searchDiv" class="roundedWhite">
                    <div id="lensIcon">
                        <img src="../images/homeTherapist/material-symbols-search-rounded.svg" alt="lensIcon"/>
                    </div>
                    <div id="textInput">
                        <input type="text" id="searchInput" placeholder="Cerca per nome o cognome">
                    </div>
                </div>
                <div id="inviteDiv" class="roundedWhite" onclick="openInvitePopup()">
                    <p>Invita paziente</p>
                </div>

            </div>
            <div id="bottomHalf" class="roundedWhite">
                <div id="content">

                    <div id="aiContainer">
                        <%@include file="aiApprove.jsp" %>
                    </div>

                    <div id="tableContainer">
                        <div class="scrollableDiv">
                            <table>
                                <thead class="table-header">
                                <tr>
                                    <th></th>
                                    <th>Paziente</th>
                                    <th>Inizio Terapia</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    if(session.getAttribute("type")!=null && session.getAttribute("type").equals("therapist")){
                                        ArrayList<UserInfo> listUser= new UserData().getUsersAndPersonalInfoByIdTherapist((Integer) session.getAttribute("id"));
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        for(UserInfo u: listUser){
                                %>
                                <tr class="hoverable-row">
                                    <td><svg xmlns="http://www.w3.org/2000/svg" height="16" width="18" viewBox="0 0 496 512"><path d="M248 8C111 8 0 119 0 256s111 248 248 248 248-111 248-248S385 8 248 8zm0 96c48.6 0 88 39.4 88 88s-39.4 88-88 88-88-39.4-88-88 39.4-88 88-88zm0 344c-58.7 0-111.3-26.6-146.5-68.2 18.8-35.4 55.6-59.8 98.5-59.8 2.4 0 4.8 .4 7.1 1.1 13 4.2 26.6 6.9 40.9 6.9 14.3 0 28-2.7 40.9-6.9 2.3-.7 4.7-1.1 7.1-1.1 42.9 0 79.7 24.4 98.5 59.8C359.3 421.4 306.7 448 248 448z"/></svg></td>
                                    <td><%=u.getFirstname()%> <%=u.getLastname()%></td>
                                    <td><%= sdf.format(u.getActivationDate()) %></td>
                                    <td>
                                        <input type="hidden" name="userId" value="<%= u.getId() %>">
                                        <button onclick="viewPatient(<%=u.getId()%>)" class="tdButton">Visualizza</button>
                                    </td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<div id="invitePopup" style="display: none;">
    <%@include file="invitePatient.jsp" %>
</div>
</body>

<link rel="stylesheet" href="../CSS/homepageTherapist.css" />
<link rel="stylesheet" href="../CSS/acceptanceExercisesAI.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
<script src="../JS/homepageTherapist.js"></script>
</html>
