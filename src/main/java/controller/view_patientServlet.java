package controller;

import model.entity.User;
import model.entity.UserInfo;
import model.service.personalinfo.PersonalInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class view_patientServlet {



    public view_patientServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        model.service.user.UserData userService = new model.service.user.UserData();
        model.service.personalinfo.PersonalInfo piService= new PersonalInfo();


        response.sendRedirect("JSP/view_patient.jsp");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }


}
