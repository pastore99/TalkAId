<%@ page import="model.entity.SlimmerExercise" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="model.service.user.UserData" %>
<%@ page import="model.entity.UserInfo" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
    <title>TalkAId - Ai testing</title>
</head>
<body>

    <%
        ExerciseManager em = new ExerciseManager();
        List<SlimmerExercise> exercises = em.retrieveAiRaccomandation(9); //TODO: Prendilo dalla sessione

        UserData ud = new UserData();
        ArrayList<UserInfo> u = ud.getUsersAndPersonalInfoByIdTherapist(9); //TODO: Prendilo dalla sessione

        Gson g = new GsonBuilder().disableHtmlEscaping().create();
    %>

    <% for (UserInfo user : u) {%>
    <table border="1">
        <tbody>
    <h1>Paziente: <%=user.getFirstname()%>  <%=user.getLastname()%></h1>
    <button onclick='approveAll(<%= user.getId() %>)'>Accetta Tutto</button>
    <button onclick='removeAll(<%= user.getId() %>)'>Rimuovi Tutto</button>

    <% for (SlimmerExercise ex : exercises){
        if(ex.getUserId() == user.getId()){
    %>
        <tr>
            <td><%= ex.getUserId() %></td>
            <td><%= ex.getName() %></td>
            <td><%= ex.getDescription() %></td>
            <td><%= ex.getDifficulty() %></td>
            <td><%= ex.getTarget() %></td>
            <td><%= ex.getType() %></td>
            <td>
                <button onclick='approveEx(<%= g.toJson(ex) %>)'>Approva</button>
                <button onclick='removeEx(<%= g.toJson(ex) %>)'>Rifiuta</button>
            </td>
        </tr>
    <%
                }
            }
    %>
        </tbody>
    </table>
    <%
        }
    %>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    function approveEx(ex) {
        let action = "Approve";
        $.ajax({
            type: "POST",
            url: "../ManageExercise",
            data: {
                action: action,
                exercise: JSON.stringify(ex)
            },
            error: function(error) {
                // Handle the error response from the servlet
                console.error("Error:", error);
            }
        });
    }

    function removeEx(ex) {
        let action = "Remove";
        $.ajax({
            type: "POST",
            url: "../ManageExercise",
            data: {
                action: action,
                exercise: JSON.stringify(ex)
            },
            error: function(error) {
                // Handle the error response from the servlet
                console.error("Error:", error);
            }
        });

    }

    function approveAll(userId){
        let action = "ApproveAll";
        $.ajax({
            type: "POST",
            url: "../ManageExercise",
            data: {
                action: action,
                userId: userId
            },
            success: function(){
                location.reload();
            },
            error: function(error) {
                // Handle the error response from the servlet
                console.error("Error:", error);
            }
        });
    }

    function removeAll(userId){
        let action = "RemoveAll";
        $.ajax({
            type: "POST",
            url: "../ManageExercise",
            data: {
                action: action,
                userId: userId
            },
            success: function(){
                location.reload();
            },
            error: function(error) {
                // Handle the error response from the servlet
                console.error("Error:", error);
            }
        });
    }

</script>
</body>
</html>

