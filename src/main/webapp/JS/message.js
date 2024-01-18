$(".heading-compose").click(function() {
    $(".side-two").css({
        "left": "0"
    });
});

$(".newMessage-back").click(function() {
    $(".side-two").css({
        "left": "-100%"
    });
});

$(document).ready(function() {
    // When a chat-box is clicked...
    $(".chat-box").on("click", function() {
        // Add a class to the main container to show right container and hide left container
        $(".main-container").addClass("show-right");
    });

    // When back button is clicked...
    $("#back-button").on("click", function() {
        // Remove the class from the main container to hide right container and show left container
        $(".main-container").removeClass("show-right");
    });
});

var count = -1;
setInterval(function(){
    // Make an AJAX request to your servlet, replace the URL as required
    $.ajax({
        url: '../CountMessages',
        type: 'GET',
        data: {
        },
        success: function (response) {
            if(count < 0)
                count = response;
            if(count > 0 && response > count){
                count = response;
                if($("#inputText").val().trim() === ""){
                    location.reload();
                }
            }
        }
    });
}, 10000); // The interval set is 10000ms = 10 seconds

var contact_id;
$(document).ready(function() {
    $('.chat-box').on('click', function() {
        // Remove "active" class from all ".chat-box" divs
        $('.chat-box').removeClass('active');
        // Add "active" class to the clicked ".chat-box"
        $(this).addClass('active');

        var contact_name = $(this).find(".text-head h4").text();
        $('#contactOpened').text(contact_name);

        var unread_indicator = $(this).find('.unread-messages');
        unread_indicator.hide();

        $(".right-container").removeAttr("hidden");
        //access which type of ID the user wanted to read or message to
        contact_id = $(this).data('contact-id');

        if (contact_id === 0) {
            // Prevent user from writing in the text field
            $(".chatbox-input input").prop('readonly', true).attr('placeholder', "Non puoi inviare un messaggio");
        } else {
            // Allow user to write in the text field
            $(".chatbox-input input").prop('readonly', false).attr('placeholder', "Componi un messaggio");
        }
        $.ajax({
            url: '../GetMessages',
            method: 'GET',
            data: { contact_id: contact_id },
            success: function(data) {
                var chatContainer = $(".chat-container");
                chatContainer.empty(); // Clear existing messages if any

                data.forEach(function(message) {
                    // Determine if current user is sender or recipient
                    var messageBoxClass = contact_id == message.sender ? 'friend-message' : 'my-message'; // Replace userId with actual user id

                    var messageBox = $('<div>').addClass('message-box').addClass(messageBoxClass);
                    var messageContent = $('<p>').text(message.body);
                    var messageTime = $('<span>').text(message.sent);
                    messageContent.append($('<br>'), messageTime);
                    messageBox.append(messageContent);

                    chatContainer.append(messageBox);
                });
            },
            error: function(error) {
                // handle error response
            }
        });

    });
});

$(document).ready(function() {
    var input = $('.chatbox-input input');

    input.on('keydown', function (e) {
        if (e.key == 'Enter') {
            e.preventDefault();

            var message = input.val().trim();  // get the message from the input field

            if (message === "") {
                return;  // if no message was typed and 'Enter' is pressed, do nothing
            }
            var date = new Date();
            var hours = date.getHours();
            var minutes = date.getMinutes();

// Pad with '0' to make sure we always get 2 digits
            hours = (hours < 10) ? "0" + hours : hours;
            minutes = (minutes < 10) ? "0" + minutes : minutes;

            var time = hours + ":" + minutes;  // current time in "HH:MM" format
            // append the message to the chat container
            var msgHtml = '<div class="message-box my-message"><p>' + message + '<br><span>' + time + '</span></p></div>';
            $('.chat-container').append(msgHtml);

            // clear the input field
            input.val('');

            // make a POST request to your servlet
            $.ajax({
                url: '../GetMessages',
                type: 'POST',
                data: {
                    // change with actual sender ID
                    recipient: contact_id,
                    body: message
                },
                success: function (response) {
                }
            });
        }
    });
});