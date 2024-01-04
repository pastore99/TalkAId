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
import model.service.user.UserData;

import java.io.IOException;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            String password = request.getParameter("password");
            String password_control= password.replaceAll("\\s", "");
            String password_criptata= new Encryption().encryptPassword(password_control);
            int id= (int) request.getSession().getAttribute("id");
            User utente= new UserData().getUser(id);
            new UserData().resetPassword(utente.getEmail(), password_criptata);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

