<%@ page import="model.entity.SlimmerExercise" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="model.service.user.UserData" %>
<%@ page import="model.entity.UserInfo" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE>
<html lang="it">
<body>
<% if(session.getAttribute("type")==null || session.getAttribute("type").equals("patient")){
    response.sendRedirect("../errorPage/403.html");
}%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
    <script>
        function hide(index){
            $("#u"+index).hide()
        }
        function hideEl(element){
            $("#"+element).hide();
        }
    </script>
    <%
        ExerciseManager em = new ExerciseManager();
        List<SlimmerExercise> exercises = em.retrieveAiRaccomandation((Integer) session.getAttribute("id"));

        UserData ud = new UserData();
        ArrayList<UserInfo> users = ud.getUsersAndPersonalInfoByIdTherapist((Integer) session.getAttribute("id"));

        Gson g = new GsonBuilder().disableHtmlEscaping().create();
    %>
        <div id="NoExercise"><h3>Non ci sono esercizi da valutare</h3> </div>
    <%
        int index=0;
        int trID = 0;
        for (UserInfo user : users) {
        index++;
        boolean sentinel=false;
    %>

    <div id="u<%=index%>">
        <table id="table">
            <tbody>
            <div id="intestazione">
                <h3>Paziente:</h3>
                <h2><%=user.getFirstname()%>  <%=user.getLastname()%></h2>
                <button class="buttonApprove" onclick='approveAll(<%= user.getId() %>); hideEl("u<%=index%>")'>Accetta Tutto</button>
                <button class="buttonRemove" onclick='removeAll(<%= user.getId() %>); hideEl("u<%=index%>")'>Rimuovi Tutto</button>
            </div>
            <th></th>
            <th>Nome</th>
            <th>Descrizione</th>
            <th>Tipo</th>
            <th>Difficoltà</th>
            <th>Target</th>
            <th></th>


    <%
        for (SlimmerExercise ex : exercises){
        if(ex.getUserId() == user.getId()){
            sentinel=true;
    %>
        <tr id="tr<%=trID%>">
            <td>●</td>
            <td><%= ex.getName() %></td>
            <td><%= ex.getDescription() %></td>
            <td><%= ex.getType() %></td>
            <td><%= ex.getDifficulty() %></td>
            <td><%= ex.getTarget() %></td>
            <td>
                <button class="buttonApprove" onclick='approveEx(<%= g.toJson(ex) %>); hideEl("tr<%=trID%>")'>Approva</button>
                <button class="buttonRemove" onclick='removeEx(<%= g.toJson(ex) %>); hideEl("tr<%=trID%>")'>Rifiuta</button>
            </td>
        </tr>
    <%
              trID++;  }
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
            }else{
    %>
            <script>
                hideEl("NoExercise");
            </script>
    <%
            }
        }
    %>

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



