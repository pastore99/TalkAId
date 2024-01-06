const resetEmail = $("#resetEmail");
const resetStep1 = $("#resetStep1");
const resetStep2 = $("#resetStep2");
const resetStep3 = $("#resetStep3");
const resetPasswordModal = $('#resetPasswordModal');
const newPassword = $("#newPassword");
const repeatNewPassword = $("#repeatNewPassword");
const resetButton = $("#resetPassword");
const newPasswordError = $(".error");
const showPasswordIcon = $("#showPassword");
const hidePasswordIcon = $("#hidePassword");
const validEmailIcon = $("#validEmail");
const invalidEmailIcon = $("#invalidEmail");
const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

let THEPASSWORD;

newPassword.on("input", validatePassword);
repeatNewPassword.on("input", validatePassword);

$(document).ready(function startUp() {

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

    $("#forgotPassword").click(handleForgotPassword);

    let sessionPin;
    /*
    $("#sendPin").click((event) => {
        event.preventDefault();
        $.post(`${contextPath}/login/reset`, {email: resetEmail.val()}, function(response) {
            sessionPin = response.trim(); // Save the response, which should be your pin
            resetStep1.hide();
            resetStep2.show();
        });
    });
    */
    $("#sendPin").click((event) => {
        event.preventDefault();
        $.post(`${contextPath}/login/reset`, {email: resetEmail.val()}, function(response) {
            response = response.trim(); // Trim the response
            if(response == "NA") {
                // Here, the servlet will return "NA" if it could not find the email
                alert("Email non registrata nel nostro sistema. Verificane la correttezza");
            } else {
                sessionPin = response; // Save the response, which should be your pin
                resetStep1.hide();
                resetStep2.show();
            }
        });
    });

    $("#confirmPin").click((event) => {
        event.preventDefault();
        if ($("#pin").val() === sessionPin) {
            // if the PIN entered matches the saved PIN, proceed to step 3
            resetStep2.hide();
            resetStep3.show();
        } else {
            alert('The pin is incorrect');
        }
    });

    $("#resetPassword").click(function(event) {
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
    if (THEPASSWORD === confirmPassword) {
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