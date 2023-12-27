package controller;

import model.entity.PersonalInfo;
import model.entity.User;
import model.service.registration.Registration;
import model.service.user.UserData;
import model.service.user.UserRegistry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")

public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String licenseCode = request.getParameter("licenseCode");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        Registration registration = new Registration();
        int result = registration.registerNewUser(licenseCode, email, password, name, surname);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(result));
        if(result == 0) {
            setSessionAttributes(email, request);
            response.sendRedirect("JSP/legal.jsp");
        }
    }

    private void setSessionAttributes(String email, HttpServletRequest request){
        HttpSession session = request.getSession();

        UserData userData = new UserData();
        UserRegistry userReg = new UserRegistry();

        User user = userData.getUserByIdOrEmail(email);
        PersonalInfo personalInfo = userReg.getPersonalInfo(user.getId());

        session.setAttribute("id", user.getId());
        session.setAttribute("name", personalInfo.getFirstname());

        if(!userData.isTherapist(user)) {
            session.setAttribute("type", "patient");
            session.setAttribute("therapist", user.getIdTherapist());
        }
        else {
            session.setAttribute("type", "therapist");
        }
    }
}
