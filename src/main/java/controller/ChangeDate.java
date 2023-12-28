package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.service.PersonalInfo.PersonalInfoData;
import model.service.user.UserData;

import java.io.IOException;

@WebServlet("/changeDate")
public class ChangeDate extends  HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Email = request.getParameter("email")!=null ? request.getParameter("email") : request.getParameter("emailDefault");
        String FirstName = request.getParameter("firstname")!=null ? request.getParameter("firstname") : request.getParameter("FirstNameDefault");
        String LastName = request.getParameter("lastname")!=null ? request.getParameter("lastname") : request.getParameter("LastNameDefault");
        String Address = request.getParameter("address")!=null ? request.getParameter("address") : request.getParameter("AddressDefault");
        String phonenumber = request.getParameter("phonenumber")!=null ? request.getParameter("phonenumber") : request.getParameter("PhoneDefault");

        PersonalInfoData personalInfo = new PersonalInfoData();
        UserData user= new UserData();
        int id = (int)request.getSession().getAttribute("id");
        personalInfo.updatePersonaInfofromId(id, FirstName, LastName, phonenumber);

        user.updateUser(id, Email, Address);

        if(request.getParameter("password")!=null)
        {
            response.sendRedirect("/TalkAID_war_exploded/JSP/Change_password.jsp");
        }
    }
}
