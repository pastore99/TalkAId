<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.Condition" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.entity.UserInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CONDITION</title>
</head>
<body>
<%
    ArrayList<model.entity.Condition> list_PatientCondition = (ArrayList<Condition>) session.getAttribute("list_PatientCondition");
    ArrayList<model.entity.Condition> list_NOTPatientCondition= (ArrayList<Condition>) session.getAttribute("list_NOTPatientCondition");
    UserInfo u= (UserInfo) session.getAttribute("user_selected");
%>
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
                <input type="hidden" name="idPatient" value="<%= u.getId() %>">
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
                <input type="hidden" name="idPatient" value="<%= u.getId() %>">
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
