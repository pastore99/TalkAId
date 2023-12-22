<!DOCTYPE html>
<html>
<head>
    <title>Login Screen</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        #login {
            width: 300px;
            margin: 0 auto;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        #login h2 {
            margin-bottom: 20px;
        }
        #login label {
            display: block;
            margin-bottom: 10px;
        }
        #login input[type="email"],
        #login input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        #login input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            margin: 20px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        #login input[type="submit"]:hover {
            background-color: #45a049;
        }
        #login a {
            text-decoration: none;
            color: #4CAF50;
        }
        #login a:hover {
            color: #45a049;
        }
    </style>
</head>
<body>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <div id="login">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <button type="submit" value="Login">Login</button>
        </form>
        <a href="#" id="forgotPassword">Password Dimenticata?</a>

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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/JS/login.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>