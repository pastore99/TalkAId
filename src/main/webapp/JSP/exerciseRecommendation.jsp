<%@ page import="java.util.ArrayList" %>
<%@ page import="model.service.condition.ConditionManager" %>
<%@ page import="model.service.user.UserData" %>
<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Raccomanda Esercizio</title>
    <link rel="stylesheet" href="../CSS/RecommendationAndConditionManager.css" />
</head>
<body>
<%
    int userId = 0;
    if(session.getAttribute("type")!=null && !session.getAttribute("type").equals("therapist") || request.getParameter("userId")==null) {
        response.sendRedirect("../errorPage/403.html");
    }else {
        userId = Integer.parseInt((request.getParameter("userId")));

        int  userTherapist = new UserData().getUser(userId).getIdTherapist();
        if (userTherapist != (Integer) session.getAttribute("id")) {
            response.sendRedirect("../errorPage/403.html");
        }
    }
    ExerciseManager ExerciseService= new ExerciseManager();

    List<ExerciseGlossary> list_Exercisedone = ExerciseService.retrieveAllPatientExerciseGlossaryDone(userId);
    List<ExerciseGlossary> list_ExerciseNOTdone = ExerciseService.retrieveAllPatientExerciseGlossaryNotDone(userId);
%>
<a href="homeTherapist.jsp" align="left">Home</a>
<table border="1" align="center">
    <caption><b>Esercizi Fatti / Già Raccomandati</b></caption>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Type</th>
        <th>Difficulty</th>
        <th>Target</th>
    </tr>
    </thead>
    <tbody>
    <% for (ExerciseGlossary exerciseGlossary : list_Exercisedone) { %>
    <tr>
        <td><%= exerciseGlossary.getIdExercise() %></td>
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
<table border="1">
    <caption><b>Raccomanda Esercizi</b></caption>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Type</th>
        <th>Difficulty</th>
        <th>Target</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (ExerciseGlossary exerciseGlossary : list_ExerciseNOTdone) { %>
    <tr>
        <td><%= exerciseGlossary.getIdExercise() %></td>
        <td><%= exerciseGlossary.getExerciseName() %></td>
        <td><%= exerciseGlossary.getExerciseDescription() %></td>
        <td><%= exerciseGlossary.getType() %></td>
        <td><%= exerciseGlossary.getDifficulty()%></td>
        <td><%= exerciseGlossary.getTarget()%></td>
        <form action="../exerciseRecommendation" method="post">
            <td>
                <input type="hidden" name="idExercise" value="<%= exerciseGlossary.getIdExercise() %>">
                <input type="hidden" name="idPatient" value="<%= userId %>">
                <input type="submit" value="Raccomanda" name="operation">
            </td>
        </form>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>

