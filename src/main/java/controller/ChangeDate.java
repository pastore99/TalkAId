package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.PersonalInfo;
import model.entity.User;
import model.service.PersonalInfo.PersonalInfoData;
import model.service.user.UserData;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/changeDate")
public class ChangeDate extends  HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonalInfo utente = new PersonalInfoData().getPersonalInfo((int) request.getSession().getAttribute("id"));
        String Email = request.getParameter("email");
        String FirstName = null;
        String LastName = null;
        String Address = request.getParameter("address");
        String phonenumber = null;
        if(!Objects.equals(request.getParameter("firstname"), "")||!Objects.equals(request.getParameter("lastname"), "")||!Objects.equals(request.getParameter("phonenumber"), ""))
        {
            FirstName = !Objects.equals(request.getParameter("firstname"), "") ? request.getParameter("firstname") : utente.getFirstname();
            LastName = !Objects.equals(request.getParameter("lastname"), "") ? request.getParameter("lastname") : utente.getLastname();
            phonenumber = !Objects.equals(request.getParameter("phonenumber"), "") ? request.getParameter("phonenumber") : utente.getPhone();
        }
        HttpSession session= request.getSession();
        PersonalInfoData personalInfo = new PersonalInfoData();
        UserData user= new UserData();
        String risultato="";
        int id = (int)session.getAttribute("id");
        if(FirstName!=null || LastName!=null || phonenumber!=null)
            if(personalInfo.updatePersonaInfofromId(id, FirstName, LastName, phonenumber))
            {
                risultato="Dati personali aggiornati con successo;";
            }
        else
            {
                risultato="Dati personali non aggiornati rinserire";
            }

        if(!Objects.equals(Email, "") || !Objects.equals(Address, "")) {
            String risult = user.updateUser(id, Email, Address);
            if (Objects.equals(risult, "l'Email inserità e già usata scegliere un'altra Email") || Objects.equals(risultato, "Aggioranmento Address riuscito ma l'Email inserità e già usata scegliere un'altra Email")) {
                risultato=risultato + risult+ "rinserire anche la password se la si vuole modificare";
                response.sendRedirect("/TalkAID_war_exploded/JSP/Cambio_dati.jsp?risultato="+risultato);
                /*request.setAttribute("risultato", risultato );

                RequestDispatcher dispatcher = request.getRequestDispatcher("/TalkAID_war_exploded/JSP/Cambio_dati.jsp");
                dispatcher.forward(request, response);*/
            }
            else
            {
                risultato = risultato + risult;
            }
        }
        if(!Objects.equals(request.getParameter("password"), ""))
        {
            response.sendRedirect("/TalkAID_war_exploded/JSP/Change_Password.jsp");
        }
        else
        {
            response.sendRedirect("/TalkAID_war_exploded/JSP/Cambio_dati.jsp?risultato="+risultato);
            /*request.setAttribute("risultato", risultato );
            RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/Cambio_dati.jsp");
            dispatcher.forward(request, response);*/
        }
    }
}
