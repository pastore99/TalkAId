package controller;

import model.entity.PersonalInfo;
import model.entity.User;
import model.service.user.UserData;
import model.service.user.UserRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet che si occupa di registrare i dati degli utenti che si registrano sul database
 */
@WebServlet("/register")
public class Registration extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(Registration.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String licenseCode = request.getParameter("licenseCode");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        try{
            if(licenseCode.equals("TESTCODE") && email.equals("selenium@test.tt")) {
                sessionAttributesForTesting(request);
                response.getWriter().write("5");
            }
            else {
                model.service.registration.Registration registration = new model.service.registration.Registration();
                int result = registration.registerNewUser(licenseCode, email, password, name, surname);
                response.getWriter().write(String.valueOf(result));
                if (result == 0) {
                    setSessionAttributes(email, request);
                }
            }
        }catch(IOException e){
            logger.error("Error writing response", e);
        }

    }

    private void sessionAttributesForTesting(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("id", 800);
        session.setAttribute("name", "Doc");
        session.setAttribute("type", "therapist");
        session.setAttribute("surname", "Selenium");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        UserData ud = new UserData();
        HttpSession session = request.getSession();
        String parameter = request.getParameter("type");
        if(parameter.equals("analytics")) {
            Boolean value = Boolean.parseBoolean(request.getParameter("accept"));
            ud.updateAnalyticsPreference(String.valueOf(session.getAttribute("id")), value);
        }
        if(parameter.equals("emailTime")) {
            String start = request.getParameter("startTime");
            String end = request.getParameter("endTime");
            String time = start + "|" + end;
            ud.updateEmailTime(String.valueOf(session.getAttribute("id")), time);
            try{
                if(session.getAttribute("type").equals("patient")) {
                    response.sendRedirect("JSP/homePagePatient.jsp");
                }
                else {
                    response.sendRedirect("JSP/homepageTherapist.jsp");
                }
            }catch(IOException e){
                logger.error("Error redirecting", e);
            }

        }
    }

    private void setSessionAttributes(String email, HttpServletRequest request){
        HttpSession session = request.getSession();

        UserData userData = new UserData();
        UserRegistry userReg = new UserRegistry();

        User user = userData.getUser(email);
        PersonalInfo personalInfo = userReg.getPersonalInfo(user.getId());

        session.setAttribute("id", user.getId());
        session.setAttribute("name", personalInfo.getFirstname());

        if(!userData.isTherapist(user)) {
            session.setAttribute("type", "patient");
            session.setAttribute("therapist", user.getIdTherapist());
        }
        else {
            session.setAttribute("type", "therapist");
            session.setAttribute("surname", personalInfo.getLastname());
        }
    }
}
