package controller;

import model.service.condition.ConditionManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/AddRemovePatientCondition")
public class AddRemovePatientCondition extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String referer = request.getHeader("Referer");
        ConditionManager conditionService= new ConditionManager();

        int idPatient = Integer.parseInt(request.getParameter("idPatient"));
        int idCondition = Integer.parseInt(request.getParameter("idCondition"));

        String operation= request.getParameter("operation");
        if (operation.equals("Rimuovi"))  //REMOVE
        {
            conditionService.RemoveConditionPatient(idCondition,idPatient);
        }
        if (operation.equals("Aggiungi")) //ADD
        {
            int severity= Integer.parseInt(request.getParameter("severity"));
            conditionService.AddConditionPatient(idCondition,idPatient,severity);
        }

        response.sendRedirect(referer);

    }
}
