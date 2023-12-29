package controller;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Message;
import model.service.message.Conversation;
import java.util.List;
import javax.json.*;

@WebServlet("/GetMessages")
public class GetMessageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");

        int contactId = Integer.parseInt(request.getParameter("contact_id")); // Get the contact's ID from the request

        // Retrieve the messages between the user and the contact
        Conversation conversation = new Conversation();
        List<Message> messages = conversation.retrieveMessages(userId, contactId);

        // Now build the JSON response
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Message message : messages) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("sender", message.getSender());
            jsonObjectBuilder.add("recipient", message.getRecipient());
            jsonObjectBuilder.add("body", message.getBody());
            // Convert timestamp to format HH:MM
            Timestamp timestamp = message.getSent();
            java.util.Date date = new java.util.Date(timestamp.getTime());
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("HH:mm");
            String formattedDate = dateFormat.format(date);

            jsonObjectBuilder.add("sent", formattedDate);
            jsonArrayBuilder.add(jsonObjectBuilder.build());
        }
        conversation.markMessagesAsRead(contactId,  userId);
        JsonArray jsonArray = jsonArrayBuilder.build();

        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int sender = (int) session.getAttribute("id");
            int recipient = Integer.parseInt(request.getParameter("recipient"));
            String body = request.getParameter("body");

            // send the message
            Conversation conversation = new Conversation();
            conversation.sendMessage(sender, recipient, body);

            response.getWriter().write("Message successfully sent");

        } catch (NumberFormatException e) {
            // The request parameter could not be parsed as an integer
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}