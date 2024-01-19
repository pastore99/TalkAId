<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="it" style="background-color: #f7fcff; ">
<%
    Integer userIdp = (Integer) session.getAttribute("id");
    if(userIdp == null) {
        response.sendRedirect("../errorPage/403.html");
    }
    else {
        int userId = (Integer) session.getAttribute("id");
%>
<head>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
    <title>TalkAId - Report Totale</title>
    <%@page contentType="text/html;charset=UTF-8"%>
    <meta charset="utf-8" />
    <link href="../CSS/userReport.css" type="text/css" rel = "stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>

</head>
<body>
<script>
    let varcalc;
</script>
<div class="container">
    <div class="up">
    </div>
    <div class="down">
<div class="container">
    <div class="margin20">
        <div class="navigation-title">
            <div class="current-lesson">Resoconto progressi:</div>
        </div>
    </div>

    <%
        ExerciseManager exerciseManager = new ExerciseManager();
        Map<String,Integer> dict = exerciseManager.retrieveAllStats((int)session.getAttribute("id"));
        if(!dict.isEmpty()){
            int Counter = 0;
            for(Map.Entry<String, Integer> exercise : dict.entrySet()) {

    %>
    <div class="prova">
        <h2><%= exercise.getKey()%></h2>
        <svg class="eimg" width="160" height="160" xmlns="http://www.w3.org/2000/svg">
            <g>
                <title>Layer 1</title>

                <circle id="circle_animation<%=Counter%>" class="circle_animation" r="69.85699" cy="81" cx="81" stroke-width="8" stroke="#6fdb6f" fill="none"></circle>
                <text x="81" y="81" font-size="30" text-anchor="middle" dominant-baseline="middle" fill="green"><%=exercise.getValue()%>%</text>
            </g>
        </svg>
    </div>

    <script>
        varcalc= (440 / 100 * <%=exercise.getValue()%>)+440;
        document.querySelector('#circle_animation<%=Counter%>').style.strokeDashoffset = varcalc; // 50%
    </script>
    <%
            Counter++;
        }
    }
    else {
    %>
    %>
    <div class="discovering-english">Non ti sono stati proposti esercizi</div>
    <%
        }
    %>

</div>
<div>
    <jsp:include page="navbar.jsp"></jsp:include>
    <div id="userInfo" hidden
         data-type = "<%= session.getAttribute("type")%>"
    ></div>
</div>
    </div>
</div>
</body>
</html>
<%
    }
%>