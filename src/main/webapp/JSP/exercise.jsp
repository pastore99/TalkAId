<%@ page import="model.entity.ExerciseGlossary" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%
        ExerciseGlossary exercise = (ExerciseGlossary) session.getAttribute("exercise");
        //session.removeAttribute("exercise"); @michele fallo nella homepage questa cosa, ovviamente controlla se prima esiste poi nel caso elimini
    %>

    <title>Esercizio <%= exercise.getIdExercise()%></title>

</head>
<body>
    <div>
        <div id="header">
            <div id="backDiv">
                <img src="../images/backIcon.svg" alt="backIcon">
            </div>
            <div id="exerciseTitleDiv">
                <p><%= exercise.getExerciseName() %></p>
            </div>
            <div id="notificationDiv">
                <img src="../images/notificationIcon.svg" alt="notificationIcon">
            </div>
        </div>
        <div id="exerciseDiv"> <!-- in questo div si deve includere l'esercizio -->


        </div>
    </div>
    <div id="exerciseInfo" hidden
         data-exerciseId = "<%= exercise.getIdExercise()%>"
         data-userId = "<%= session.getAttribute("id") %>"
         data-type = "<%= exercise.getType()%>"
         data-userType = "<%= session.getAttribute("type")%>"
    ></div>
</body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
    <script src="../JS/exercise.js"></script>
    <link rel="stylesheet" href="../CSS/exercise.css">
    <script>
        startUp(<%= exercise.getInitialState()%>);
    </script>
</html>
