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
import model.service.condition.Condition;
import model.service.personalinfo.PersonalInfo;

@WebServlet("/view_patientServlet")
public class view_patientServlet extends HttpServlet {



    public view_patientServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        model.service.user.UserData userService = new model.service.user.UserData();
        model.service.personalinfo.PersonalInfo piService= new PersonalInfo();

        int userId = Integer.parseInt(request.getParameter("userId"));

        User u = userService.getUserByIdOrEmail(userId);
        model.entity.PersonalInfo pi = piService.getPersonalInfoById(userId);

        UserInfo user_inf= new UserInfo(u.getId(),u.getEmail(),u.getActivationDate(),pi.getFirstname(),pi.getLastname(),pi.getDateOfBirth(),pi.getGender(),pi.getAddress(),pi.getSsn(),pi.getPhone());

        session.setAttribute("user_selected",user_inf);

        /*CONDITION MENU*/
        model.service.condition.Condition ConditionService= new Condition();

        ArrayList<model.entity.Condition> list_PatientCondition=new ArrayList<>();
        ArrayList<model.entity.Condition> list_NOTPatientCondition=new ArrayList<>();
        list_PatientCondition=ConditionService.getConditionsOfPatient(userId);
        list_NOTPatientCondition=ConditionService.getConditionsNOTOfPatient(userId);



        session.setAttribute("list_PatientCondition",list_PatientCondition);
        session.setAttribute("list_NOTPatientCondition",list_NOTPatientCondition);

        response.sendRedirect("JSP/view_patient.jsp");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }


}
