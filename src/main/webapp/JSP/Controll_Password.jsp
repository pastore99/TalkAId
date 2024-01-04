<%--
  Created by IntelliJ IDEA.
  User: petri
  Date: 23/12/2023
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/Change_Password.css" type="text/css" rel = "stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <script>
        $(document).ready(function() {
            // Ascolta l'evento di clic sul pulsante
            $("#Controlla").click(function () {
                var password_attuale = $("#password").val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/ControllPassword',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        password: password_attuale
                    },
                })
                    .done(function(result)
                    {
                        if(result.contains("true"))
                        {
                           showPopup();
                        }
                        else
                        {
                            alert("Password Inserita Errata Riprova");
                        }
                    })
                    .fail(function(jqXHR, textStatus, errorThrown) {
                        console.error('Errore nella richiesta AJAX:', textStatus, errorThrown);    // Aggiungi dettagli dell'errore alla console o visualizzali a schermo
                        console.error(jqXHR.responseText);    // Aggiorna l'elemento con l'ID 'messaggioErrore' con il messaggio di errore dettagliato
                        alert('Si è verificato un errore: ' + errorThrown);
                    });
        })
        })
    </script>

</head>
<body>
<!--Qui c'è quello che vedi sin da subito -->
    <div class="up">
    </div>
    <h2 class="title">Inserisci password corrente</h2>
    <p class="info">Inserisci la password corrente</p>

        <input type="text" id="password" name="Password" placeholder="Inserisci Password" required="">
        <button class="form-submit-btn" id="Controlla" >Verifica</button>

<!-- e finisce qui. Se tutto funziona correttamente, devi mostrare questo altro popup-->

    <div class= "popup" style="hidden">
        <input type="text" id="password2" name="password" placeholder="Nuova password" required="" onchange="controllaPassword()" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{12,}">
        <input type="text" id="Conferma_password" name="Conferm_password" placeholder="Conferma nuova password" required="" onchange="controllaPassword()" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{12,}">
        <button class="form-submit-btn" id="Controlla2" disabled>Conferma password</button>
    </div>

    <script>
        $(document).ready(function() {
            // Ascolta l'evento di clic sul pulsante
            $("#Controlla2").click(function () {
                var password_nuova = $("#password2").val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/ChangePassword',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        password: password_nuova
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
    </script>
</body>
</html>
