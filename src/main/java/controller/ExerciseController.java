package controller;

import model.entity.ExerciseGlossary;
import model.service.exercise.ExerciseManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/exerciseController")
public class ExerciseController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExerciseManager em = new ExerciseManager();
        String id = request.getParameter("exerciseID");
        //TODO: Aggiungi alla sessione insertion date @michele
        ExerciseGlossary ex = em.getExercise(Integer.parseInt(id));
        request.getSession().setAttribute("exercise", ex);
        response.sendRedirect(request.getContextPath() + "/JSP/exercise.jsp");
    }
}