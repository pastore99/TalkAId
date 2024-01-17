<%@ page import="model.entity.Exercise" %>
<%@ page import="model.entity.SlimmerExercise" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="model.service.user.UserData" %>
<%@ page import="model.entity.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: ms
  Date: 17/01/2024
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tabella con Bottoni</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Nome Prodotto</th>
        <th>Prezzo</th>
        <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <%
        ExerciseManager em = new ExerciseManager();
        List<SlimmerExercise> exercises = em.retrieveAiRaccomandation(9);
        UserData ud = new UserData();
        ArrayList<UserInfo> u = ud.getUsersAndPersonalInfoByIdTherapist(9);
    %>

    <% for (UserInfo user : u) { %>
    <h1>Paziente: <%=user.getFirstname()%>  <%=user.getLastname()%></h1>
    <button>Approva tutto</button>
    <button>Elimina Tutto</button>
    <% for (SlimmerExercise ex : exercises){
        if(ex.getUserId() == user.getId()){
    %>
    <tr>
        <td><%= ex.getName() %></td>
        <td><%= ex.getDescription() %></td>
        <td><%= ex.getDifficulty() %></td>
        <td><%= ex.getTarget() %></td>
        <td><%= ex.getType() %></td>
        <td>
            <button>Approva</button>
            <button>Rimuovi</button>
        </td>
    </tr>
    <% }}} %>
    </tbody>
</table>

<script>
    function eseguiAzione1(nomeProdotto) {
        // Implementa l'azione desiderata per il pulsante 1
        alert("Azione 1 per " + nomeProdotto);
    }

    function eseguiAzione2(nomeProdotto) {
        // Implementa l'azione desiderata per il pulsante 2
        alert("Azione 2 per " + nomeProdotto);
    }
</script>
</body>
</html>

