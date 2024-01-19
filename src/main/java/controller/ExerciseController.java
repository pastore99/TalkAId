package controller;

import model.entity.ExerciseGlossary;
import model.service.exercise.ExerciseManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet("/exerciseController")
public class ExerciseController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExerciseManager em = new ExerciseManager();
        int id = 0;
        Date insertionDate = null;
        try{
            id = Integer.parseInt(request.getParameter("exerciseID"));
            insertionDate = Date.valueOf(request.getParameter("insertionDate"));
        }catch(NumberFormatException e){
            e.printStackTrace();
        }

        request.getSession().setAttribute("insertionDate", insertionDate);
        request.getSession().setAttribute("exerciseID", id);

        ExerciseGlossary ex = em.getExercise(id);
        request.getSession().setAttribute("exercise", ex);
        try{
            response.sendRedirect(request.getContextPath() + "/JSP/exercise.jsp");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}