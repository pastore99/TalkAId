<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

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
    <title></title>
</head>
<body>

<input type="text" id="searchInput" placeholder="Cerca per nome o cognome">

<div class="table-container" id="tableContainer">
    <table>
        <thead class="table-header">
        <tr>
            <th>Icona</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Inizio Terapia</th>
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
            <td><svg xmlns="http://www.w3.org/2000/svg" height="16" width="15.5" viewBox="0 0 496 512"><path d="M248 8C111 8 0 119 0 256s111 248 248 248 248-111 248-248S385 8 248 8zm0 96c48.6 0 88 39.4 88 88s-39.4 88-88 88-88-39.4-88-88 39.4-88 88-88zm0 344c-58.7 0-111.3-26.6-146.5-68.2 18.8-35.4 55.6-59.8 98.5-59.8 2.4 0 4.8 .4 7.1 1.1 13 4.2 26.6 6.9 40.9 6.9 14.3 0 28-2.7 40.9-6.9 2.3-.7 4.7-1.1 7.1-1.1 42.9 0 79.7 24.4 98.5 59.8C359.3 421.4 306.7 448 248 448z"/></svg></td>
            <td><%=u.getFirstname()%></td>
            <td><%=u.getLastname()%></td>
            <td><%=u.getActivationDate()%></td>
        </tr>
        <%
                }
            }
        %>
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
<script>
    $(document).ready(function () {
        var tableContainer = document.getElementById('tableContainer');

        // Aggiungi un gestore di eventi all'input di ricerca
        $('#searchInput').on('input', function () {
            var searchText = $(this).val().toLowerCase();

            // Nascondi tutte le righe
            tableContainer.getElementsByTagName('tbody')[0].style.marginTop = '0';

            // Filtra e mostra solo le righe che corrispondono alla ricerca
            $('tbody tr').each(function () {
                var name = $(this).find('td:eq(1)').text().toLowerCase();
                var lastName = $(this).find('td:eq(2)').text().toLowerCase();

                if (name.includes(searchText) || lastName.includes(searchText)) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
        });

        tableContainer.addEventListener('scroll', function () {
            // Calcola il numero di righe da nascondere nel corpo
            var rowsToHide = Math.floor(tableContainer.scrollTop / 35); // 35px è l'altezza approssimativa di una riga
            // Imposta il margine superiore per nascondere le righe nel corpo
            tableContainer.getElementsByTagName('tbody')[0].style.marginTop = rowsToHide * 35 + 'px';
        });
    });
</script>

</body>
</html>
