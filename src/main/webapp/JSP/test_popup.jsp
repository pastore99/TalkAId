<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Popup Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        #popup {
            display: none;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 2;
        }

        #overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1;
        }

        #content {
            transition: opacity 0.5s ease-in-out;
        }

        .overlay-visible #content {
            opacity: 0.5;
        }
    </style>
</head>
<body>

<div id="overlay" onclick="closePopup()"></div>

<div id="content">
    <button onclick="openPopup()">Mostra Popup</button>
</div>

<div id="popup">
    <p id="popupMessage"></p>
    <button onclick="updatePopup()">OK</button>
</div>

<script>
    function openPopup() {
        document.getElementById('overlay').classList.add('overlay-visible');
        document.getElementById('popup').style.display = 'block';

        // Mostra un messaggio di default
        document.getElementById('popupMessage').innerHTML = "Messaggio di default";
    }

    function updatePopup() {
        // Chiama la servlet per aggiornare il messaggio del popup
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById('popupMessage').innerHTML = this.responseText;
            }
        };
        xmlhttp.open("POST", "../PopupServlet", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("action=updatePopup");
    }

    function closePopup() {
        document.getElementById('overlay').classList.remove('overlay-visible');
        document.getElementById('popup').style.display = 'none';
    }
</script>

</body>
</html>
