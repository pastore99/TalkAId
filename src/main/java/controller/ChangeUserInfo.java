package controller;

import model.service.user.UserRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet che si occupa del cambio dei dati personali
 */
@WebServlet("/changeDate")
public class ChangeUserInfo extends  HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ChangeUserInfo.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        UserRegistry userRegistry = new UserRegistry();
        String risultato;
        if (updatePersonalInfo(request, userId, userRegistry)) {
            risultato = "Dati personali aggiornati con successo;";
        } else {
            risultato = "Dati personali non aggiornati, email gia' utilizzata";
        }
        try {
            response.sendRedirect("JSP/userArea.jsp?errorMessage=" + risultato);
        }catch(IOException e){
            logger.error("Error redirecting", e);
        }
    }

    /**
     * Questo metodo si occupa dell'effettivo cambio
     * @param request è la richiesta generata dal form
     * @param userId è l'id dell'utente le cui informazioni vanno modificate
     * @param userRegistry si occuperà dell'effettivo cambiamento dei dati presenti sul database
     * @return true se vengono cambiati dei dati, false altrimenti
     */
    private boolean updatePersonalInfo(HttpServletRequest request, int userId, UserRegistry userRegistry) {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String phoneNumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        if (!firstName.isEmpty() || !lastName.isEmpty() || !phoneNumber.isEmpty()) {
            return userRegistry.updatePersonaInfofromId(userId, firstName, lastName, phoneNumber,email,address);
        }
        return false;
    }
}