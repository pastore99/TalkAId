<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.Condition" %>
<%@ page import="model.service.condition.ConditionManager" %>
<%@ page import="model.service.user.UserData" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<body>
<%
    int patientIdCM = 0;
    if(session.getAttribute("type")==null || !session.getAttribute("type").equals("therapist") ||  request.getParameter("patientID") == null) {
        response.sendRedirect("../errorPage/403.html");
    }else {
        patientIdCM = Integer.parseInt(request.getParameter("patientID"));

        User user = new UserData().getUser(patientIdCM);
        if (user.getIdTherapist() != (Integer) session.getAttribute("id")) {
            response.sendRedirect("../errorPage/403.html");
        }
    }

    ConditionManager ConditionService= new ConditionManager();

    ArrayList<Condition> list_PatientCondition = ConditionService.getConditionsOfPatient(patientIdCM);
    ArrayList<Condition> list_NOTPatientCondition = ConditionService.getConditionsNOTOfPatient(patientIdCM);
%>
<table>
    <caption><b>Patologie del paziente</b></caption>
    <thead>
    <tr>
        <th></th>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Gravità</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (Condition condition : list_PatientCondition) { %>
    <tr>
        <td>●</td>
        <td><%= condition.getDisorderName()%></td>
        <td><%= condition.getDisorderDescription()%></td>
        <td><%= condition.getSeverity()%></td>
        <td>
            <form action="../AddRemovePatientCondition" method="post">
                <input type="hidden" name="idCondition" value="<%= condition.getIdCondition() %>">
                <input type="hidden" name="idPatient" value="<%= patientIdCM %>">
                <input type="submit" class="buttonRemove" value="Rimuovi" name="operation">
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<hr>
<table>
    <caption><b>Aggiungi nuova patologia</b></caption>
    <thead>
    <tr>
        <th></th>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Gravità</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (Condition condition : list_NOTPatientCondition) { %>
    <tr>
        <td>●</td>
        <td><%= condition.getDisorderName()%></td>
        <td><%= condition.getDisorderDescription()%></td>
        <form action="../AddRemovePatientCondition" method="post">
            <td><input type="number" class="input-number" name="severity" min="1" max="10" value="1"></td>
            <td>
                <input type="hidden" name="idCondition" value="<%= condition.getIdCondition() %>">
                <input type="hidden" name="idPatient" value="<%= patientIdCM %>">
                <input type="submit" class="buttonApprove" value="Aggiungi" name="operation">
            </td>
        </form>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
