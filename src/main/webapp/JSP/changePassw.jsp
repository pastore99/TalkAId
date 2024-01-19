<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE>
<html lang="it">
<head>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
    <title>TalkAId - Cambio Password</title>
    <link href="../CSS/Change_Password.css" type="text/css" rel = "stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha384-UG8ao2jwOWB7/oDdObZc6ItJmwUkR/PfMyt9Qs5AwX7PsnYn1CRKCTWyncPTWvaS" crossorigin="anonymous"></script>

    <script>
        $(document).ready(function() {
            // Ascolta l'evento di clic sul pulsante
            $("#Controlla").click(function () {
                let password_attuale = $("#password").val();
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
                        if(result.toString()==="true")
                        {
                            document.querySelector(".popup").style.display = "block";
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

        <input type="password" id="password" name="Password" placeholder="Inserisci Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{12,}" title="La password deve contenere almeno un numero, una lettera maiuscola e una minuscola, e deve avere almeno 12 caratteri." required>
        <button class="form-submit-btn" id="Controlla" >Verifica</button>

<!-- e finisce qui. Se tutto funziona correttamente, devi mostrare questo altro popup-->

    <div class= "popup" style="display: none;">
        <input type="password" id="password2" name="password" placeholder="Nuova password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{12,}" title="La password deve contenere almeno un numero, una lettera maiuscola e una minuscola, e deve avere almeno 12 caratteri." required>
        <input type="password" id="Conferma_password" name="Conferm_password" placeholder="Conferma nuova password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{12,}" title="La password deve contenere almeno un numero, una lettera maiuscola e una minuscola, e deve avere almeno 12 caratteri." required>
        <button class="form-submit-btn" id="Controlla2">Conferma password</button>
    </div>

    <script>
        $(document).ready(function() {
            // Ascolta l'evento di clic sul pulsante
            $("#Controlla2").click(function () {
                let password_nuova = $("#password2").val();
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
                        if(result.toString()==="true")
                        {
                            window.location.href = '../JSP/userArea.jsp';
                            alert("Password inserita correttamente");
                            document.querySelector(".popup").style.display = "none";
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
