$(document).ready(function () {
    let canvasDiv = $("#myChart");
    let parentContainer = canvasDiv.parent();

    $("#home").click(() => redirect("homepageTherapist.jsp"));
    $("#message").click(() => redirect("messageCenter.jsp"));
    $("#agenda").click(() => redirect("schedule.jsp"));
    $("#docInfo").click(() => redirect("userArea.jsp"));

    canvasDiv.width(parentContainer.width());
    canvasDiv.height(parentContainer.height());
})

function showExercises(){
    $("#content").hide();
    $("#conditionsDiv").hide();
    $("#exercisesDiv").show();
}

function showCondition(){
    $("#content").hide();
    $("#exercisesDiv").hide();
    $("#conditionsDiv").show();
}

function showPatient(){
    $("#exercisesDiv").hide();
    $("#conditionsDiv").hide();
    $("#content").show();
}

function redirect(where){
    window.location.href = where;
}