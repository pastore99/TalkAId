<%@ page import="model.service.user.UserData" %>
<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE>
<html lang="it">
<body>
<%
    int patientIdEX = 0;
    if(session.getAttribute("type")==null || !session.getAttribute("type").equals("therapist") ||  request.getParameter("patientID") == null) {
        response.sendRedirect("../errorPage/403.html");
    }else {
        patientIdEX = Integer.parseInt(request.getParameter("patientID"));

        User user = new UserData().getUser(patientIdEX);
        if (user.getIdTherapist() != (Integer) session.getAttribute("id")) {
            response.sendRedirect("../errorPage/403.html");
        }
    }
    ExerciseManager ExerciseService= new ExerciseManager();

    List<ExerciseGlossary> list_Exercisedone = ExerciseService.retrieveAllPatientExerciseGlossaryDone(patientIdEX);
    List<ExerciseGlossary> list_ExerciseNOTdone = ExerciseService.retrieveAllPatientExerciseGlossaryNotDone(patientIdEX);
%>
<table>
    <caption><b>Esercizi Fatti / Già Raccomandati</b></caption>
    <thead>
    <tr>
        <th></th>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Tipo</th>
        <th>Difficoltà</th>
        <th>Target</th>
    </tr>
    </thead>
    <tbody>
    <% for (ExerciseGlossary exerciseGlossary : list_Exercisedone) { %>
    <tr>
        <td>●</td>
        <td><%= exerciseGlossary.getExerciseName() %></td>
        <td><%= exerciseGlossary.getExerciseDescription() %></td>
        <td><%= exerciseGlossary.getType() %></td>
        <td><%= exerciseGlossary.getDifficulty()%></td>
        <td><%= exerciseGlossary.getTarget()%></td>
    </tr>
    <% } %>
    </tbody>
</table>
<hr>
<table>
    <caption><b>Raccomanda Esercizi</b></caption>
    <thead>
    <tr>
        <th></th>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Tipo</th>
        <th>Difficoltà</th>
        <th>Target</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (ExerciseGlossary exerciseGlossary : list_ExerciseNOTdone) { %>
    <tr>
        <td>●</td>
        <td><%= exerciseGlossary.getExerciseName() %></td>
        <td><%= exerciseGlossary.getExerciseDescription() %></td>
        <td><%= exerciseGlossary.getType() %></td>
        <td><%= exerciseGlossary.getDifficulty()%></td>
        <td><%= exerciseGlossary.getTarget()%></td>
        <form action="../exerciseRecommendation" method="post">
            <td>
                <input type="hidden" name="idExercise" value="<%= exerciseGlossary.getIdExercise() %>">
                <input type="hidden" name="idPatient" value="<%= patientIdEX %>">
                <input type="submit" class="buttonApprove" value="Raccomanda" name="operation">
            </td>
        </form>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>

