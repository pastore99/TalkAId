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

/**
 * Servet che si occupa del cambio di password
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ChangePassword.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
            String password = request.getParameter("password");
            String password_control = password.replaceAll("\\s", "");
            int id = (int) request.getSession().getAttribute("id");
            new Authenticator().resetPassword( new UserData().getUser(id).getEmail(), password_control);
            try {
                response.getWriter().write("true");
            }catch(IOException e){
                logger.error("Error writing response", e);
            }
    }
}