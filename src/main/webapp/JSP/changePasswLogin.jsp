<%@ page contentType="text/html;charset=UTF-8" language="java" %><!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="../CSS/changePasswLogin.css" />
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
<div class="container">

    <!-- Email input -->
    <div class="card" id="c1">
        <div class="discovering-english">Password dimenticata?</div>
        <p class="chapter">	Inserisci la tua email, ti invieremo un codice di<br>
            conferma e potrai procedere al reset della password</p>
        <input class="box" placeholder="email" id="resetEmail" type="email">
        <button class="button" id="sendPin">Verifica</button>
    </div>

    <!-- PIN input -->
    <div class="card" id="c2" style="display: none;">
        <div class="discovering-english">Inserisci Codice di Verifica</div>
        <p class="chapter">	Se hai fornito l’email correttamente, Inserisci <br>
            il codice di verifica che ti abbiamo inviato.</p>
        <div class="cardo">
            <div class="square" onclick="focusInput(0)" id="square0"></div>
            <div class="square" onclick="focusInput(1)" id="square1"></div>
            <div class="square" onclick="focusInput(2)" id="square2"></div>
            <div class="square" onclick="focusInput(3)" id="square3"></div>
        </div>
        <p class="chapter">Non hai ricevuto il codice?Rimanda</p>
        <button class="button" id="confirmPin">Conferma</button>
    </div>

    <!-- New password input -->
    <div class="card" id="c3"style="display: none;">
        <div class="discovering-english">Crea nuova Password</div>
        <p class="chapter">Inserisci la nuova password per il login</p>
        <input class="box" id="newPassword" placeholder="Nuova Password" type="password">
        <input class="box" id="repeatNewPassword" placeholder="Ripeti Password" type="password">
        <span class="error" aria-live="polite"></span>
        <button class="button" id="resetPassword" disabled>Cambia Password</button>
    </div>

    <!-- Login Redirect -->
    <div class="card" id="c4" style="display: none;"><img class="done-px" src="../images/done-24px-1.svg" />
        <div class="discovering-english">Confermato</div>
        <p class="chapter">Hai resettato correttamente la tua password.</p>
        <button class="button" id="gotoLogin">Login</button>
    </div>

</div>
<script src="../JS/changePasswLogin.js"></script>
</body>
</html>
