<%@ page import="java.util.*" %>
<%@ page import="model.service.user.UserRegistry" %>
<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.entity.Schedule" %>
<%@ page import="model.service.schedule.ScheduleManager" %>
<%  ScheduleManager scheduleManagerc = new ScheduleManager();
    Integer userIdp = (Integer) session.getAttribute("id");

    if(userIdp == null) {
        response.sendRedirect("../errorPage/403.html");
    }
    else {
        int userId = (Integer) session.getAttribute("id");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
    <%@page contentType="text/html;charset=UTF-8"%>
    <meta charset="UTF-8">
    <title>TalkAId - Schedule Prenotazioni</title>
    <link rel="stylesheet" type="text/css" href="../CSS/schedule.css">
</head>
<script>
    let nuovaData
    function formattaData(data) {
        let partiData = data.split("-");
        nuovaData = partiData[2] + "/" + partiData[1];
        return nuovaData;
    }

</script>
<body onload="controllaPopup();">
<div id="calendar">
    <div class="header">
        Agenda
    </div>
    <%
        if (session.getAttribute("type") == "therapist"){
    %>
    <div class="divbar"></div>
    <h2 id="calendarTitle"></h2>
    <button class="button" id="prevMonth">Mese Indietro</button>
    <button class="button" id="nextMonth">Mese Avanti</button>
    <div id="seltimecaldiv">
        <table id="calendarTable"></table>
        <table id="timeTable"></table>
    </div>
    <form action="<%=request.getContextPath()%>/ScheduleServlet" id="dateForm" method="post" onsubmit="return validateForm()">
        <input type="hidden"  name="idTherapist" value="<%=userId%>">
        <input type="hidden" id="selectedDate" name="date">
        <input type="hidden" id="selectedTime" name="timeslot">
        <input type="hidden" id="getContextPath" value="<%=request.getContextPath()%>">
        <button class="button" type="submit" name="action" value="createNewSchedule">Aggiungi data</button>
    </form>

    <div id="popup" >
        <div id="popup-uscita">
            <p id="Contenuto"></p>
            <div class="input">
                <button id="cancella" class="button" onclick="chiudiPopup()">Chiudi</button>
            </div>
        </div>
    </div>

    <div class="divbar"></div>






    <h2>le mie prenotate</h2>
    <%
        ScheduleManager scheduleManager = new ScheduleManager();
        List<Schedule> list = scheduleManager.retrieveAllPrenotedSchedules(userId);
        if(!list.isEmpty()){
    %>
    <h3>Hai <%=scheduleManagerc.retrieveAllPrenotedSchedulesCount(userId)%> prenotazioni</h3>
    <table class="modTable">
        <tr>
            <th>Data</th>
            <th>Orario</th>
            <th>Paziente</th>
        </tr>
        <%
                for(Schedule schedule : list) {
                    UserRegistry ur = new UserRegistry();
                    PersonalInfo data= ur.getPersonalInfo(schedule.getReserved());
        %>
        <form action="<%=request.getContextPath()%>/ScheduleServlet" id="myprenotlog" method="post">
            <tr>
                <th><script>document.write(formattaData('<%=schedule.getDate()%>'));</script></th>
                <th><%=schedule.getTimeSlot()%></th>
                <%
                    if(schedule.getReserved()==0){
                %>
                <th>nessuno</th>
                <%
                }else{
                %>
                <th><%=data.getFirstname()%> <%=data.getLastname()%></th>
                <%
                    }
                %>
                <input type="hidden" name="date" value="<%=schedule.getDate()%>">
                <input type="hidden" name="timeslot" value="<%=schedule.getTimeSlot()%>">
                <input type="hidden" name="idReserved" value="<%=schedule.getReserved()%>">
                <th>
                    <button class="buttonimg" type="submit" name="action" value="deleteSchedule"><img class="profile" src="../images/schedule/scheduledel.png" alt="Elimina prenotazione"></button>
                </th>
            </tr>
        </form>
        <%
            }
        }else{
        %>
        <h3>nessuna prenotazione ricevuta</h3>
        <%
            }
        %>
    </table>




    <div class="divbar"></div>
    <h2>le mie schedule</h2>
    <%
        ScheduleManager scheduleManager2 = new ScheduleManager();
        List<Schedule> list2 = scheduleManager2.retrieveAllTherapistSchedules(userId);
        if(!list2.isEmpty()){
    %>
    <table class="modTable">
        <tr>
            <th>Data</th>
            <th>Orario</th>
            <th>Paziente</th>
        </tr>
        <%
                for(Schedule schedule : list2) {
                    UserRegistry ur = new UserRegistry();
                    PersonalInfo data = ur.getPersonalInfo(schedule.getReserved());
        %>
        <form action="<%=request.getContextPath()%>/ScheduleServlet" id="myschedlog" method="post">
            <tr>
                <th><script>document.write(formattaData('<%=schedule.getDate()%>'));</script></th>
                <th><%=schedule.getTimeSlot()%></th>
                <%
                    if(schedule.getReserved()==0){
                %>
                <th>nessuno</th>
                <%
                }else{
                %>
                <th><%=data.getFirstname()%> <%=data.getLastname()%></th>
                <%
                    }
                %>
                <input type="hidden" name="date" value="<%=schedule.getDate()%>">
                <input type="hidden" name="timeslot" value="<%=schedule.getTimeSlot()%>">
                <input type="hidden" name="idReserved" value="<%=schedule.getReserved()%>">
                <th>
                    <button class="buttonimg" type="submit" name="action" value="deleteSchedule"><img class="profile" src="../images/schedule/scheduledel.png" alt="Elimina data"></button>
                </th>
            </tr>
        </form>
        <%
            }
        }else{
        %>
        <tr>
            <h3>nessuna prenotazione creata</h3>
        </tr>
        <%
            }
        %>
    </table>
    <%
    }else{
    %>







    <div class="divbar"></div>
    <h2>le mie prenotazioni</h2>
    <%
        ScheduleManager scheduleManager4 = new ScheduleManager();
        List<Schedule> list4 = scheduleManager4.retrieveAllPatientSchedules(userId);
        if(!list4.isEmpty()){
    %>
    <table class="modTable">
        <tr>
            <th>Terapista</th>
            <th>Data</th>
            <th>Orario</th>
        </tr>
        <%
                for(Schedule schedule : list4) {
                    UserRegistry ur = new UserRegistry();
                    PersonalInfo data = ur.getPersonalInfo(schedule.getIdTherapist());
        %>
        <form action="<%=request.getContextPath()%>/ScheduleServlet" id="myprenotpaz" method="post">
            <tr>
                <th><%=data.getFirstname()%> <%=data.getLastname()%></th>
                <th><script>document.write(formattaData('<%=schedule.getDate()%>'));</script></th>
                <th><%=schedule.getTimeSlot()%></th>
                <input type="hidden"  name="idTherapist" value="<%=schedule.getIdTherapist()%>">
                <input type="hidden" name="date" value="<%=schedule.getDate()%>">
                <input type="hidden" name="timeslot" value="<%=schedule.getTimeSlot()%>">
                <th>
                    <button class="buttonimg" type="submit" name="action" value="unprenoteSchedule"><img class="profile" src="../images/schedule/scheduledel.png" alt="Elimina prenotazione"></button>
                </th>
            </tr>
        </form>
        <%
            }
        }else{
        %>
        <tr>
            <h3>nessuna prenotazione effettuata</h3>
        </tr>
        <%
            }
        %>
    </table>




    <div class="divbar"></div>
    <h2>prenotazioni disponibili</h2>
    <%
        ScheduleManager scheduleManager3 = new ScheduleManager();
        List<Schedule> list3 = scheduleManager3.retrieveAllNotPrenotedSchedules((Integer) session.getAttribute("therapist"));
        if(!list3.isEmpty()){
    %>
    <table class="modTable">
        <tr>
            <th>Terapista</th>
            <th>Data</th>
            <th>Orario</th>
        </tr>
        <%
                for(Schedule schedule : list3) {
                    UserRegistry ur = new UserRegistry();
                    PersonalInfo data = ur.getPersonalInfo(schedule.getIdTherapist());
        %>
        <form action="<%=request.getContextPath()%>/ScheduleServlet" id="disprenotpaz" method="post">
            <tr>
                <th><%=data.getFirstname()%> <%=data.getLastname()%></th>
                <th><script>document.write(formattaData('<%=schedule.getDate()%>'));</script></th>
                <th><%=schedule.getTimeSlot()%></th>
                <input type="hidden"  name="idTherapist" value="<%=schedule.getIdTherapist()%>">
                <input type="hidden" name="date" value="<%=schedule.getDate()%>">
                <input type="hidden" name="timeslot" value="<%=schedule.getTimeSlot()%>">
                <th>
                    <button class="buttonimg" type="submit" name="action" value="prenoteSchedule"><img class="profile" src="../images/schedule/scheduleadd.svg" alt="Prenotati"></button>
                </th>
            </tr>
        </form>
        <%
            }
        }else{
        %>
        <h3>nessuna prenotazione disponibile</h3>
        <%
            }
        %>
    </table>




    <%
        }
    %>



</div>
<script src="../JS/schedule.js"></script>

<script>
    function controllaPopup() {
        let urlParams = new URLSearchParams(window.location.search);
        let errorMessage = urlParams.get('errorMessage');
        let contenuto = document.getElementById("Contenuto");


        if (errorMessage && errorMessage.trim() !== "") {
            contenuto.innerHTML=errorMessage;
            document.getElementById("popup").style.display = "block";
        } else {
            document.getElementById("popup").style.display = "none";
        }
    }
    function chiudiPopup() {
        document.getElementById('popup').style.display = 'none';
    }

</script>
<div id="navbarContainer">
    <jsp:include page="navbar.jsp"></jsp:include>
    <div id="userInfo" hidden
         data-type = "<%= session.getAttribute("type")%>"
    ></div>
</div>

</body>
</html>

<%
    }
%>