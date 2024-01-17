<!DOCTYPE html>
<html lang="it">
<head>
    <title>TalkAId - Registrati</title>
    <%@page contentType="text/html;charset=UTF-8"%>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
</head>
<body>
<div id="loginPage">
    <div id="loginTitle">
        <h2>Registrati</h2>
    </div>
    <div id="loginLogo">
        <img src="../images/TalkAidLogo.svg"  alt="TalkAId logo">
    </div>
    <div class="inputDiv">
        <div class="icon iconLeft">
            <img src="../images/registration/icon-user-outline.svg" alt="usericon">
        </div>
        <input class="formInput" type="number" id="pin" name="email" placeholder="Inserisci il PIN o Licenza" pattern="\b(\d{4}|\d{8})\b" title="Devi inserire un numero di 4 o 8 cifre" required>
        <div class="icon iconRight"></div>
    </div>

    <div class="inputDiv">
        <div class="icon iconLeft">
            <img src="../images/registration/icon-user-outline.svg" alt="usericon">
        </div>
        <input class="formInput" type="text" id="cognome" placeholder="Il tuo cognome" pattern="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$" title="Il cognome deve contenere solo lettere, spazi, trattini, apostrofi e accenti." required>
        <div class="icon iconRight"></div>
    </div>

    <div class="inputDiv">
        <div class="icon iconLeft">
            <img src="../images/registration/icon-user-outline.svg" alt="usericon">
        </div>
        <input class="formInput" type="text" id="nome" placeholder="Il tuo nome" pattern="^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$" title="Il nome deve contenere solo lettere, spazi, trattini, apostrofi e accenti." required>
        <div class="icon iconRight"></div>
    </div>

    <div class="inputDiv">
        <div class="icon iconLeft">
            <img src="../images/emailIcon.svg" alt="emailIcon">
        </div>
        <input class="formInput" type="email" id="email" name="email" placeholder="example@email.com" required>
        <div class="icon iconRight">
            <img id="validEmail" src="../images/valid.svg" alt="validEmail" class="hiddenClass">
            <img id="invalidEmail" src="../images/invalid.svg" alt="invalidEmail" class="hiddenClass">
        </div>
    </div>

    <br>
    <div class="inputDiv">
        <div class="icon iconLeft">
            <img src="../images/passwordIcon.svg" alt="passwordIcon">
        </div>
        <input class="formInput" type="password" id="password" name="password" placeholder="Password" required>
        <div class="icon iconRight">
            <button class="togglePassword hiddenClass" id="hidePassword"><img src="../images/hidePassword.svg" alt="hidePasswordIcon"></button>
            <button class="togglePassword hiddenClass" id="showPassword"><img src="../images/showPassword.svg" alt="showPasswordIcon"></button>
        </div>
    </div>
    <br>
    <button type="submit" value="Login" id="signUpButton">Registrati</button>
    <div id="loginRegister">
        <p>Hai un account? <a href="login.jsp">Accedi</a> </p>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
<script src="../JS/registration.js"></script>
<link rel="stylesheet" href="../CSS/login.css">
</body>
</html>