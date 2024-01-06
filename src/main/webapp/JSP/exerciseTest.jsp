<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        form {
            width: 80%;
            text-align: center;
        }

        input[type="number"], input[type="submit"] {
            width: 100%;
            height: 20vh;
            padding: 10px;
            box-sizing: border-box;
            margin-bottom: 10px;
            font-size: 5em;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/exerciseController" method="post">
    <input type="number" name="exerciseID" value="">
    <!-- <input type="hidden" name="insertionDate" value=""> @michele -->
    <input type="submit">
</form>
</body>
</html>