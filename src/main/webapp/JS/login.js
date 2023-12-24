// Extract the element identifiers as variables
let resetEmail = $("#resetEmail");
let resetStep1 = $("#resetStep1");
let resetStep2 = $("#resetStep2");
let resetStep3 = $("#resetStep3");
let resetPasswordModal = $('#resetPasswordModal');
let newPassword = $("#newPassword");
let repeatNewPassword = $("#repeatNewPassword");
let resetButton = $("#resetPassword");
let newPasswordError = $(".error");
let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

let THEPASSWORD;

newPassword.on("input", validatePassword);
repeatNewPassword.on("input", validatePassword);

$(document).ready(function () {

    $("#forgotPassword").click(handleForgotPassword);

    var sessionPin;
    $("#sendPin").click(() => {
        event.preventDefault();
        $.post(`${contextPath}/login/reset`, {email: resetEmail.val()}, function(response) {
            sessionPin = response.trim(); // Save the response, which should be your pin
            resetStep1.hide();
            resetStep2.show();
        });
    });

    $("#confirmPin").click(() => {
        event.preventDefault();
        if ($("#pin").val() === sessionPin) {
            // if the PIN entered matches the saved PIN, proceed to step 3
            resetStep2.hide();
            resetStep3.show();
        } else {
            alert('The pin is incorrect');
        }
    });

    $("#resetPassword").click(function() {
        event.preventDefault();
        $.post(`${contextPath}/login/resetpassword`, {
            email: $("#resetEmail").val(),
            newPassword: $("#newPassword").val()
        })
            .done(function(data) {
                alert(data);
                $("#resetPasswordModal").modal('hide');
            })
            .fail(function(err) {
                console.log(err);
            });
    });


    $("#showPassword").click(function (event){
        togglePassword(event);

    });
    $("#hidePassword").click(function (event){
        event.preventDefault();
        togglePassword(event);
    });

    $("#email").blur(function (){
        checkRegexEmail();
    });
});

function handleForgotPassword(event) {
    event.preventDefault();
    resetPasswordModal.modal('show');
    resetStep1.show();
    resetStep2.hide();
    resetStep3.hide();
}


function validatePassword() {
    THEPASSWORD = newPassword.val();
    let confirmPassword = repeatNewPassword.val();

    let check1 = false;
    let check2 = false;

    let regex = new RegExp("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$");

    if (!(regex.test(THEPASSWORD))) {
        showError("Non contiene un carattere maiuscolo, minuscolo, cifra speciale e non lunga 12.");
        check1 = false;
    }
    if (regex.test(THEPASSWORD)) {
        showError("");
        check1 = true;
    }

    if (THEPASSWORD !== confirmPassword) {
        showError("Le password non coincidono.");
        check2 = false;
    }
    if (THEPASSWORD == confirmPassword) {
        showError("");
        check2 = true;
    }

    if(check1 && check2) {
        resetButton.prop('disabled', false);
    }
}

function showError(message) {
    newPasswordError.text(message);
    newPasswordError.addClass('error active');
    // Removes 'active' class when there is no error
    if (!message) {
        newPasswordError.removeClass('error active');
    }
}
function togglePassword(event){
    event.preventDefault();

    const passwordField = $("#password");
    let passwordType = passwordField.attr('type');
    console.log(passwordType);

    if (passwordType === 'password') {
        passwordField.attr("type", "text")
    } else {
        passwordField.attr("type", "password");
    }

    $("#showPassword").toggle()
    $("#hidePassword").toggle()

    return NaN
}

function checkRegexEmail() {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$/;
    const emailField = $("#email");

    if (regex.test(emailField.val())) {
        $("#validEmail").show()
        $("#invalidEmail").hide()

    } else {
        $("#validEmail").hide()
        $("#invalidEmail").show()
    }

    return NaN
}