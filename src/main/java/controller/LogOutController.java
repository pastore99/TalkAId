package controller;

import model.DAO.DAOPersonalInfo;
import model.entity.PersonalInfo;
import model.entity.User;
import model.service.login.Authenticator;
import model.service.user.UserData;
import model.service.user.UserRegistry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logut")
public class LogOutController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("/TalkAID_war_exploded/JSP/login.jsp");
    }
}
