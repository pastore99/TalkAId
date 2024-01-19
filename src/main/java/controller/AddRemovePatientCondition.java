package controller;

import model.service.condition.ConditionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/AddRemovePatientCondition")
public class AddRemovePatientCondition extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddRemovePatientCondition.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String referer = request.getHeader("Referer");
        ConditionManager conditionService= new ConditionManager();
        int idPatient = 0;
        int idCondition = 0;
        try{
            idPatient = Integer.parseInt(request.getParameter("idPatient"));
            idCondition = Integer.parseInt(request.getParameter("idCondition"));
        }catch(NumberFormatException e){
            logger.error("Error parsing idPatient and idCondition", e);
        }


        String operation= request.getParameter("operation");
        if (operation.equals("Rimuovi"))  //REMOVE
        {
            conditionService.RemoveConditionPatient(idCondition,idPatient);
        }
        if (operation.equals("Aggiungi")) //ADD
        {
            int severity = 0;
            try{
                severity= Integer.parseInt(request.getParameter("severity"));
            }catch(NumberFormatException e){
                logger.error("Error parsing severity", e);
            }

            conditionService.AddConditionPatient(idCondition,idPatient,severity);
        }
        try{
            response.sendRedirect(referer);
        } catch(IOException e){
            logger.error("Error redirecting", e);
        }

    }
}
