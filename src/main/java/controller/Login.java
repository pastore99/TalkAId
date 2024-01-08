package controller;

import model.entity.PersonalInfo;
import model.entity.User;
import model.service.login.Authenticator;
import model.service.user.UserData;
import model.service.user.UserRegistry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    private Authenticator authService;
    private UserData userData;
    private UserRegistry userReg;

    public void init() {
        this.authService = new Authenticator();
        this.userData = new UserData();
        this.userReg = new UserRegistry();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int result = authService.authenticate(email, password);

        if (result > 0) {
            // Login success, defining its Session attributes and the redirect page
            response.sendRedirect(setSessionAttributes(result, request));
        } else {
            // Login failed, redirect back to the login page
            response.sendRedirect("JSP/login.jsp?error=1");
        }
    }

    private String setSessionAttributes(int id, HttpServletRequest request){
        HttpSession session = request.getSession();

        userData = new UserData();
        userReg = new UserRegistry();

        User user = userData.getUser(id);
        PersonalInfo personalInfo = userReg.getPersonalInfo(id);

        session.setAttribute("id", id);
        session.setAttribute("name", personalInfo.getFirstname());

        if(!userData.isTherapist(user)) {

            session.setAttribute("type", "patient");
            session.setAttribute("therapist", user.getIdTherapist());
            return "JSP/homePagePatient.jsp";
        }
        else {
            session.setAttribute("type", "therapist");
            session.setAttribute("surname", personalInfo.getLastname());
            return "JSP/homeTherapist.jsp";
        }
    }
}

