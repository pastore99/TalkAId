package controller;

import model.service.login.Authenticator;
import model.service.user.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet
{
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
                e.printStackTrace();
            }
    }
}