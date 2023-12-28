package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.service.PersonalInfo.PersonalInfoData;
import model.service.user.UserData;

import java.io.IOException;

@WebServlet("/ControllPassword")
public class ControllPassword extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String password = (String) request.getAttribute("password");
        UserData utenteData= new UserData();
        if(utenteData.ControlPassword(request.getSession().getAttribute("id")))
        {

        }
        else
        {

        }
    }
}
