package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import model.entity.*;
import model.service.personalinfo.PersonalInfo;

@WebServlet("/homeTherapistServlet")
public class homeTherapistServlet extends HttpServlet {

    public homeTherapistServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        model.service.user.UserData userService = new model.service.user.UserData();
        model.service.personalinfo.PersonalInfo piService= new PersonalInfo();

        ArrayList<UserInfo> list_user=new ArrayList<>();
        User logged= (User) session.getAttribute("user_logged");
        list_user=userService.getUsersAndPersonalInfoByIdTherapist((Integer) logged.getId()); //save all patient of X therapist

        session.setAttribute("list_user",list_user);

        model.entity.PersonalInfo InfoLogged=piService.getPersonalInfoById(logged.getId());
        if(InfoLogged!=null)
        session.setAttribute("NameSurnameLogged", InfoLogged.getFirstname() + " " + InfoLogged.getLastname());
        else  session.setAttribute("NameSurnameLogged",null);

        response.sendRedirect("JSP/homeTherapist.jsp");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
