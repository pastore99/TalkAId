<%--
  Created by IntelliJ IDEA.
  User: petri
  Date: 22/12/2023
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/User_Area.css" type="text/css" rel = "stylesheet">

</head>
<body>
<div class="up">
    <div class="navbar-up">
        <img src="../Image/pngwing.com.png">
        <div class="paziente">
            <img src="../Image/profilo_utente.png" class="Immagine_profilo">
            <div class="info-paziente">
                <h2 class="title">Nome paziente</h2>
                <h2 class = "content">numero di telefono</h2>
            </div>
        </div>
        <img src="../Image/tre_punti_menu.png">
    </div>
    <div class="informazioni">
        <div class="info-paziente-logopedista">
            <div class="info-paziente">
                <h2 class="title">Durata Trattamento</h2>
                <h2 class = "content">Durata</h2>
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
        <div class="content-info"><h2>email@prova.com</h2></div>
    </div>
    <div class="info">
        <div class="nome_info"><h2>Dott.assegnato:</h2></div>
        <div class="content-info"><h2>Luigi Prova</h2></div>
    </div>
    <div class="info">
        <div class="nome_info"><h2>Email.Dott:</h2></div>
        <div class="content-info"><h2>prova@dottore.com</h2></div>
    </div>
    <div class="info">
        <div class="nome_info"><h2>Valutazione</h2></div>
        <div class="content-info"><h2>3.5/5</h2></div>
    </div>
    <div class="input">
        <form action="/action_page.php">
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
            <button>Si</button>
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
</script>

</body>
</html>
