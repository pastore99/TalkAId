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
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
<%
    if (session != null && session.getId() != null) {
        // Ottieni l'utente dalla sessione
        if(session.getAttribute("autorizzato")!=null)
        {

        }
        else
        {
            response.sendRedirect("/TalkAID_war_exploded/JSP/Controll_Password.jsp");
        }
    } else {
        // Se la sessione non è attiva, reindirizza alla pagina di login
        response.sendRedirect("/TalkAID_war_exploded/JSP/login.jsp");
    }
%>
<div class="up">

</div>
<h2 class="title">Crea nuova Password</h2>
<p class="info">Inserisci la nuova password<br> che vuoi assegnare al tuo account</p>


    <input type="text" id="password" name="password" placeholder="Nuova password" required="" onchange="controllaPassword()" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{12,}">
    <input type="text" id="Conferma_password" name="Conferm_password" placeholder="Conferma nuova password" required="" onchange="controllaPassword()" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{12,}">
    <button class="form-submit-btn" id="Controlla" disabled>Conferma password</button>


<div id="popup">
    <div id="popup-uscita">
        <img src="../Image/conferma.png">
        <p>Confermato <br> hai cambiato correttamente <br>la tua password</p>
        <form action="../logut" method="post">
        <div class="input">
            <button id="Login" type="submit">Login</button>
        </div>
        </form>
    </div>
</div>


<script>

    $(document).ready(function() {
        // Ascolta l'evento di clic sul pulsante
        $("#Controlla").click(function () {
            var password_attuale = $("#password").val();
            $.ajax({
                url: '${pageContext.request.contextPath}/ChangePassword',
                type: 'POST',
                dataType: 'json',
                data: {
                    password: password_attuale
                },
            })
                .done(function(result)
                {
                    if(result.result)
                    {
                        $('#popup').css('display', 'block');
                    }else
                    {
                        alert("problemi nel settaggio della nuova password");
                    }
                })
                .fail(function(jqXHR, textStatus, errorThrown) {
                    console.error('Errore nella richiesta AJAX:', textStatus, errorThrown);    // Aggiungi dettagli dell'errore alla console o visualizzali a schermo
                    console.error(jqXHR.responseText);    // Aggiorna l'elemento con l'ID 'messaggioErrore' con il messaggio di errore dettagliato
                    alert('Si è verificato un errore: ' + errorThrown);
                });
        })
    })

    function controllaPassword() {
        // Ottieni i valori dagli input
        var password1 = document.getElementById("password").value;
        var password2 = document.getElementById("Conferma_password").value;
        var Controlla = document.getElementById("Controlla");

        // Controlla se i valori corrispondono
        if (password1 === password2 && password1 !== "" && password2 !== "") {
            Controlla.disabled=false;
        } else {
            Controlla.disabled=true;
        }
    }
</script>
</body>
</html>
