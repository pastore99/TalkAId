<!DOCTYPE html>
<html lang="it">
<head>
    <title>TalkAId - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/login.css">

</head>
<body>
    <div id="login">
        <div id="login-title">
            <h2>Login</h2>
        </div>
        <div id="login-logo">
            <img src="${pageContext.request.contextPath}/images/TalkAidLogo.svg"  alt="TalkAId logo">
        </div>
        <form action="${pageContext.request.contextPath}/login" method="post" id="login-form">
            <input class="form-input" type="email" id="email" name="email" placeholder="example@email.com" required>
            <br>
            <input class="form-input" type="password" id="password" name="password" placeholder="Password" required>
            <br>
            <button type="submit" value="Login" id="login-button">Login</button>
        </form>
        <div id="login-forgot-password">
            <a href="#">Forgot password</a>
        </div>

    </div>
</body>
</html>