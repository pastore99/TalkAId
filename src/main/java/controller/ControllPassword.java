package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.entity.User;
import model.service.encryption.Encryption;
import model.service.login.Authenticator;
import model.service.user.UserData;

@WebServlet("/ControllPassword")
public class ControllPassword extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            String password = request.getParameter("password");
            Authenticator authenticator = new Authenticator();
            int id = (int) request.getSession().getAttribute("id");
            String email = new UserData().getUser(id).getEmail();
            response.getWriter().write(String.valueOf(authenticator.authenticate(email, password) > 0)); //true se deve abilitare, false altrimenti
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
