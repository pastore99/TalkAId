package controller;

import model.entity.Message;
import model.service.message.MessageManager;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/GetMessages")
public class GetSendMessages extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");

        int contactId = 0;
        try{
            contactId = Integer.parseInt(request.getParameter("contact_id")); // Get the contact's ID from the request
        }catch(NumberFormatException e){
            e.printStackTrace();
        }


        // Retrieve the messages between the user and the contact
        MessageManager messageManager = new MessageManager();
        List<Message> messages = messageManager.retrieveMessages(userId, contactId);

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
        messageManager.markMessagesAsRead(contactId,  userId);
        JsonArray jsonArray = jsonArrayBuilder.build();

        response.setContentType("application/json");
        try{
            response.getWriter().write(jsonArray.toString());
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            HttpSession session = request.getSession();
            int sender = (int) session.getAttribute("id");
            int recipient = Integer.parseInt(request.getParameter("recipient"));
            String body = request.getParameter("body");

            // send the message
            MessageManager messageManager = new MessageManager();
            messageManager.sendMessage(sender, recipient, body);

            response.getWriter().write("Message successfully sent");

        } catch (IOException | NumberFormatException e) {
            // The request parameter could not be parsed as an integer
            try{
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }catch(IOException er){
                er.printStackTrace();
            }

        }
    }
}