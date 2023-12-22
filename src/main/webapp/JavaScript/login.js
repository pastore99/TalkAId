$(document).ready(function startUp(){
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

})

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

function checkRegexEmail(){
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const emailField = $("#email");

    if(regex.test(emailField.val())){
        $("#validEmail").show()
        $("#invalidEmail").hide()

    }
    else{
        $("#validEmail").hide()
        $("#invalidEmail").show()
    }

    return NaN
}