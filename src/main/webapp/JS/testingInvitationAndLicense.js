let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));


$(document).ready(function (){
    $("#invito").click(function () {
        generaInvito();
    });

    $("#licenza").click(function() {
        generaLicenza();
    });
});

function generaInvito(){
    console.log("click!");
    $.get(`${contextPath}/license`);
}

function generaLicenza(){
    console.log("click!");
    $.post(`${contextPath}/license`);
}