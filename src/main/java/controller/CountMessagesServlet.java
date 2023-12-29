package controller;

import model.service.message.Conversation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CountMessages")
public class CountMessagesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int recipientId = (int) session.getAttribute("id");
        Conversation conversation = new Conversation();
        int receivedMessageCount = conversation.countReceivedMessages(recipientId);

        response.setContentType("text/plain");  // Output is a plain text integer
        response.getWriter().println(receivedMessageCount);
    }

}
