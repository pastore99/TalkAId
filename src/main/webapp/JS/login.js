const resetEmail = $("#resetEmail");
const c1 = $("#c1");
const c2 = $("#c2");
const c3 = $("#c3");
const c4 = $('#c4');
const loginPage = $('#loginPage');
const container = $('#container');
const newPassword = $("#newPassword");
const repeatNewPassword = $("#repeatNewPassword");
const resetButton = $("#resetPassword");
const newPasswordError = $(".error");
const showPasswordIcon = $("#showPassword");
const hidePasswordIcon = $("#hidePassword");
const validEmailIcon = $("#validEmail");
const invalidEmailIcon = $("#invalidEmail");
const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));


let myDiv = document.getElementById('container');
myDiv.classList.add('hidden');
let loginPageDiv = document.getElementById('loginPage');

$(document).ready(function startUp() {

    showPasswordIcon.toggleClass("hiddenClass");

    $("#password").keypress(function(event) {
        if (event.which === 13) {
            $("#loginButton").click()
        }
    })


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


let THEPASSWORD;

newPassword.on("input", validatePassword);
repeatNewPassword.on("input", validatePassword);

$(document).ready(function startUp() {


    let currentSquare = 0;

    function focusInput(squareIndex) {
        currentSquare = squareIndex;
        updateSquares();
    }

    document.addEventListener('keydown', function(event) {

        let key = event.key;

        if (key >= '0' && key <= '9') {
            let squareId = 'square' + currentSquare;
            let square = document.getElementById(squareId);
            square.textContent = key;

            currentSquare++;
            if (currentSquare > 3) {
                currentSquare = 0;
            }
            updateSquares();
        }
    });

    function updateSquares() {
        for (let i = 0; i < 4; i++) {
            let squareId = 'square' + i;
            let square = document.getElementById(squareId);
            square.style.backgroundColor = (i === currentSquare) ? '#c0c0c0' : '';
        }
    }

    $("#handleForgotPassword").click((event) => {
        event.preventDefault();
        myDiv.classList.remove('hidden');
        container.show();
        loginPageDiv.classList.add('hidden');
        loginPage.hide();
    });

    $("#sendPin").click((event) => {
        event.preventDefault();
        $.post(`${contextPath}/login/reset`, {email: resetEmail.val()}, function(response) {
            response = response.trim(); // Trim the response
            if(response === "NA") {
                // Here, the servlet will return "NA" if it could not find the email
                alert("Email non registrata nel nostro sistema. Verificane la correttezza");
            } else {
                sessionPin = response; // Save the response, which should be your pin
                c1.hide();
                c2.show();
            }
        });
    });

    $("#confirmPin").click((event) => {
        event.preventDefault();
        let data = '';
        for (let i = 0; i < 4; i++) {
            let squareId = 'square' + i;
            let square = document.getElementById(squareId);
            data += square.textContent || '0';
        }
        if (data === sessionPin) {
            // if the PIN entered matches the saved PIN, proceed to step 3
            c2.hide();
            c3.show();
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
                c3.hide();
                c4.show();
            })
            .fail(function(err) {
                console.log(err);
            });
    });

    $("#gotoLogin").click(function(event) {
        event.preventDefault();
        myDiv.classList.add('hidden');
        container.hide();
        loginPageDiv.classList.remove('hidden');
        loginPage.show();
    });

    $("#gotoLoginArrow").click(function(event) {
        event.preventDefault();
        myDiv.classList.add('hidden');
        container.hide();
        loginPageDiv.classList.remove('hidden');
        loginPage.show();
    });
});

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