<!DOCTYPE html>
<html lang="it">
<head>
    <title>TalkAId - Login</title>
    <%@page contentType="text/html;charset=UTF-8"%>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
</head>
<body>
    <div id="loginPage">
        <div id="loginTitle">
            <h2>Login</h2>
        </div>
        <div id="loginLogo">
            <img src="../images/TalkAidLogo.svg"  alt="TalkAId logo">
        </div>
        <form id="loginForm" action="../login" method="post">
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
            <div id="loginForgot">
                <a href="#" id="handleForgotPassword">Password Dimenticata?</a>
            </div>
            <button type="submit" value="Login" id="loginButton">Login</button>
        </form>
        <div id="loginRegister">
            <p>Non hai un account? <a href="registration.jsp">Registrati</a> </p>
         </div>
        </div>

    <div class="container" id="container">

        <button id="gotoLoginArrow"><span></span></button>
        <!-- Email input -->
        <div class="card" id="c1" >
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
        <div class="card" id="c3" style="display: none;">
            <div class="discovering-english">Crea nuova Password</div>
            <p class="chapter">Inserisci la nuova password per il login</p>
            <input class="box" id="newPassword" placeholder="Nuova Password" type="password">
            <input class="box" id="repeatNewPassword" placeholder="Ripeti Password" type="password">
            <span class="error" aria-live="polite"></span>
            <button class="button" id="resetPassword" disabled>Cambia Password</button>
        </div>

        <!-- Login Redirect -->
        <div class="card" id="c4" style="display: none;"><img alt="doneIcon" class="done-px" src="../images/done-24px-1.svg" />
            <div class="discovering-english">Confermato</div>
            <p class="chapter">Hai resettato correttamente la tua password.</p>
            <button class="button" id="gotoLogin">Login</button>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="../JS/login.js"></script>
    <link rel="stylesheet" href="../CSS/login.css">
    <link rel="stylesheet" href="../CSS/changePasswLogin.css" />
</body>
</html>