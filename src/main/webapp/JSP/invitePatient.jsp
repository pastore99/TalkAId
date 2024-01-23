<!DOCTYPE html>
<html lang="it">
<head>
    <title>TalkAId - Invita Paziente</title>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
</head>
<body>
<%
    if(session.getAttribute("type")==null || !session.getAttribute("type").equals("therapist")) {
        response.sendRedirect("../errorPage/403.html");
    }
%>
<div id="InvitePage">
    <div id="InviteTitle">
        <p>Invita Paziente</p>
    </div>
    <br>
    <form id="InviteForm" action="../invitePatient" method="post">
        <div class="inputDiv">
            <div class="icon iconLeft">
                <img alt="emailIcon" class="img" src="../images/registration/icon-email.svg" />
            </div>
            <input class="formInput" type="email" name="email" placeholder="La sua email" required>
        </div>
        <br>
        <div class="inputDiv">
            <div class="icon iconLeft">
                <img alt="userIcon" class="img" src="../images/registration/icon-user-outline.svg" />
            </div>
            <input class="formInput" type="text" name="nome" placeholder="Nome" required>
        </div>
        <br>
        <div class="inputDiv">
            <div class="icon iconLeft">
                <img alt="userImg" class="img" src="../images/registration/icon-user-outline.svg" />
            </div>
            <input class="formInput" type="text" name="cognome" placeholder="Cognome" required>
        </div>
        <br>
        <br>
        <button type="submit" value="Invite" id="InviteButton">Invita</button>
        <a id="return" onclick="hideInvite()"> < Torna alla home</a>
    </form>
</div>
<script>
    function hideInvite(){
        $("#invitePopup").hide();
    }
</script>

<link rel="stylesheet" href="../CSS/invitePatient.css">
</body>
</html>