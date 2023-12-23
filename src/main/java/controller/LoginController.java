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

@WebServlet("/login")
public class LoginController extends HttpServlet {

    /**
     Your AuthenticationService instance should go here
     **/
    private Authenticator authService;

    public void init() {
        this.authService = new Authenticator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int result = authService.authenticate(email, password);

        if (result > 0) {
            // Login success, defining its Session attributes
            setSessionAttributes(result,request);
            response.sendRedirect("JSP/welcome.jsp");
        } else {
            // Login failed, redirect back to the login page
            response.sendRedirect("JSP/login.jsp?error=1");
        }
    }

    private void setSessionAttributes(int id, HttpServletRequest request){
        HttpSession session = request.getSession();

        UserData userData = new UserData();
        UserRegistry userReg = new UserRegistry();

        User user = userData.getUserByIdOrEmail(id);
        PersonalInfo personalInfo = userReg.getPersonalInfo(id);

        session.setAttribute("id", id);
        session.setAttribute("name", personalInfo.getFirstname());

        if(userData.isTherapist(user)) {
            session.setAttribute("type", "patient");
            session.setAttribute("therapist", user.getIdTherapist());
        }
        else {
            session.setAttribute("type", "therapist");
        }
    }

}