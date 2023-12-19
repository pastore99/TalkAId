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
<!-- grazie -->
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
        <a href="#">Forgot password</a>
    </div>
</body>
</html>