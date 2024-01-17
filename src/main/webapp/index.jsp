<!DOCTYPE html>
<html lang="it" style="background-color: #f7fcff; ">
<head>
    <link rel="icon" href="images/siteIco.png" type="image/png">
    <%@page contentType="text/html;charset=UTF-8"%>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="CSS/homepageGuest.css" />
    <title>TalkAId - Guest</title>
</head>
<body>
<div class="container">
    <div class="margin20">
        <div class="card">
            <img class="logo" src="images/TalkAidLogo.svg" alt="TalkAId logo">
            <div class="discovering-english">
                    TALKAID è un avanzato progetto di riabilitazione per disabilità del linguaggio. Offre trattamenti a distanza,
                    personalizzati grazie all'Intelligenza Artificiale, facilitando logopedisti e motivando i pazienti con il monitoraggio
                    dei progressi. La flessibilità di esercizi da casa elimina barriere, rendendo il percorso accessibile e confortevole.
                    Il sistema, pensato per adulti responsabili, mira a rivoluzionare l'approccio inclusivo e centrato sulla persona per affrontare
                    le disabilità del linguaggio.
            </div>
            <button class="button-2" onclick="register()">Registrazione</button>
            <button class="button-2" onclick="login()">Login</button>
        </div>
    </div>
</div>

<script>
    function register() {
        window.location.href = 'JSP/registration.jsp';
    }

    function login() {
        window.location.href = 'JSP/login.jsp';
    }

</script>

</body>
</html>