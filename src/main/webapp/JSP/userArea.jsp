<%@ page import="model.service.user.UserData" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.service.user.UserRegistry" %>
<html>
<head>
    <title>TalkAId - User Area</title>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
    <link href="../CSS/User_Area.css" type="text/css" rel = "stylesheet">

</head>
<body>
<%
    UserData userDate= new UserData();
    User utente=null;
    if (session != null && session.getId() != null) {
        // Ottieni l'utente dalla sessione
        utente = userDate.getUser(session.getAttribute("id"));

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
        PersonalInfo infoutente = new UserRegistry().getPersonalInfo(utente.getId());
        User therapist = userDate.getUser(utente.getIdTherapist());
        PersonalInfo infotherapist = new UserRegistry().getPersonalInfo((therapist.getId()));

%>
<div class="up">
    <div class="navbar-up">
            <img src="../Image/pngwing.com.png" id="back">
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
        <div class="nome_info"> <h2>Email:</h2></div>
        <div class="content-info"><h2><%= utente.getEmail()%></h2></div>
    </div>

    <%
        if (session.getAttribute("type") != "therapist"){
    %>

    <div class="info">
        <div class="nome_info"> <h2>Patologie:</h2></div>
        <div class="content-info"> <h2>Disgrafia, Disortografia</h2></div>
    </div>
    <div class="info">
        <div class="nome_info"><h2>Dott.assegnato:</h2></div>
        <div class="content-info"><h2><%= infotherapist.getFirstname()%> <%= infotherapist.getLastname()%></h2></div>
    </div>
    <div class="info">
        <div class="nome_info"><h2>Email.Dott:</h2></div>
        <div class="content-info"><h2><%= therapist.getEmail()%></h2></div>
    </div>


    <%
        }
    %>

    <div class="input">
        <form action="userInfoChange.jsp" method="post">
            <input type="hidden" value="<%= infoutente.getLastname()%>" name="lastname">
            <input type="hidden" value="<%= infoutente.getFirstname()%>" name="firstname">
            <input type="hidden" value="<%= infoutente.getAddress()%>" name="address">
            <input type="hidden" value="<%= infoutente.getPhone()%>" name="phone">
            <input type="hidden" value="<%= utente.getEmail()%>" name="email">
            <button type="submit">Modifica Profilo</button>
        </form>
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
            <form action="../logout" method="post">
            <button type="submit">Si</button>
            </form>
            <button id="cancella" onclick="chiudiPopup()">Cancella</button>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var backImage = document.getElementById("back");

        // Aggiungi un ascoltatore di eventi per il click sull'immagine
        backImage.addEventListener("click", function() {
            // Esegui la funzione per tornare alla pagina precedente
            goBack();
        });
    });

    // Funzione per tornare alla pagina precedente
    function goBack() {
        window.history.back();
    }

    function mostraPopup() {
        document.getElementById('popup').style.display = 'block';
    }

    function chiudiPopup() {
        document.getElementById('popup').style.display = 'none';
    }
</script>

<%
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
%>

<div id="navbarContainer">
    <jsp:include page="navbar.jsp"></jsp:include>
    <div id="userInfo" hidden
         data-type = "<%= session.getAttribute("type")%>"
    ></div>
</div>

</body>
</html>