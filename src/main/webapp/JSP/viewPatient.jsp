<%@ page import="model.entity.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.service.user.UserRegistry" %>
<%@ page import="model.service.user.UserData" %>
<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
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
    <div class="group-6" onclick="redirectToGestioneRaccomandazioni()">
        <div class="overlap-4"><div class="text-wrapper-3">Raccomanda Esercizio</div></div>
    </div>
    <div class="button-chat"><img class="iconly-light-chat" src="../images/homeTherapist/iconly-light-chat.svg" /></div>
    <!--Progress-->
    <div class="text-wrapper-10">Andamento</div>

        <%
            ExerciseManager exerciseManager = new ExerciseManager();
            List<Exercise> exercises = exerciseManager.retrievePatientExerciseDone(patientId);
            if (exercises == null || exercises.isEmpty()) {
        %>
        <div class="overlap-5"><p class="errorProgress">Nessun esercizio svolto</p></div>
        <%
        } else {
            String labels = "";
            String data = "";
            for (Exercise exercise : exercises) {
                labels += "'" + exercise.getInsertionDate() + "', ";
                data += exercise.getEvaluation() + ", ";
            }
            labels = labels.substring(0, labels.length() - 2);
            data = data.substring(0, data.length() - 2);
        %>
        <!-- Create Chart -->
        <div class="overlap-5-2"><canvas id="myChart" width="400" height="200"></canvas></div>
        <script>
            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: [<%= labels %>],
                    datasets: [{
                        label: 'Andamento Esercizi',
                        data: [<%= data %>],
                        borderWidth: 1,
                        borderColor: 'rgba(75, 192, 192, 1)',
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    }]
                },
                options: {
                    scales: {
                        x: [{
                            display: false,
                        }],
                        y: {
                            title: {
                                display: true,
                                text: 'Valutazione'
                            },
                            beginAtZero: true,
                            max: 100
                        }
                    }
                }
            });
        </script>
        <%
            }
        %>
    </div>

</div>

<script>
    function redirectToGestioneMalattie() {
        window.location.href = 'homeConditionManager.jsp?userId='+<%=user_selected.getIdUser() %>;
    }
    function redirectToGestioneRaccomandazioni() {
        window.location.href = 'homeExerciseRecommendation.jsp?userId='+<%=user_selected.getIdUser() %>;
    }
</script>
 <%} %>
</body>
</html>
