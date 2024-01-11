const homeIcon = $("#homeIcon")
const homeIconS = $("#homeIconSelected")
const messageIcon = $("#messageIcon")
const messageIconS = $("#messageIconSelected")
const calendarIcon = $("#calendarIcon")
const calendarIconS = $("#calendarIconSelected")
const profileIcon = $("#profileIcon")
const profileIconS = $("#profileIconSelected")

const page =  window.location.pathname.split('/').pop()
const homePages = [] //TODO: Mettere le pagine da considerare "Home"
const messagePages = [] //TODO: Mettere le pagine da considerare "Message"
const calendarPages = [] //TODO: Mettere le pagine da considerare "Calendar"
const profilePages = [] //TODO: Mettere le pagine da considerare "Profile"

$("document").ready(()=>{
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
    $("#home").click(() => redirect("home.jsp")); //TODO: METTERE I GIUSTI REDIRECT
    $("#message").click(() => redirect("message.jsp")); //TODO: METTERE I GIUSTI REDIRECT
    $("#calendar").click(() => redirect("calendar.jsp")); //TODO: METTERE I GIUSTI REDIRECT
    $("#profile").click(() => redirect("profile.jsp")); //TODO: METTERE I GIUSTI REDIRECT
}

function redirect(where){
    window.location.href = where
}