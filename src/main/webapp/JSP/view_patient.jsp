<%@ page import="model.entity.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.service.user.UserRegistry" %>
<%@ page import="model.service.user.UserData" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="../CSS/view_patient.css" />
    <title>Paziente Selezionato</title>

</head>
<body>
<div class="element-home-logopedista">
    <div class="div">
        <!--INFO PATIENT-->
        <div class="pop-up">
            <div class="overlap-group">
            <%

                if(session.getAttribute("type")==null || !session.getAttribute("type").equals("therapist") ||  request.getParameter("patientID") == null) {
                    response.sendRedirect("../errorPage/403.html");
                }else {
                    int patientId = Integer.parseInt(request.getParameter("patientID"));

                    PersonalInfo user_selected = new UserRegistry().getPersonalInfo(patientId);
                    User user = new UserData().getUser(patientId);
                    if (user.getIdTherapist() != (Integer) session.getAttribute("id")) {
                        response.sendRedirect("../errorPage/403.html");
                    }
                    String email = user.getEmail();

            %>
                <table>
                    <tr>
                        <td><svg xmlns="http://www.w3.org/2000/svg" height="16" width="18" viewBox="0 0 496 512"><path d="M248 8C111 8 0 119 0 256s111 248 248 248 248-111 248-248S385 8 248 8zm0 96c48.6 0 88 39.4 88 88s-39.4 88-88 88-88-39.4-88-88 39.4-88 88-88zm0 344c-58.7 0-111.3-26.6-146.5-68.2 18.8-35.4 55.6-59.8 98.5-59.8 2.4 0 4.8 .4 7.1 1.1 13 4.2 26.6 6.9 40.9 6.9 14.3 0 28-2.7 40.9-6.9 2.3-.7 4.7-1.1 7.1-1.1 42.9 0 79.7 24.4 98.5 59.8C359.3 421.4 306.7 448 248 448z"/></svg></td>
                        <td>
                            <p><b>Nome:</b> <%=user_selected.getFirstname()%></p>
                            <p><b>Indirizzo:</b> <%=user_selected.getAddress()%> </p>
                            <p><b>Email:</b> <%=email%> </p>
                        </td>
                        <td>
                            <p><b>Cognome:</b> <%=user_selected.getLastname()%></p>
                            <p><b>Data di nascita:</b> <%=user_selected.getDateOfBirth()%> </p>
                            <p><b>Tel:</b> <%=user_selected.getPhone()%> </p>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- NAVBAR -->
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
            <div class="text-wrapper-3">Dr.</br> <%=(String) session.getAttribute("name") + " " + (String)session.getAttribute("surname")%> </div>
            <img class="ellipse-2" src="../images/homeTherapist/ellipse-94.svg" />
        </div>
    </div>
</div>
<!--EXAMPLE PROGRESS-->
<div class="singolo-paziente">
    <div class="group-5" onclick="redirectToGestioneMalattie()">
        <div class="overlap-3"><div class="text-wrapper-2">Modifica patologie</div></div>
    </div>
    <div class="group-6">
        <div class="overlap-4"><div class="text-wrapper-3">Raccomanda Esercizio</div></div>
    </div>
    <div class="button-chat"><img class="iconly-light-chat" src="../images/homeTherapist/iconly-light-chat.svg" /></div>
    <div class="overlap-5">
        <img class="img" src="../images/homeTherapist/group-181.png" />
        <img class="vector" src="../images/homeTherapist/vector-5.svg" />
        <div class="text-wrapper-4">Days</div>
        <div class="text-wrapper-5">Points</div>
        <img class="line-2" src="../images/homeTherapist/line-6.svg" />
        <img class="line-3" src="../images/homeTherapist/line-7.svg" />
        <img class="line-4" src="../images/homeTherapist/line-8.svg" />
        <img class="line-5" src="../images/homeTherapist/line-6.svg" />
        <div class="ellipse-2"></div>
        <div class="ellipse-3"></div>
        <div class="ellipse-4"></div>
        <div class="ellipse-5"></div>
        <div class="text-wrapper-6">10/9</div>
        <div class="text-wrapper-7">11/9</div>
        <div class="text-wrapper-8">12/9</div>
        <div class="text-wrapper-9">13/9</div>
    </div>
    <div class="text-wrapper-10">Andamento</div>
</div>


<script>
    function redirectToGestioneMalattie() {
        window.location.href = 'PatientConditionManager.jsp?userId='+<%=user_selected.getIdUser() %>;
    }
</script>
 <%} %>
</body>
</html>
