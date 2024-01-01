package controller;

import model.entity.Schedule;
import model.service.schedule.ScheduleManager;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = 44;//(Integer) request.getSession().getAttribute("id");
        response.getWriter().append("Served at: ").append(request.getContextPath());
        String action = request.getParameter("action");
        ScheduleManager scheduleManager = new ScheduleManager();

        try {

            //azioni fatte dal logopedista

            RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/schedule.jsp");
            if (action.equalsIgnoreCase("createNewSchedule")) {
                System.out.println("entrato createNewSchedule");
                int idTherapist = Integer.parseInt(request.getParameter("idTherapist"));
                scheduleManager.createNewSchedule(idTherapist,(request.getParameter("date")),(request.getParameter("timeslot")));
                System.out.println(idTherapist +" == "+ (request.getParameter("date")) +" == "+ (request.getParameter("timeslot")));
                //scheduleManager.createNewSchedule(userId,(request.getParameter("date")),(request.getParameter("timeslot")));
                dispatcher = request.getRequestDispatcher("JSP/schedule.jsp");
            }
            else if (action.equalsIgnoreCase("deleteSchedule")) {
                System.out.println("entrato deleteSchedule");
                int idTherapist = Integer.parseInt(request.getParameter("idTherapist"));
                scheduleManager.deleteSchedule(idTherapist,(request.getParameter("date")),(request.getParameter("timeslot")));
                //scheduleManager.deleteSchedule(userId,(request.getParameter("date")),(request.getParameter("timeslot")));
                dispatcher = request.getRequestDispatcher("JSP/schedule.jsp");
            }
            else if (action.equalsIgnoreCase("modifySchedule")) {
                int idReserved = Integer.parseInt(request.getParameter("idReserved"));
                scheduleManager.modifySchedule(userId,(request.getParameter("date")),(request.getParameter("timeslot")),(request.getParameter("ndate")),(request.getParameter("ntimeslot")),idReserved);
                        dispatcher = request.getRequestDispatcher("JSP/schedule.jsp");
            }

            //azioni fatte dal paziente

            else if (action.equalsIgnoreCase("prenoteSchedule")) {
                int idTherapist = Integer.parseInt(request.getParameter("idTherapist"));
                scheduleManager.modifySchedule(idTherapist,(request.getParameter("date")),(request.getParameter("timeslot")),(request.getParameter("date")),(request.getParameter("timeslot")),userId);
                        dispatcher = request.getRequestDispatcher("JSP/schedule.jsp");
            }else if (action.equalsIgnoreCase("unprenoteSchedule")) {
                int idTherapist = Integer.parseInt(request.getParameter("idTherapist"));
                scheduleManager.modifySchedule(idTherapist,(request.getParameter("date")),(request.getParameter("timeslot")),(request.getParameter("date")),(request.getParameter("timeslot")),0);
                dispatcher = request.getRequestDispatcher("JSP/schedule.jsp");
            }


            else {
                // Gestisci il caso in cui "action" non sia presente nella richiesta
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
            }

            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}