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
        String id = request.getParameter("exerciseID");
        String insertionDate = request.getParameter("insertionDate");
        request.getSession().setAttribute("insertionDate", Date.valueOf(insertionDate));
        request.getSession().setAttribute("exerciseID", Integer.parseInt(id));

        ExerciseGlossary ex = em.getExercise(Integer.parseInt(id));
        request.getSession().setAttribute("exercise", ex);
        response.sendRedirect(request.getContextPath() + "/JSP/exercise.jsp");
    }
}