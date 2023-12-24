package controller;

import model.service.login.Authenticator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/reset")
public class SendResetPin extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String pin = new Authenticator().resetPassword(email);
        // Store the pin and email in the session for future comparison
        // Send back a result
        response.setContentType("text/plain");
        response.getWriter().println(pin);
    }
}
