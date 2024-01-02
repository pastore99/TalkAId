<%@ page import="model.entity.ExerciseGlossary" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.google.gson.Gson"%>

<html>
<head>
    <%
        ExerciseGlossary exercise = (ExerciseGlossary) session.getAttribute("exercise");
        Gson g = new Gson();
        session.removeAttribute("exercise");
    %>

    <title>Esercizio <%= exercise.getIdExercise()%></title>

</head>
<body>
    <div>
        <div id="header">
            <div id="backDiv">
                <img src="../images/backIcon.svg" alt="backIcon">
            </div>
            <div>
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
         data-id = "<%= exercise.getIdExercise()%>"
         data-type = "<%= exercise.getType()%>"
    ></div>
</body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
    <script src="../JS/exercise.js"></script>
    <link rel="stylesheet" href="../CSS/exercise.css">
    <script>
        startUp(<%= exercise.getInitialState()%>);
    </script>
</html>
