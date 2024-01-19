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
        int idPatient = 0;
        int idCondition = 0;
        try{
            idPatient = Integer.parseInt(request.getParameter("idPatient"));
            idCondition = Integer.parseInt(request.getParameter("idCondition"));
        }catch(NumberFormatException e){
            e.printStackTrace();
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
                e.printStackTrace();
            }

            conditionService.AddConditionPatient(idCondition,idPatient,severity);
        }
        try{
            response.sendRedirect(referer);
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
