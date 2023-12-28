<%--
  Created by IntelliJ IDEA.
  User: petri
  Date: 22/12/2023
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.service.user.UserData" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.service.PersonalInfo.PersonalInfoData" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/User_Area.css" type="text/css" rel = "stylesheet">

</head>
<body>

<script>console.log("sto qua")</script>

<%
    UserData userDate= new UserData();
    User utente=null;
    if (session != null && session.getId() != null) {
        // Ottieni l'utente dalla sessione
        utente = userDate.getUserByIdOrEmail(session.getAttribute("id"));

        // Se l'utente non è valido o non esiste, reindirizza alla pagina di login
        if (utente == null) {
            response.sendRedirect("/TalkAID_war_exploded/JSP/login.jsp");
        }
    } else {
        // Se la sessione non è attiva, reindirizza alla pagina di login
        response.sendRedirect("/TalkAID_war_exploded/JSP/login.jsp");
    }
    try
    {
        String[] data_di_inizio = String.valueOf(utente.getActivationDate()).split(" ");
        String[] giorno = data_di_inizio[0].split("-");
        PersonalInfo infoutente = new PersonalInfoData().getPersonalInfo(utente.getId());
        User therapist = userDate.getUserByIdOrEmail(utente.getIdTherapist());
        PersonalInfo infotherapist = new PersonalInfoData().getPersonalInfo((therapist.getId()));


%>
<div class="up">
    <div class="navbar-up">
        <a href="javascript:history.back()">
            <img src="../Image/pngwing.com.png">
        </a>
        <div class="paziente">
            <img src="../Image/profilo_utente.png" class="Immagine_profilo">
            <div class="info-paziente">
                <h2 class="title"><%= infoutente.getFirstname()%> <%= infoutente.getLastname()%></h2>
                <h2 class = "content"><%= infoutente.getPhone()%></h2>
            </div>
        </div>
        <img src="../Image/tre_punti_menu.png">
    </div>
    <div class="informazioni">
        <div class="info-paziente-logopedista">
            <div class="info-paziente">
                <h2 class="title">Inizio Trattamento</h2>
                <h2 class = "content"><%= giorno[0]%> <%= giorno[1]%> <%= giorno[2]%></h2>
            </div>
            <div class="info-paziente">
                <h2 class="title">Tempo rimasto</h2>
                <h2 class = "content">Tempo</h2>
            </div>

        </div>

    </div>
</div>

<div class="card">
    <div class="info">
        <div class="nome_info"> <h2>Patologie:</h2></div>
        <div class="content-info"> <h2>Disgrafia, Disortografia</h2></div>
    </div>
    <div class="info">
        <div class="nome_info"> <h2>Email:</h2></div>
        <div class="content-info"><h2><%= utente.getEmail()%>></h2></div>
    </div>
    <div class="info">
        <div class="nome_info"><h2>Dott.assegnato:</h2></div>
        <div class="content-info"><h2><%= infotherapist.getFirstname()%> <%= infotherapist.getLastname()%></h2></div>
    </div>
    <div class="info">
        <div class="nome_info"><h2>Email.Dott:</h2></div>
        <div class="content-info"><h2><%= therapist.getEmail()%></h2></div>
    </div>
    <div class="info">
        <div class="nome_info"><h2>Valutazione</h2></div>
        <div class="content-info"><h2>3.5/5</h2></div>
    </div>
    <div class="input">
        <form action="/TalkAID_war_exploded/JSP/Cambio_dati.jsp" method="post">
            <input type="hidden" value="<%= infoutente.getLastname()%>" name="lastname">
            <input type="hidden" value="<%= infoutente.getFirstname()%>" name="firstname">
            <input type="hidden" value="<%= infoutente.getAddress()%>" name="address">
            <input type="hidden" value="<%= infoutente.getPhone()%>" name="phone">
            <input type="hidden" value="<%= utente.getEmail()%>" name="email">
            <button type="submit">Modifica Profilo</button>
        </form>
        <button>
            Avvia Tutorial
        </button>
    </div>
    <button class = "Logout" onclick="mostraPopup()">
        Logout
    </button>
</div>

<div id="popup">
    <div id="popup-uscita">
        <img src="../Image/usicta.png">
        <p>Vuoi disconnetterti?</p>
        <div class="input">
            <button onclick="logout()">Si</button>
            <button id="cancella" onclick="chiudiPopup()">Cancella</button>
        </div>
    </div>
</div>

<script>
    function mostraPopup() {
        document.getElementById('popup').style.display = 'block';
    }

    function chiudiPopup() {
        document.getElementById('popup').style.display = 'none';
    }

    function logout()
    {
        var options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        };


        var url = '/logout';


        fetch(url, options)
            .then(response => response.json())
            .then(data => {
            })
            .catch(error => {
                console.error('Errore durante la richiesta:', error);
            });
    }
</script>

<%
    }
    catch (Exception e)
    {

    }
%>
</body>
</html>