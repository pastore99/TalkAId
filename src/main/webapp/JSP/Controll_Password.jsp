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
                        if(result.result)
                        {
                            window.location.href="Change_Password.jsp"
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
    <div class="up">

    </div>
    <h2 class="title">Inserisci password corrente</h2>
    <p class="info">Inserisci la password corrente</p>

        <input type="text" id="password" name="Password" placeholder="Enter your password" required="">
        <button class="form-submit-btn" id="Controlla" >Verifica</button>

</body>
</html>
