package controller;

import model.service.registration.Registration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/invitePatient")
public class InvitePatient extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        model.service.registration.Registration registration=new Registration();
        registration.invitePatient((Integer) session.getAttribute("id"), request.getParameter("email"), request.getParameter("nome"), request.getParameter("cognome"));
        response.sendRedirect(request.getContextPath() + "/JSP/homepageTherapist.jsp");
    }

}