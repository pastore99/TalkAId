
$(document).ready(function() {
    $('#signUpButton').click(function(e) {
        //e.preventDefault();
        let pin = $('#pin').val();
        let cognome = $('#cognome').val();
        let nome = $('#nome').val();
        let email = $('#email').val();
        let password = $('#password').val();

        let registrationData = {
            licenseCode: pin,
            surname: cognome,
            name: nome,
            email: email,
            password: password
        };

        $.ajax({
            type: 'POST',
            url: '../register',
            data: registrationData, // data is passed as form data
            success: function(response) {
                var resultCode = parseInt(response);
                var message;
                switch (resultCode) {
                    case 0:
                        break;
                    case 1:
                        message = "Licenza non valida.";
                        break;
                    case 2:
                        message = "Email già in uso";
                        break;
                    case 3:
                        message = "Impossibile generare l'utente";
                        break;
                    case 4:
                        message = "Impossibile generare anagrafica";
                        break;
                    default:
                        message = "Errore sconosciuto";
                }
                if(resultCode !== 0) {
                    alert(message);
                    console.log('User registered error code: ', response);
                }
                else {
                    window.location.href = 'legal.jsp';
                }
            },
            error: function(error) {
                console.log('Error while registering user: ', error);
            }
        });
    });
});