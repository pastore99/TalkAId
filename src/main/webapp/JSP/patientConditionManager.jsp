<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.Condition" %>
<%@ page import="model.service.condition.ConditionManager" %>
<%@ page import="model.entity.Schedule" %>
<%@ page import="model.entity.UserInfo" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.service.user.UserData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Condition</title>
</head>
<body>
<%
    int userId = 0;
    if(session.getAttribute("type")!=null && !session.getAttribute("type").equals("therapist") || request.getParameter("userId")==null) {
        response.sendRedirect("../errorPage/403.html");
    }else {
        userId = Integer.parseInt((request.getParameter("userId")));

        int  userTherapist = new UserData().getUser(userId).getIdTherapist();
        if (userTherapist != (Integer) session.getAttribute("id")) {
            response.sendRedirect("../errorPage/403.html");
        }
    }


    ConditionManager ConditionService= new ConditionManager();

    ArrayList<Condition> list_PatientCondition = ConditionService.getConditionsOfPatient(userId);
    ArrayList<Condition> list_NOTPatientCondition = ConditionService.getConditionsNOTOfPatient(userId);
%>
<a href="homeTherapist.jsp" align="left">Home</a>
<table border="1" align="center">
    <caption>Condition of patient</caption>
    <thead>
    <tr>
        <th>ID</th>
        <th>Disorder Description</th>
        <th>Disorder Name</th>
        <th>Severity</th>
        <th>Operazione</th>
    </tr>
    </thead>
    <tbody>
    <% for (Condition condition : list_PatientCondition) { %>
    <tr>
        <td><%= condition.getIdCondition() %></td>
        <td><%= condition.getDisorderDescription() %></td>
        <td><%= condition.getDisorderName() %></td>
        <td><%= condition.getSeverity() %></td>
        <td>
            <form action="../AddRemovePatientCondition" method="post">
                <input type="hidden" name="idCondition" value="<%= condition.getIdCondition() %>">
                <input type="hidden" name="idPatient" value="<%= userId %>">
                <input type="submit" value="Rimuovi" name="operation">
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<hr>
<table border="1">
    <caption>Add new condition to patient</caption>
    <thead>
    <tr>
        <th>ID</th>
        <th>Disorder Description</th>
        <th>Disorder Name</th>
        <th>Severity</th>
        <th>Operazione</th>
    </tr>
    </thead>
    <tbody>
    <% for (Condition condition : list_NOTPatientCondition) { %>
    <tr>
        <td><%= condition.getIdCondition() %></td>
        <td><%= condition.getDisorderDescription() %></td>
        <td><%= condition.getDisorderName() %></td>
        <form action="../AddRemovePatientCondition" method="post">
            <td><input type="number" name="severity" min="1" max="10" value="1"></td>
            <td>
                <input type="hidden" name="idCondition" value="<%= condition.getIdCondition() %>">
                <input type="hidden" name="idPatient" value="<%= userId %>">
                <input type="submit" value="Aggiungi" name="operation">
            </td>
        </form>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>

</body>
</html>
