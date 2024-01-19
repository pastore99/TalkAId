<%@ page import="model.service.user.UserData" %>
<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.service.user.UserRegistry" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="model.entity.Exercise" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/chart.js" integrity="sha384-9nhczxUqK87bcKHh20fSQcTGD4qq5GhayNYSYWqwBkINBhOfQLg/P5HG5lF1urn4" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../CSS/homepageTherapist.css" />
    <link rel="stylesheet" href="../CSS/viewPatientInfo.css" />
    <link rel="stylesheet" href="../CSS/RecommendationAndConditionManager.css" />
    <meta charset="utf-8" />
    <title>Paziente Selezionato</title>
</head>
<body>
<div id="container">
    <div id="navbar">
        <div id="logoImg">
            <img src="../images/TalkAidLogo.svg" alt="logoImg" id="logoIcon">
        </div>
        <div id="navIcons">
            <div id="home">
                <img src="../images/home.svg" alt="homeImg" id="homeIcon">
            </div>
            <div id="message">
                <img src="../images/message.svg" alt="messageImg" id="messageIcon">
            </div>
            <div id="agenda">
                <img src="../images/calendar.svg" alt="homeImg" id="agendaIcon">
            </div>
            <div id="goBack" onclick="showPatient()">
                <img src="../images/backIcon.svg" alt="backImg" id="goBackIcon">
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
        <div id="bottomHalf" class="roundedWhite">
            <div id="conditionsDiv" style="display: none;">
                <div class="scrollableDiv">
                    <%@ include file="patientConditionManager.jsp" %>
                </div>
            </div>
            <div id="exercisesDiv" style="display: none;">
                <div class="scrollableDiv">
                    <%@include file="exerciseRecommendation.jsp" %>
                </div>
            </div>
            <div id="content">
                <div id="patientInfo">
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
                                <p><b>Tel:</b> <%=user_selected.getPhone()%></p>
                            </td>
                        </tr>
                    </table>
                </div>

                <div id="moreInfo">
                    <div id="buttonDivs">
                        <div id="patientCondition">
                            <button onclick="showCondition()">Modifica patologie</button>
                        </div>
                        <div id="exerciseManager">
                            <button onclick="showExercises()">Raccomanda Esercizio</button>
                        </div>
                    </div>
                        <%
                            ExerciseManager exerciseManager = new ExerciseManager();
                            List<Exercise> exercises = exerciseManager.retrievePatientExerciseDone(patientId);
                            if (exercises != null && !exercises.isEmpty()) {
                                StringBuilder labels = new StringBuilder();
                                StringBuilder data = new StringBuilder();
                                for (Exercise exercise : exercises) {
                                    labels.append("'").append(exercise.getInsertionDate()).append("', ");
                                    data.append(exercise.getEvaluation()).append(", ");
                                }
                                labels = new StringBuilder(labels.substring(0, labels.length() - 2));
                                data = new StringBuilder(data.substring(0, data.length() - 2));
                            %>
                            <div id="chartDiv">
                                <canvas id="myChart"></canvas>
                            </div>

                            <script>
                                const ctx = $('#myChart');

                                new Chart(ctx, {
                                    type: 'line',
                                    data: {
                                        labels: [<%=labels.toString()%>],
                                        datasets: [{
                                            label: 'Andamento',
                                            data: [<%= data.toString()%>],
                                            borderWidth: 1
                                        }]
                                    },
                                    options: {
                                        maintainAspectRatio: false,
                                        scales: {
                                            y: {
                                                beginAtZero: true
                                            }
                                        }
                                    }
                                });



                            </script>

                            <%
                                } else {
                            %>
                                <div id="noProgress"> <p>Nessun esercizio svolto</p> </div>
                            <%
                            }
                        %>
                </div>
            </div>
            <%} %>
            </div>
        </div>
    </div>
</body>
<script src="../JS/viewPatientInfo.js"></script>
</html>
