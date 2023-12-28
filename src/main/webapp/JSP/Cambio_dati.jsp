<%--
  Created by IntelliJ IDEA.
  User: petri
  Date: 23/12/2023
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/Cambio_dati.css" type="text/css" rel = "stylesheet">
</head>
<body>
    <form method="post" action="/">
        <div class="up">
            <div>
            <img src="../Image/pngwing.com.png">
            <h2>Dati personali</h2>
            </div>
        </div>
        <img src="../Image/profilo_utente.png" id="paziente">
        <div class="down">
            <div class="input-field">
                <input required="" class="input" type="text" placeholder="<%= request.getParameter("firstname")%>" name="firstname"/>
                <label class="label" for="input">FirstName</label>
            </div>
            <div class="input-field">
                <input required="" class="input" type="text" placeholder="<%= request.getParameter("lastame")%>" name = "lastname"/>
                <label class="label" for="input">LastName</label>
            </div>
            <div class="input-field">
                <input required="" class="input" type="text" placeholder="<%= request.getParameter("email")%>" name ="email"/>
                <label class="label" for="input">Email</label>
            </div>
            <div class="input-field">
                <input required="" class="input" type="text" placeholder="<%= request.getParameter("address")%>" name="address"/>
                <label class="label" for="input">Address</label>
            </div>
            <div class="input-field">
                <input required="" class="input" type="text" placeholder="<%= request.getParameter("phone")%>" name="phonenumber"/>
                <label class="label" for="input">Phone Number</label>
            </div>
            <div class="input-field">
                <input required="" class="input" type="text" placeholder="<%= request.getParameter("password")%>" name ="password"/>
                <label class="label" for="input">Password</label>
            </div>
            <input type="hidden" name="FirstNameDefault" value = <%= request.getParameter("firstname")%>>
            <input type="hidden" name="LastNameDefault" value="<%= request.getParameter("lastame")%>">
            <input type="hidden" name="emailDefault" value="<%= request.getParameter("email")%>">
            <input type="hidden" name="AddressDefault" value="<%= request.getParameter("address")%>">
            <input type="hidden" name="PhoneDefault" value="<%= request.getParameter("phone")%>">

        </div>

        <div class="save">
            <button class="submit-btn">Salva</button>
        </div>
    </form>
</body>
</html>
