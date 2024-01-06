package controller;

import model.service.condition.Condition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;

@WebServlet("/AddRemovePatientCondition")
public class AddRemovePatientCondition extends HttpServlet {



    public AddRemovePatientCondition() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        model.service.condition.Condition conditionService= new Condition();

        int idPatient= Integer.parseInt(request.getParameter("idPatient"));
        int idCondition= Integer.parseInt(request.getParameter("idCondition"));

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

        response.sendRedirect("view_patientServlet");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }


}
