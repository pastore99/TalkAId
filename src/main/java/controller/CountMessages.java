package controller;

import model.service.message.MessageManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CountMessages")
public class CountMessages extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int recipientId = (int) session.getAttribute("id");
        MessageManager messageManager = new MessageManager();
        int receivedMessageCount = messageManager.countReceivedMessages(recipientId);

        response.setContentType("text/plain");  // Output is a plain text integer
        try{
            response.getWriter().println(receivedMessageCount);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
