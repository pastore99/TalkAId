<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.SlimmerExercise" %>
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
    <%@page contentType="text/html;charset=UTF-8"%>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="../CSS/homepagepatient.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
    <title>TalkAId - I tuoi risultati</title>
</head>
<body>

<script>
    let varcalc;
</script>
<div class="container">
    <div class="margin20">
        <div class="navigation-title">
            <div class="current-lesson">Resoconto esercizi:</div>
        </div>
    </div>

    <%
        ExerciseManager exerciseManager = new ExerciseManager();
        List<SlimmerExercise> list = exerciseManager.retrieveDoneExercises(userId);
        if(!list.isEmpty()){
            int Counter = 0;
            for(SlimmerExercise exerciseGlossary : list) {
    %>
    <div class="margin20">
        <div class="card">
            <div class="frame-3"><div class="frame-4"><div class="frame-5"><div class="frame-7">
                <div class="eillustration-wrapper">
                    <img alt="icon" class="illustration" src="../images/homepagepatient/attention.png">

                    <svg class="eimg" width="160" height="160" xmlns="http://www.w3.org/2000/svg">
                        <g>
                            <title>Layer 1</title>
                            <circle id="circle_animation<%=Counter%>" class="circle_animation" r="69.85699" cy="81" cx="81" stroke-width="8" stroke="#6fdb6f" fill="none"></circle>
                            <text x="81" y="81" font-size="30" text-anchor="middle" dominant-baseline="middle" fill="green"><%=list.get(Counter).getEvaluation()%>%</text>
                        </g>
                    </svg>

                </div></div>
                <div class="discovering-english"><%=exerciseGlossary.getName()%></div>
            </div></div></div>
            <button class="button-2">Aggiungi Feedback</button>
        </div>

        <div class="errorcard" style="display: none;">
            <div class="frame-3"><div class="frame-4"><div class="frame-5"><div class="frame-7">
                <div class="illustration-wrapper">
                    <img alt="icon" class="illustration" src="../images/homepagepatient/feedback.png">
                </div></div>
                <div class="imagediv">
                    <div class="images-container">
                        <img alt="icon" class="tdown" src="../images/homepagepatient/tdown.png">
                        <img alt="icon" class="tup" src="../images/homepagepatient/tup.png">
                    </div>
                </div>
            </div></div></div>
        </div>
        <script>
            varcalc= (440 / 100 * <%=list.get(Counter).getEvaluation()%>)+440;
            document.querySelector('#circle_animation<%=Counter%>').style.strokeDashoffset = varcalc; // 50%
        </script>
    </div>
    <%
            Counter++;
        }
    }
    else {
    %>

    <div class="discovering-english">Esercizi non disponibili</div>
    <%
        }
    %>


    <script>
        $(document).ready(function() {
            $(".button-2").click(function () {
                let errorCard = $(this).parent().nextAll(".errorcard").first();
                if (errorCard.css("display") === "none") {
                    errorCard.css("display", "block");
                } else {
                    errorCard.css("display", "none");
                }
            });
        });
    </script>
</div>
<div>
    <jsp:include page="navbar.jsp"></jsp:include>
    <div id="userInfo" hidden
         data-type = "<%= session.getAttribute("type")%>"
    ></div>
</div>
</body>
</html>
<%
    }
%>