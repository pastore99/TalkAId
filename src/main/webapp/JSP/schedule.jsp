<%@ page import="java.util.*" %>
<%@ page import="model.service.user.UserRegistry" %>
<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.entity.Schedule" %>
<%@ page import="model.service.schedule.ScheduleManager" %>
<%
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
    <%@page contentType="text/html;charset=UTF-8"%>
    <meta charset="UTF-8">
    <title>TalkAId - Schedule Prenotazioni</title>
    <link rel="stylesheet" type="text/css" href="../CSS/schedule.css">
</head>
<body>
<div id="calendar">
    <%
        if (session.getAttribute("type") == "therapist"){
    %>
    <h1>aggiunta calendario prenotazione</h1>
    <h2 id="calendarTitle"></h2>
    <button id="prevMonth">Previous Month</button>
    <button id="nextMonth">Next Month</button>
    <table id="calendarTable"></table>
    <table id="timeTable"></table>
    <form action="<%=request.getContextPath()%>/ScheduleServlet" id="dateForm" method="post">
        <input type="hidden"  name="idTherapist" value="<%=userId%>">
        <input type="hidden" id="selectedDate" name="date">
        <input type="hidden" id="selectedTime" name="timeslot">
        <input type="submit" name="action" value="createNewSchedule">
    </form>








    <h1>logopedista</h1>
    <h2>le mie prenotate</h2>
        <table class="modTable">
            <thead>
            <tr>
                <th>Terapista</th>
                <th>Data</th>
                <th>Orario</th>
                <th>Paziente</th>
            </tr>
            </thead>
            <%
                    ScheduleManager scheduleManager = new ScheduleManager();
                    List<Schedule> list = scheduleManager.retrieveAllPrenotedSchedules(userId);
                    if(list!=null){
                        for(Schedule schedule : list) {
                            UserRegistry ur = new UserRegistry();
                            PersonalInfo data1 = ur.getPersonalInfo(schedule.getIdTherapist());
                            PersonalInfo data2 = ur.getPersonalInfo(schedule.getReserved());
            %>
            <form action="<%=request.getContextPath()%>/ScheduleServlet" id="myprenotlog" method="post">
            <tr>
            <th><%=data1.getFirstname()%> <%=data1.getLastname()%></th>
            <th><%=schedule.getDate()%></th>
            <th><%=schedule.getTimeSlot()%></th>
                <%
                    if(schedule.getReserved()==0){
                %>
                <th>nessuno</th>
                <%
                }else{
                %>
                <th><%=data2.getFirstname()%> <%=data2.getLastname()%></th>
                <%
                    }
                %>
            <input type="hidden" name="date" value="<%=schedule.getDate()%>">
            <input type="hidden" name="timeslot" value="<%=schedule.getTimeSlot()%>">
            <input type="hidden" name="idReserved" value="<%=schedule.getReserved()%>">
            <th>
                <input type="submit" name="action" value="modifySchedule">
            </th>
            </tr>
            </form>
            <%
                }
            }else{
            %>
            <tr>
                <th>ancora nessuna prenotazione ricevuta</th>
            </tr>
            <%
                }
            %>
        </table>




    <h2>le mie schedule</h2>
        <table class="modTable">
            <thead>
            <tr>
                <th>Terapista</th>
                <th>Data</th>
                <th>Orario</th>
                <th>Paziente</th>
            </tr>
            </thead>
            <%
                ScheduleManager scheduleManager2 = new ScheduleManager();
                List<Schedule> list2 = scheduleManager2.retrieveAllTherapistSchedules(userId);
                if(list2!=null){
                    for(Schedule schedule : list2) {
                        UserRegistry ur = new UserRegistry();
                        PersonalInfo data1 = ur.getPersonalInfo(schedule.getIdTherapist());
                        PersonalInfo data2 = ur.getPersonalInfo(schedule.getReserved());
            %>
            <form action="<%=request.getContextPath()%>/ScheduleServlet" id="myschedlog" method="post">
                <tr>
                    <th><%=data1.getFirstname()%> <%=data1.getLastname()%></th>
                    <th><%=schedule.getDate()%></th>
                    <th><%=schedule.getTimeSlot()%></th>
                    <%
                        if(schedule.getReserved()==0){
                    %>
                    <th>nessuno</th>
                    <%
                    }else{
                    %>
                    <th><%=data2.getFirstname()%> <%=data2.getLastname()%></th>
                    <%
                        }
                    %>
                    <input type="hidden" name="date" value="<%=schedule.getDate()%>">
                    <input type="hidden" name="timeslot" value="<%=schedule.getTimeSlot()%>">
                    <input type="hidden" name="idReserved" value="<%=schedule.getReserved()%>">
                    <th>
                        <input type="submit" name="action" value="modifySchedule">
                        <br>
                        <input type="submit" name="action" value="deleteSchedule">
                    </th>
                </tr>
            </form>
            <%
                }
            }else{
            %>
            <tr>
                <th>ancora nessuna prenotazione ricevuta</th>
            </tr>
            <%
                }
            %>
        </table>
    <%
        }else{
    %>







    <h1>paziente <%=userId%></h1>
    <h2>prenotazioni disponibili</h2>
        <table class="modTable">
            <thead>
            <tr>
                <th>Terapista</th>
                <th>Data</th>
                <th>Orario</th>
                <th>Paziente</th>
            </tr>
            </thead>
            <%
                ScheduleManager scheduleManager3 = new ScheduleManager();
                List<Schedule> list3 = scheduleManager3.retrieveAllNotPrenotedSchedules((Integer) session.getAttribute("therapist"));
                if(list3!=null){
                    for(Schedule schedule : list3) {
                        UserRegistry ur = new UserRegistry();
                        PersonalInfo data1 = ur.getPersonalInfo(schedule.getIdTherapist());
                        PersonalInfo data2 = ur.getPersonalInfo(schedule.getReserved());
            %>
            <form action="<%=request.getContextPath()%>/ScheduleServlet" id="disprenotpaz" method="post">
                <tr>
                    <th><%=data1.getFirstname()%> <%=data1.getLastname()%></th>
                    <th><%=schedule.getDate()%></th>
                    <th><%=schedule.getTimeSlot()%></th>
                    <%
                        if(schedule.getReserved()==0){
                    %>
                    <th>nessuno</th>
                    <%
                    }else{
                    %>
                    <th><%=data2.getFirstname()%> <%=data2.getLastname()%></th>
                    <%
                        }
                    %>
                    <input type="hidden"  name="idTherapist" value="<%=schedule.getIdTherapist()%>">
                    <input type="hidden" name="date" value="<%=schedule.getDate()%>">
                    <input type="hidden" name="timeslot" value="<%=schedule.getTimeSlot()%>">
                    <th>
                        <input type="submit" name="action" value="prenoteSchedule">
                    </th>
                </tr>
            </form>
            <%
                }
            }else{
            %>
            <tr>
                <th>ancora nessuna prenotazione ricevuta</th>
            </tr>
            <%
                }
            %>
        </table>




    <h2>le mie prenotazioni</h2>
        <table class="modTable">
            <thead>
            <tr>
                <th>Terapista</th>
                <th>Data</th>
                <th>Orario</th>
                <th>Paziente</th>
            </tr>
            </thead>
            <%
                ScheduleManager scheduleManager4 = new ScheduleManager();
                List<Schedule> list4 = scheduleManager4.retrieveAllPatientSchedules(userId);
                if(list4!=null){
                    for(Schedule schedule : list4) {
                        UserRegistry ur = new UserRegistry();
                        PersonalInfo data1 = ur.getPersonalInfo(schedule.getIdTherapist());
                        PersonalInfo data2 = ur.getPersonalInfo(schedule.getReserved());
            %>
            <form action="<%=request.getContextPath()%>/ScheduleServlet" id="myprenotpaz" method="post">
                <tr>
                    <th><%=data1.getFirstname()%> <%=data1.getLastname()%></th>
                    <th><%=schedule.getDate()%></th>
                    <th><%=schedule.getTimeSlot()%></th>
                    <%
                        if(schedule.getReserved()==0){
                    %>
                    <th>nessuno</th>
                    <%
                    }else{
                    %>
                    <th><%=data2.getFirstname()%> <%=data2.getLastname()%></th>
                    <%
                        }
                    %>
                    <input type="hidden"  name="idTherapist" value="<%=schedule.getIdTherapist()%>">
                    <input type="hidden" name="date" value="<%=schedule.getDate()%>">
                    <input type="hidden" name="timeslot" value="<%=schedule.getTimeSlot()%>">
                    <th>
                        <input type="submit" name="action" value="unprenoteSchedule">
                    </th>
                </tr>
            </form>
            <%
                }
            }else{
            %>
            <tr>
                <th>ancora nessuna prenotazione ricevuta</th>
            </tr>
            <%
                }
            %>
        </table>

    <%
        }
    %>



</div>
<script src="../JS/schedule.js"></script>
</body>
</html>

<%
    }
%>