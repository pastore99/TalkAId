const homeIcon = $("#homeIcon")
const homeIconS = $("#homeIconSelected")
const messageIcon = $("#messageIcon")
const messageIconS = $("#messageIconSelected")
const calendarIcon = $("#calendarIcon")
const calendarIconS = $("#calendarIconSelected")
const profileIcon = $("#profileIcon")
const profileIconS = $("#profileIconSelected")



let USERTYPE
const page =  window.location.pathname.split('/').pop()
const homePages = ["homePagePatient.jsp", "patientReport.jsp"] //TODO: Mettere le pagine da considerare "Home"
const messagePages = ["messageCenter.jsp"] //TODO: Mettere le pagine da considerare "Message"
const calendarPages = ["schedule.jsp"] //TODO: Mettere le pagine da considerare "Calendar"
const profilePages = ["userArea.jsp"] //TODO: Mettere le pagine da considerare "Profile"

$("document").ready(()=>{
    USERTYPE = $("#userInfo").data("type");
    hideSelectedIcons();
    showRightIcons();
    manageOnClick();
})

function hideSelectedIcons(){
    homeIconS.hide()
    messageIconS.hide()
    calendarIconS.hide()
    profileIconS.hide()
}

function showRightIcons(){
    if (homePages.includes(page)){
        homeIcon.hide();
        homeIconS.show();
    }else if (messagePages.includes(page)){
        messageIcon.hide();
        messageIconS.show();
    }else if (calendarPages.includes(page)){
        calendarIcon.hide();
        calendarIconS.show();
    }else if (profilePages.includes(page)){
        profileIcon.hide();
        profileIconS.show();
    }else{
        $("#navbarDiv").hide();
    }
}

function manageOnClick(){
    $("#home").click(() => redirect("home"));
    $("#message").click(() => redirect("messageCenter.jsp"));
    $("#calendar").click(() => redirect("schedule.jsp"));
    $("#profile").click(() => redirect("userArea.jsp"));
}

function redirect(where){
    if (where === "home"){
        if (USERTYPE === "patient"){
            window.location.href = "homePagePatient.jsp";
        }else if (USERTYPE === "therapist"){
            window.location.href = "homeTherapist.jsp";
        }
    }
    else{
        window.location.href = where;
    }
}