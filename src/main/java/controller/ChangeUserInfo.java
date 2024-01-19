package controller;

import model.service.user.UserRegistry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changeDate")
public class ChangeUserInfo extends  HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        UserRegistry userRegistry = new UserRegistry();
        String risultato = "";
        if (updatePersonalInfo(request, userId, userRegistry)) {
            risultato = "Dati personali aggiornati con successo;";
        } else {
            risultato = "Dati personali non aggiornati, email gia' utilizzata";
        }
        response.sendRedirect("JSP/userArea.jsp?errorMessage=" + risultato);
    }


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