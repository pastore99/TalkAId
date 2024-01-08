<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.service.user.UserData" %>
<%@ page import="model.service.user.UserRegistry" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/Cambio_dati.css" type="text/css" rel = "stylesheet">
    <link href="../CSS/Popup.css" type="text/css" rel = "stylesheet">
</head>
<body onload="controllaPopup();">
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
        PersonalInfo infoutente = new UserRegistry().getPersonalInfo(utente.getId());

%>
    <form  action="../changeDate" method="post">
        <div class="up">
            <div>
            <img src="../Image/pngwing.com.png" id="back">
            <h2>Dati personali</h2>
            </div>
        </div>
        <img src="../Image/profilo_utente.png" id="paziente">
        <div class="down">
            <div class="input-field">
                <input class="input" type="text" placeholder="<%= infoutente.getFirstname()%>" value="<%= infoutente.getFirstname()%>" required pattern="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$" title="Il nome deve contenere solo lettere, spazi, trattini, apostrofi e accenti." name="firstname"/>
                <label class="label">FirstName</label>
            </div>
            <div class="input-field">
                <input class="input" type="text" placeholder="<%= infoutente.getLastname()%>" value="<%= infoutente.getLastname()%>" required pattern="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$" title="Il cognome deve contenere solo lettere, spazi, trattini, apostrofi e accenti." name = "lastname"/>
                <label class="label">LastName</label>
            </div>
            <div class="input-field">
                <input class="input" type="text" placeholder="<%= utente.getEmail()%>" value="<%= utente.getEmail()%>" required name ="email"/>
                <label class="label">Email</label>
            </div>
            <div class="input-field">
                <input class="input" type="text" placeholder="<%= infoutente.getAddress()%>" value="<%= infoutente.getAddress()%>" required name="address"/>
                <label class="label">Address</label>
            </div>
            <div class="input-field">
                <input class="input" type="number" placeholder="<%= infoutente.getPhone()%>" value="<%= infoutente.getPhone()%>" required pattern="^((\+|00)39)?\s?3[0-9]{2}[-.\s]?[0-9]{6,7}$" name="phonenumber"/>
                <label class="label">Phone Number</label>
            </div>
        </div>

        <div class="save">
            <button class="submit-btn" type="submit">Salva</button>
        </div>
    </form>

<div class="input-field">
    <button class="submit-btn" onclick="redirectToHref()">Cambia Password</button>
</div>
    <div id="popup" >
        <div id="popup-uscita">
            <img src="../Image/conferma.png">
            <p id="Contenuto"></p>
            <div class="input">
                <button id="cancella" onclick="chiudiPopup()">Chiudi</button>
            </div>
        </div>
    </div>
    <script>

        function redirectToHref() {
        window.location.href = "changePassw.jsp";
    }
        function chiudiPopup() {
            document.getElementById('popup').style.display = 'none';
        }

        function controllaPopup() {
            var urlParams = new URLSearchParams(window.location.search);
            var risultato = urlParams.get('risultato');
            var contenuto = document.getElementById("Contenuto");


            if (risultato && risultato.trim() !== "") {
                contenuto.innerHTML=risultato;
                document.getElementById("popup").style.display = "block";
            } else {
                document.getElementById("popup").style.display = "none";
            }
        }

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
    </script>
<%
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
%>
</body>
</html>
