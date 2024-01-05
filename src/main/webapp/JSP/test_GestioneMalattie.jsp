<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.Condition" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stampa Array in</title>
</head>
<body>
<h2>Condition</h2>
<%
    ArrayList<model.entity.Condition> list_PatientCondition = (ArrayList<Condition>) session.getAttribute("list_PatientCondition");
    ArrayList<model.entity.Condition> list_NOTPatientCondition= (ArrayList<Condition>) session.getAttribute("list_NOTPatientCondition");
%>
<table border="1">
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
    <%
        for (Condition condition : list_PatientCondition) {
    %>
    <tr>
        <td><%= condition.getIdCondition() %></td>
        <td><%= condition.getDisorderDescription() %></td>
        <td><%= condition.getDisorderName() %></td>
        <td><%= condition.getSeverity() %></td>
        <td>
            <form action="RimuoviServlet" method="post">
                <input type="hidden" name="idCondition" value="<%= condition.getIdCondition() %>">
                <input type="submit" value="Rimuovi" name="operation">
            </form>
        </td>
    </tr>
    <% } %>

    <%
        for (Condition condition : list_NOTPatientCondition) {
    %>
    <tr>
        <td><%= condition.getIdCondition() %></td>
        <td><%= condition.getDisorderDescription() %></td>
        <td><%= condition.getDisorderName() %></td>
        <td><%= condition.getSeverity() %></td>
        <td>
            <form action="AggiungiServlet" method="post">
                <input type="hidden" name="idCondition" value="<%= condition.getIdCondition() %>">
                <input type="submit" value="Aggiungi" name="operation">
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>

</body>
</html>
