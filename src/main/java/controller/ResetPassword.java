package controller;

import model.service.login.Authenticator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/login/resetpassword")
public class ResetPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("newPassword");
        Authenticator authenticator = new Authenticator();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        if(authenticator.resetPassword(email, password)){
            try{
                response.getWriter().write("Password cambiata con successo!");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
