const resetEmail = $("#resetEmail");
const c1 = $("#c1");
const c2 = $("#c2");
const c3 = $("#c3");
const c4 = $('#c4');
const resetButton = $("#resetPassword");
const newPasswordError = $(".error");
const newPassword = $("#newPassword");
const repeatNewPassword = $("#repeatNewPassword");
const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));


let THEPASSWORD;

newPassword.on("input", validatePassword);
repeatNewPassword.on("input", validatePassword);

$(document).ready(function startUp() {

    var currentSquare = 0;

    function focusInput(squareIndex) {
        currentSquare = squareIndex;
        updateSquares();
    }

    document.addEventListener('keydown', function(event) {

        var key = event.key;

        if (key >= '0' && key <= '9') {
            var squareId = 'square' + currentSquare;
            var square = document.getElementById(squareId);
            square.textContent = key;

            currentSquare++;
            if (currentSquare > 3) {
                currentSquare = 0;
            }
            updateSquares();
        }
    });

    function updateSquares() {
        for (var i = 0; i < 4; i++) {
            var squareId = 'square' + i;
            var square = document.getElementById(squareId);
            square.style.backgroundColor = (i === currentSquare) ? '#c0c0c0' : '';
        }
    }
    $("#sendPin").click((event) => {
        console.log("Send Pin button clicked");
        event.preventDefault();
        $.post(`${contextPath}/login/reset`, {email: resetEmail.val()}, function(response) {
            response = response.trim(); // Trim the response
            if(response == "NA") {
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
        var data = '';
        for (var i = 0; i < 4; i++) {
            var squareId = 'square' + i;
            var square = document.getElementById(squareId);
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
        window.location.href = 'login.jsp';
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