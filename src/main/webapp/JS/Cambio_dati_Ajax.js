$(document).ready(function() {
    // Ascolta l'evento di clic sul pulsante
    $("#Controlla").click(function () {
        var password = $("#password").val();
        var email = $("#email").val();
        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();
        var address = $("#address").val();
        var phonenumber = $("#phonenumber").val();
        $.ajax({
            url: '${pageContext.request.contextPath}/changeDate',
            type: 'POST',
            dataType: 'json',
            data: {
                password: password,
                email: email,
                firstname: firstname,
                lastname: lastname,
                address: address,
                phonenumber: phonenumber
            },
        })
            .done(function(result)
            {
                alert(result.result)
            })
            .fail(function(jqXHR, textStatus, errorThrown) {
                console.error('Errore nella richiesta AJAX:', textStatus, errorThrown);    // Aggiungi dettagli dell'errore alla console o visualizzali a schermo
                console.error(jqXHR.responseText);    // Aggiorna l'elemento con l'ID 'messaggioErrore' con il messaggio di errore dettagliato
                alert('Si è verificato un errore: ' + errorThrown);
            });
    })
})