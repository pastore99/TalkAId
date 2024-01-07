<%@ page import="model.service.exercise.ExerciseManager" %>
<%@ page import="model.entity.Exercise" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.ExerciseGlossary" %>
<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.service.user.UserRegistry" %>
<!DOCTYPE html>
<html lang="it" style="background-color: #f7fcff; ">
<%
    Integer userIdp = (Integer) session.getAttribute("id");
    if(userIdp == null) {
        response.sendRedirect("../errorPage/403.html");
    }
    else {
        int userId = (Integer) session.getAttribute("id");
        UserRegistry ur = new UserRegistry();
        PersonalInfo data= ur.getPersonalInfo(userIdp);
%>
<head>
    <%@page contentType="text/html;charset=UTF-8"%>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="../CSS/homepagepatient.css" />
</head>


<%
    if(session.getAttribute("exercise") !=null){
        session.removeAttribute("exercise");
    }
%>
<body>

<div class="container">
    <div class="margin20">
        <div class="card">
            <div class="header">
                Ciao <%=data.getFirstname()%> <%=data.getLastname()%>
            </div>
        </div>
    </div>




    <div class="margin20">
        <div class="challenges">
            <div class="margin20">
                <div class="navigation-title">
                    <div class="current-lesson">Esercizi:</div>
                    <button id="openAll" class="buttons">Guarda tutti</button>
                </div>
            </div>
            <div class="acards" style="display: none;">

                <%
                    ExerciseManager exerciseManager = new ExerciseManager();
                    List<Exercise> list = exerciseManager.retrieveAllNewPatientExercise(userId);
                    ExerciseGlossary exerciseGlossary = new ExerciseGlossary();
                    if(!list.isEmpty()){
                        for(Exercise exercise : list) {
                            exerciseGlossary = exerciseManager.getExercise(exercise.getIdExercise());
                %>
                <div class="margin20">
                <form action="${pageContext.request.contextPath}/exerciseController" method="post">
                <div class="card2">
                    <div class="frame-3"><div class="frame-4"><div class="frame-5"><div class="frame-7">
                        <div class="illustration-wrapper">
                            <img class="illustration" src="../images/homepagepatient/exerc.png">
                        </div></div>
                        <div class="discovering-english"><%=exerciseGlossary.getExerciseName()%></div>
                    </div></div></div>
                    <input type="hidden" name="exerciseID" value="<%=exercise.getIdExercise()%>">
                    <input type="hidden" name="insertionDate" value="<%=exercise.getInsertionDate()%>">
                    <button class="button-2" type="submit">Comincia l'esercitazione</button>
                </div>
                </form>
                </div>
                <%
                        }
                    }
                    else {
                        %>
                <div class="discovering-english">Esercizi non disponibili</div>
                        <%
                        }
                %>
            </div>
        </div>
    </div>


    <div class="divbar"></div>


    <div class="margin20">
        <div class="challenges">
            <div class="margin20">
                <div class="navigation-title">
                    <div class="current-lesson">Esercizi Non Completati</div>
                </div>
            </div>
            <div class="cards">

                <%
                    ExerciseManager exerciseManager1 = new ExerciseManager();
                    List<Exercise> list1 = exerciseManager1.retrieveAllNewPatientExerciseNotDone(userId);
                    ExerciseGlossary exerciseGlossary1 = new ExerciseGlossary();
                    if(!list1.isEmpty()){
                        int maxCardsToShow = 5;
                        int cardCounter = 0;
                        for(Exercise exercise : list1) {
                            exerciseGlossary1 = exerciseManager1.getExercise(exercise.getIdExercise());
                %>
                <form action="${pageContext.request.contextPath}/exerciseController" method="post">
                    <div class="card2">
                        <div class="frame-3"><div class="frame-4"><div class="frame-5"><div class="frame-7">
                            <div class="illustration-wrapper">
                                <img class="illustration" src="../images/homepagepatient/illustration-challenges-1.svg">
                            </div></div>
                            <div class="discovering-english"><%=exerciseGlossary1.getExerciseName()%></div>
                        </div></div></div>
                        <input type="hidden" name="exerciseID" value="<%=exercise.getIdExercise()%>">
                        <input type="hidden" name="insertionDate" value="<%=exercise.getInsertionDate()%>">
                        <button class="button-2" type="submit">Comincia l'esercitazione</button>
                    </div>
                </form>
                <%
                        cardCounter++;
                        if (cardCounter >= maxCardsToShow) {
                            break;  // Interrompi il ciclo se il numero massimo di cards è stato raggiunto
                        }
                    }
                }
                else {
                %>
                %>
                <div class="discovering-english">Esercizi non disponibili</div>
                <%
                    }
                %>
            </div>
        </div>
    </div>


    <div class="divbar"></div>


    <div class="margin20">
        <div class="challenges">
            <div class="margin20">
                <div class="navigation-title">
                    <div class="current-lesson">Esercizi Svolti</div>
                    <button onclick="goReport()" class="buttons">Guarda Report</button>
                </div>
            </div>
            <div class="cards">

                <%
                    ExerciseManager exerciseManager2 = new ExerciseManager();
                    List<Exercise> list2 = exerciseManager2.retrieveAllPatientExerciseDone(userId);
                    ExerciseGlossary exerciseGlossary2 = new ExerciseGlossary();
                    if(!list2.isEmpty()){
                        int maxCardsToShow = 5;
                        int cardCounter = 0;
                        for(Exercise exercise : list2) {
                            exerciseGlossary2 = exerciseManager2.getExercise(exercise.getIdExercise());
                %>
                <form action="${pageContext.request.contextPath}/exerciseController" method="post">
                    <div class="card2">
                        <div class="frame-3"><div class="frame-4"><div class="frame-5"><div class="frame-7">
                            <div class="illustration-wrapper">
                                <img class="illustration" src="../images/homepagepatient/check.png">
                            </div></div>
                            <div class="discovering-english"><%=exerciseGlossary2.getExerciseName()%></div>
                        </div></div></div>
                        <input type="hidden" name="exerciseID" value="<%=exercise.getIdExercise()%>">
                        <input type="hidden" name="insertionDate" value="<%=exercise.getInsertionDate()%>">
                        <button class="button-2" type="submit">Comincia l'esercitazione</button>
                    </div>
                </form>
                <%
                        cardCounter++;
                        if (cardCounter >= maxCardsToShow) {
                            break;  // Interrompi il ciclo se il numero massimo di cards è stato raggiunto
                        }
                    }
                }
                else {
                %>
                %>
                <div class="discovering-english">Esercizi non disponibili</div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>



<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    function goReport() {
        window.location.href = "patientReport.jsp";
    }

    $(document).ready(function() {
        $("#openAll").click(function () {
            let acardsDiv = $(".acards");
            if (acardsDiv.css("display") === "none") {
                acardsDiv.css("display", "flex");
            } else {
                acardsDiv.css("display", "none");
            }
        });
    });
</script>

</body>
</html>

<%
    }
%>