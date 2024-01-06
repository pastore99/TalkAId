<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Testing Invitation and License</title>
    <%
        String license = (String) request.getAttribute("license");
        String invitation = (String) request.getAttribute("invitation");
        HttpSession s = (HttpSession) request.getSession();
        s.setAttribute("id", 9999);
    %>
</head>
<body>

    <button id="invito">Genera Invito</button>
    <p> <%= invitation%> </p>
    <button id="licenza">Genera Licenza</button>
    <p> <%= license%> </p>


</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="../JS/testingInvitationAndLicense.js"></script>
</html>
