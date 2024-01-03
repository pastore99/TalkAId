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
            String password_control= password.replaceAll("\\s", "");
            Authenticator authenticator = new Authenticator();
            int id = (int) request.getSession().getAttribute("id");
            String email = new UserData().getUser(id).getEmail();
            JsonObject jsonResponse = new JsonObject();
            if (authenticator.authenticate(email, password) > 0) {
                jsonResponse.addProperty("result", true);
                request.getSession().setAttribute("autorizzato",true);
                String jsonString = new Gson().toJson(jsonResponse);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonString);
            } else {
                jsonResponse.addProperty("result", false);
                String jsonString = new Gson().toJson(jsonResponse);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonString);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
