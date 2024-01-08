<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="model.entity.Exercise" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.ExerciseGlossary" %>
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
    <%@page contentType="text/html;charset=UTF-8"%>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="../CSS/homepagepatient.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
        List<Exercise> list = exerciseManager.retrieveAllPatientExerciseDone(userId);
        ExerciseGlossary exerciseGlossary = new ExerciseGlossary();
        if(!list.isEmpty()){
            int Counter = 0;
            for(Exercise exercise : list) {
                exerciseGlossary = exerciseManager.getExercise(exercise.getIdExercise());
    %>
    <div class="margin20">
        <div class="card">
            <div class="frame-3"><div class="frame-4"><div class="frame-5"><div class="frame-7">
                <div class="eillustration-wrapper">
                    <img class="illustration" src="../images/homepagepatient/check.png">

                    <svg class="eimg" width="160" height="160" xmlns="http://www.w3.org/2000/svg">
                        <g>
                            <title>Layer 1</title>
                            <circle id="circle_animation<%=Counter%>" class="circle_animation" r="69.85699" cy="81" cx="81" stroke-width="8" stroke="#6fdb6f" fill="none"></circle>
                            <text x="81" y="81" font-size="30" text-anchor="middle" dominant-baseline="middle" fill="green"><%=exercise.getEvaluation()%>%</text>
                        </g>
                    </svg>

                </div></div>
                <div class="discovering-english"><%=exerciseGlossary.getExerciseName()%></div>
            </div></div></div>
            <button class="button-2">Guarda errori</button>
        </div>

        <div class="errorcard" style="display: none;">
            <div class="frame-3"><div class="frame-4"><div class="frame-5"><div class="frame-7">
                <div class="illustration-wrapper">
                    <img class="illustration" src="../images/homepagepatient/attention.png">
                </div></div>
                <div class="discovering-english">Errori</div>
                <div class="chapter">Errori</div>
            </div></div></div>
        </div>
        <script>
            varcalc= (440 / 100 * <%=exercise.getEvaluation()%>)+440;
            document.querySelector('#circle_animation<%=Counter%>').style.strokeDashoffset = varcalc; // 50%
        </script>
    </div>
    <%
            Counter++;
            }
        }
        else {
    %>
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
</body>
</html>
<%
    }
%>