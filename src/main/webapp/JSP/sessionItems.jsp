<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Session Items</title>
</head>
<body>
<h2>Session Items:</h2>
<%
    java.util.Enumeration<String> sessionAttrNames = session.getAttributeNames();
    while (sessionAttrNames.hasMoreElements()) {
        String attributeName = sessionAttrNames.nextElement();
        String attributeValue = session.getAttribute(attributeName) != null ? session.getAttribute(attributeName).toString() : "null";
%>
<p><strong><%= attributeName %></strong>: <%= attributeValue %></p>
<%
    }
%>
</body>
</html>