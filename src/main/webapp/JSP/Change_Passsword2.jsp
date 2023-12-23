<%--
  Created by IntelliJ IDEA.
  User: petri
  Date: 23/12/2023
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/Change_Password.css" type="text/css" rel = "stylesheet">
</head>
<body>
<div class="up">

</div>
<h2 class="title">Crea nuova Password</h2>
<p class="info">Inserisci la nuova password<br> che vuoi assegnare al tuo account</p>

<form>
    <input type="text" id="email" name="email" placeholder="Nuova password" required="">
    <button class="form-submit-btn" onclick="inviaForm()">Conferma password</button>
</form>

<div id="popup">
    <div id="popup-uscita">
        <img src="../Image/conferma.png">
        <p>Confermato <br> hai cambiato correttamente <br>la tua password</p>
        <div class="input">
            <button onclick="chiudiPopup()">Login</button>
        </div>
    </div>
</div>


<script>
    function inviaForm() {
        // Raccogli i dati del form
        var formData = new FormData(document.getElementById('myForm'));

        // Crea un oggetto XMLHttpRequest
        var xhr = new XMLHttpRequest();

        // Definisci il metodo e l'URL di destinazione
        xhr.open('POST', 'url_del_server.php', true);

        // Definisci la funzione di callback quando la richiesta è completata
        xhr.onload = function() {
            if (xhr.status === 200) {
                // La richiesta è stata completata con successo
                console.log(xhr.responseText); // Puoi gestire la risposta del server qui
            }
        };

        // Invia la richiesta al server
        xhr.send(formData);
    }
</script>
</body>
</html>
