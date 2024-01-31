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
const homePages = ["homePagePatient.jsp", "patientReport.jsp", "userReport.jsp"]
const messagePages = ["messageCenter.jsp"]
const calendarPages = ["schedule.jsp"]
const profilePages = ["userArea.jsp", "changePassw.jsp"]

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
    $("#homeNavbar").click(() => redirect("home"));
    $("#messageNavbar").click(() => redirect("messageCenter.jsp"));
    $("#calendarNavbar").click(() => redirect("schedule.jsp"));
    $("#profileNavbar").click(() => redirect("userArea.jsp"));
}

function redirect(where){
    if (where === "home"){
        if (USERTYPE === "patient"){
            window.location.href = "homePagePatient.jsp";
        }else if (USERTYPE === "therapist"){
            window.location.href = "homepageTherapist.jsp";
        }
    }
    else{
        window.location.href = where;
    }
}