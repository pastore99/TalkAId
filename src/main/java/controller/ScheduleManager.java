package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ScheduleServlet")
public class ScheduleManager extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = (Integer) request.getSession().getAttribute("id");
        try{
            response.getWriter().append("Served at: ").append(request.getContextPath());
        }catch(IOException e){
            e.printStackTrace();
        }

        String action = request.getParameter("action");
        model.service.schedule.ScheduleManager scheduleManager = new model.service.schedule.ScheduleManager();

        try {

            //azioni fatte dal logopedista

            if (action.equalsIgnoreCase("createNewSchedule")) {
                if (scheduleManager.checkData(userId, request.getParameter("date"), request.getParameter("timeslot"))) {
                    scheduleManager.createNewSchedule(userId, request.getParameter("date"), request.getParameter("timeslot"));
                    response.sendRedirect("JSP/schedule.jsp");
                } else {
                    String errorMessage = "Seleziona una data non esistente perfavore.";
                    response.sendRedirect("JSP/schedule.jsp?errorMessage=" + errorMessage);
                }
            }
            else if (action.equalsIgnoreCase("deleteSchedule")) {
                scheduleManager.deleteSchedule(userId,(request.getParameter("date")),(request.getParameter("timeslot")));
                response.sendRedirect("JSP/schedule.jsp");
            }

            //azioni fatte dal paziente

            else if (action.equalsIgnoreCase("prenoteSchedule")) {
                int idTherapist = Integer.parseInt(request.getParameter("idTherapist"));
                scheduleManager.modifySchedule(idTherapist,(request.getParameter("date")),(request.getParameter("timeslot")),(request.getParameter("date")),(request.getParameter("timeslot")),userId);
                response.sendRedirect("JSP/schedule.jsp");
            }else if (action.equalsIgnoreCase("unprenoteSchedule")) {
                int idTherapist = Integer.parseInt(request.getParameter("idTherapist"));
                scheduleManager.modifySchedule(idTherapist,(request.getParameter("date")),(request.getParameter("timeslot")),(request.getParameter("date")),(request.getParameter("timeslot")),0);
                response.sendRedirect("JSP/schedule.jsp");
            }


            else {
                // Gestisci il caso in cui "action" non sia presente nella richiesta
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
            }
    } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}