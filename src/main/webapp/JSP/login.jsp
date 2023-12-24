<!DOCTYPE html>
<html lang="it">
<head>
    <title>TalkAId - Login</title>
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
                    <img hidden id="validEmail" src="../images/valid.svg" alt="emailIcon">
                    <img hidden id="invalidEmail" src="../images/invalid.svg" alt="emailIcon">
                </div>
            </div>

            <br>
            <div class="inputDiv">
                <div class="icon iconLeft">
                    <img src="../images/passwordIcon.svg" alt="emailIcon">
                </div>
                <input class="formInput" type="password" id="password" name="password" placeholder="Password" required>
                <div class="icon iconRight">
                    <button hidden class="togglePassword" id="hidePassword"><img src="../images/hidePassword.svg" alt="ShowPasswordIcon"></button>
                    <button class="togglePassword" id="showPassword"><img src="../images/showPassword.svg" alt="ShowPasswordIcon"></button>
                </div>
            </div>
            <br>
            <div id="loginForgot">
                <a href="#">Password Dimenticata?</a>
            </div>
            <button type="submit" value="Login" id="loginButton">Login</button>
        </form>
        <div id="loginRegister">
            <p>Non hai un account? <a href="#">Registrati</a> </p>
         </div>

        <div id="resetPasswordModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-body">
                        <form>
                            <!-- Email input -->
                            <div id="resetStep1">
                                <input type="email" id="resetEmail" placeholder="Email">
                                <button id="sendPin">Invia PIN</button>
                            </div>
                            <!-- PIN input -->
                            <div id="resetStep2" style="display: none;">
                                <input type="text" id="pin" placeholder="PIN">
                                <button id="confirmPin">Conferma PIN</button>
                            </div>
                            <!-- New password input -->
                            <div id="resetStep3" style="display: none;">
                                <input type="password" id="newPassword" placeholder="Nuova Password">
                                <input type="password" id="repeatNewPassword" placeholder="Ripeti Password">
                                <span class="error" aria-live="polite"></span>
                                <button id="resetPassword" disabled>Cambia Password</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <link rel="stylesheet" href="../CSS/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="../JS/login.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>