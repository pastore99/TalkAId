package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.entity.User;
import model.service.PersonalInfo.PersonalInfoData;
import model.service.encryption.Encryption;
import model.service.login.Authenticator;
import model.service.user.UserData;

import java.io.IOException;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
            String password = request.getParameter("password");
            String password_control = password.replaceAll("\\s", "");
            int id = (int) request.getSession().getAttribute("id");
            new Authenticator().resetPassword( new UserData().getUser(id).getEmail(), password_control);
            response.getWriter().write("Password cambiata con successo!");
    }
}

