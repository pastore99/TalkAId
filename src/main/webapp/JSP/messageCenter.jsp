<%@ page import="model.service.message.Conversation" %>
<%@ page import="java.util.List" %>
<%@ page import="model.service.user.UserData" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.service.user.UserRegistry" %>
<%@ page import="model.entity.PersonalInfo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@page contentType="text/html;charset=UTF-8"%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/391827d54c.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="../JS/message.js"></script>
    <link rel="stylesheet" href="../CSS/messageCenter.css">
    <title>Whatsapp Clone</title>
</head>
<body>
<div class="background-green"></div>


<div class="main-container">
    <div class="left-container">

        <!--chats -->
        <div class="chat-list">
            <%
                if(session.getAttribute("id") == "null") {
                    response.sendRedirect("../errorPage/403.html");
                }
                else {
                    int userId = (Integer) session.getAttribute("id");
                    Conversation conversation = new Conversation();
                    List<Integer> list = conversation.retrieveAllTheContacts(userId);
                    for(int contact : list) {
                        UserRegistry ur = new UserRegistry();
                        PersonalInfo data = ur.getPersonalInfo(contact);
                        int toRead = conversation.getUnreadMessagesForConversation(userId, contact);
            %>
            <div class="chat-box" data-contact-id="<%=contact%>">
                <div class="img-box">
                    <img class="img-cover" src="https://t4.ftcdn.net/jpg/00/64/67/27/360_F_64672736_U5kpdGs9keUll8CRQ3p3YaEv2M6qkVY5.jpg" alt="">
                </div>
                <div class="chat-details">
                    <div class="text-head">
                        <h4><%=data.getFirstname()%> <%=data.getLastname()%></h4>
                        <!--<p class="time unread">11:49</p> -->
                    </div>
                    <div class="text-message">
                            <%if(toRead!=0) { %>
                        <b class="unread-messages"><%=toRead%></b>
                            <%} %>
                    </div>
                </div>
            </div>
            <%
                } }
            %>
        </div>

    </div>





    <div class="right-container" hidden="hidden">
        <!--header -->
        <div class="header">
            <div class="img-text">
                <div class="user-img">
                    <img class="dp" src="https://t4.ftcdn.net/jpg/00/64/67/27/360_F_64672736_U5kpdGs9keUll8CRQ3p3YaEv2M6qkVY5.jpg" alt="">
                </div>
                <h4 id="contactOpened"><br><span></span></h4>
            </div>
            <div class="nav-icons">
                <li><i class="fa-solid fa-magnifying-glass"></i></li>
                <li><i class="fa-solid fa-ellipsis-vertical"></i></li>
            </div>
        </div>

        <!--chat-container -->
        <div class="chat-container">
        </div>

        <!--input-bottom -->
        <div class="chatbox-input" hidden="hidden">
            <i class="fa-regular fa-face-grin"></i>
            <i class="fa-sharp fa-solid fa-paperclip"></i>
            <input type="text" placeholder="Componi un messaggio">
            <i class="fa-solid fa-microphone"></i>
        </div>
    </div>

</div>
</body>
</html>