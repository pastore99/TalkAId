
$(document).ready(function() {

    showPasswordIcon.toggleClass("hiddenClass");

    $("#showPassword").click((event) => {
        event.preventDefault();
        togglePassword(event);
    });

    $("#hidePassword").click((event) => {
        event.preventDefault();
        togglePassword(event);
    });

    $("#email").blur(() => {
        checkRegexEmail();
    });


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


const showPasswordIcon = $("#showPassword");
const hidePasswordIcon = $("#hidePassword");
const validEmailIcon = $("#validEmail");
const invalidEmailIcon = $("#invalidEmail");


function togglePassword(event) {
    event.preventDefault();

    const passwordField = $("#password");
    let passwordType = passwordField.attr('type');
    console.log(passwordType);

    if (passwordType === 'password') {
        passwordField.attr("type", "text")
    } else {
        passwordField.attr("type", "password");
    }

    showPasswordIcon.toggleClass("hiddenClass")
    hidePasswordIcon.toggleClass("hiddenClass")
}

function checkRegexEmail(){
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const emailField = $("#email");

    if(regex.test(emailField.val())){
        if(validEmailIcon.hasClass("hiddenClass")){
            validEmailIcon.toggleClass("hiddenClass");
        }
        if(!invalidEmailIcon.hasClass("hiddenClass")) {
            invalidEmailIcon.toggleClass("hiddenClass");
        }
    } else{
        if(!validEmailIcon.hasClass("hiddenClass")){
            validEmailIcon.toggleClass("hiddenClass");
        }
        if(invalidEmailIcon.hasClass("hiddenClass")){
            invalidEmailIcon.toggleClass("hiddenClass");
        }
    }
}
