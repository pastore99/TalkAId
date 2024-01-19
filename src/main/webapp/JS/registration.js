$(document).ready(function() {

    // Get the input fields and the sign up button
    var passwordInput = $("#password");
    var emailInput = $("#email");
    var nomeInput = $("#nome");
    var cognomeInput = $("#cognome");
    var pinInput = $("#pin");
    var signUpButton = $("#signUpButton");

    // Function to toggle password visibility
    function togglePassword(event) {
        event.preventDefault();

        const passwordField = $("#password");
        let passwordType = passwordField.attr('type');

        if (passwordType === 'password') {
            passwordField.attr("type", "text")
        } else {
            passwordField.attr("type", "password");
        }

        $("#showPassword").toggleClass("hiddenClass");
        $("#hidePassword").toggleClass("hiddenClass");
    }

    // Function to check email regex
    function checkRegexEmail() {
        const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const emailField = $("#email");

        if(regex.test(emailField.val())){
            if($("#validEmail").hasClass("hiddenClass")){
                $("#validEmail").toggleClass("hiddenClass");
            }
            if(!$("#invalidEmail").hasClass("hiddenClass")) {
                $("#invalidEmail").toggleClass("hiddenClass");
            }
        } else{
            if(!$("#validEmail").hasClass("hiddenClass")){
                $("#validEmail").toggleClass("hiddenClass");
            }
            if($("#invalidEmail").hasClass("hiddenClass")){
                $("#invalidEmail").toggleClass("hiddenClass");
            }
        }
    }

    // Add event listeners
    $("#showPassword").click(togglePassword);
    $("#hidePassword").click(togglePassword);
    $("#email").blur(checkRegexEmail);

    signUpButton.hover(function(event){
        // Password regex
        var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{12,}$/;
        // Email regex
        var emailPattern = /^\S+@\S+\.\S+$/;
        // Nome and Cognome regex
        var namePattern = /^[A-Z][a-zA-Z' ]{1,}$/;
        // Pin regex
        var pinPattern = /^[0-9A-Z]{8}$|^[0-9A-F]{4}$/;

        // Check if the values match their respective regex
        if (!passwordPattern.test(passwordInput.val()) || !emailPattern.test(emailInput.val()) || !namePattern.test(nomeInput.val()) || !namePattern.test(cognomeInput.val()) || !pinPattern.test(pinInput.val())){
            // Prevent the default action if any of the regex don't match
            signUpButton.prop('disabled', true);
            event.preventDefault();
            alert("Per favore, compila correttamente i campi.");
        }
        else {
            signUpButton.prop('disabled', false);
        }
    });

    $('#signUpButton').click(function(e) {
        e.preventDefault();
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
                let resultCode = parseInt(response);
                let message;
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
                    case 5:
                        window.location.href = 'homepageTherapist.jsp';
                        break;
                    default:
                        message = "Errore sconosciuto";
                }
                if(resultCode !== 0 && resultCode !== 5) {
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
