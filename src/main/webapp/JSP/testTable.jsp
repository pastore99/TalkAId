<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .table-container {
            max-height: 200px; /* Altezza massima della tabella */
            overflow-y: auto; /* Rendi scorrevole solo l'asse Y quando necessario */
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Stile per la parte dell'intestazione */
        .table-header {
            position: sticky;
            top: 0;
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>


<div class="table-container" id="tableContainer">
    <table>
        <thead class="table-header">
        <tr>
            <th>Colonna 1</th>
            <th>Colonna 2</th>
            <th>Colonna 3</th>
            <th>Colonna 4</th>
        </tr>
        </thead>
        <tbody>
        <%
            if(session.getAttribute("list_user")!=null) {
                @SuppressWarnings("unchecked")
                ArrayList<UserInfo> list_user=(ArrayList<UserInfo>) session.getAttribute("list_user");
                for(UserInfo u: list_user){
        %>
        <tr>
            <% for (int j = 1; j <= 4; j++) { %>
            <td>Elemento <%= j %> <%=u.getEmail()%></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<script>
    var tableContainer = document.getElementById('tableContainer');

    tableContainer.addEventListener('scroll', function() {
        // Calcola il numero di righe da nascondere nel corpo
        var rowsToHide = Math.floor(tableContainer.scrollTop / 35); // 35px è l'altezza approssimativa di una riga
        // Imposta il margine superiore per nascondere le righe nel corpo
        tableContainer.getElementsByTagName('tbody')[0].style.marginTop = rowsToHide * 35 + 'px';
    });
</script>

</body>
</html>
