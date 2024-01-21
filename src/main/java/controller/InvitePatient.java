package controller;

import model.service.registration.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet che occupa dell'invito di nuovi pazienti inviando una email contenente il codice d'invito
 */
@WebServlet("/invitePatient")
public class InvitePatient extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(InvitePatient.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        model.service.registration.Registration registration=new Registration();
        registration.invitePatient((Integer) session.getAttribute("id"), request.getParameter("email"), request.getParameter("nome"), request.getParameter("cognome"));
        try{
            response.sendRedirect(request.getContextPath() + "/JSP/homepageTherapist.jsp");
        }catch(IOException e){
            logger.error("Error redirecting", e);
        }

    }

}