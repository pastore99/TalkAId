package controller;

import model.service.login.Authenticator;
import model.service.user.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ControllPassword")
public class CheckCurrentPassword extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CheckCurrentPassword.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String password = request.getParameter("password");
        Authenticator authenticator = new Authenticator();
        int id = (int) request.getSession().getAttribute("id");
        String email = new UserData().getUser(id).getEmail();
        try {
            response.getWriter().write(String.valueOf(authenticator.authenticate(email, password) > 0)); //true se deve abilitare, false altrimenti
        }catch(IOException e){
            logger.error("Error writing response", e);
        }
    }
}
