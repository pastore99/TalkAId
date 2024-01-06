package controller;

import model.entity.PersonalInfo;
import model.entity.User;
import model.entity.UserInfo;
import model.service.login.Authenticator;
import model.service.personalinfo.PersonalInfoManager;
import model.service.user.UserData;
import model.service.user.UserRegistry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

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
            // Login success, defining its Session attributes
            setSessionAttributes(result, request, response);
            //response.sendRedirect("JSP/welcome.jsp"); Viene gestita sopra il redirect in base all'utente
        } else {
            // Login failed, redirect back to the login page
            response.sendRedirect("JSP/login.jsp?error=1");
        }
    }

    private void setSessionAttributes(int id, HttpServletRequest request, HttpServletResponse response){
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
            try {
                response.sendRedirect("JSP/homepagepatient.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            setPatientsInfo(session);
            session.setAttribute("type", "therapist");
            try {
                response.sendRedirect("JSP/homeTherapist.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void setPatientsInfo(HttpSession session){

        UserData userService = new UserData();
        PersonalInfoManager piService = new PersonalInfoManager();

        ArrayList<UserInfo> list_user = new ArrayList<>();
        list_user = userService.getUsersAndPersonalInfoByIdTherapist((Integer) session.getAttribute("id")); //save all patient of X therapist

        session.setAttribute("list_user",list_user);

        PersonalInfo InfoLogged=piService.getPersonalInfoById((Integer) session.getAttribute("id"));
        if(InfoLogged!=null)
            session.setAttribute("NameSurnameLogged", InfoLogged.getFirstname() + " " + InfoLogged.getLastname());
        else  session.setAttribute("NameSurnameLogged",null);
    }
}

