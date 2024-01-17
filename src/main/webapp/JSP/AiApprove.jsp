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
    <title>Gestione AI</title>
    <link rel="stylesheet" href="../CSS/acceptanceExercisesAI.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
</head>
<body>
    <script>
        function hide(index){
            $("#u"+index).hide()
        }
    </script>
    <%
        ExerciseManager em = new ExerciseManager();
        List<SlimmerExercise> exercises = em.retrieveAiRaccomandation((Integer) session.getAttribute("id"));

        UserData ud = new UserData();
        ArrayList<UserInfo> u = ud.getUsersAndPersonalInfoByIdTherapist((Integer) session.getAttribute("id"));

        Gson g = new GsonBuilder().disableHtmlEscaping().create();
    %>

    <% int index=0;
        for (UserInfo user : u) {
        index++;
        boolean sentinel=false;
    %>

    <div id="u<%=index%>">
        <table>
            <tbody>
            <div id="intestazione">
                <h3>Paziente:</h3>
                <h2><%=user.getFirstname()%>  <%=user.getLastname()%></h2>
                <button class="buttonApprove" onclick='approveAll(<%= user.getId() %>)'>Accetta Tutto</button>
                <button class="buttonRemove" onclick='removeAll(<%= user.getId() %>)'>Rimuovi Tutto</button>
            </div>
            <th></th>
            <th>Nome</th>
            <th>Descrizione</th>
            <th>Tipo</th>
            <th>Difficoltà</th>
            <th>Target</th>
            <th></th>


    <% for (SlimmerExercise ex : exercises){
        if(ex.getUserId() == user.getId()){
        sentinel=true;
    %>
        <tr>
            <td>●</td>
            <td><%= ex.getName() %></td>
            <td><%= ex.getDescription() %></td>
            <td><%= ex.getType() %></td>
            <td><%= ex.getDifficulty() %></td>
            <td><%= ex.getTarget() %></td>
            <td>
                <button class="buttonApprove" onclick='approveEx(<%= g.toJson(ex) %>)'>Approva</button>
                <button class="buttonRemove" onclick='removeEx(<%= g.toJson(ex) %>)'>Rifiuta</button>
            </td>
        </tr>
    <%
                }
            }
    %>
        </tbody>
    </table>
    </div>
    <%
            if (!sentinel) {

    %>
            <script>
                hide(<%=index%>);
            </script>
    <%
            }
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

