package controller;

import com.google.gson.Gson;
import model.entity.SlimmerExercise;
import model.service.exercise.ExerciseManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManageExercise")
public class ManageAIExercise extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String referer = request.getHeader("Referer");
        String action = request.getParameter("action");
        ExerciseManager em = new ExerciseManager();
        Gson g = new Gson();

        if(action.equalsIgnoreCase("Approve") || action.equalsIgnoreCase("Remove")){
            SlimmerExercise exercise = g.fromJson(request.getParameter("exercise"), SlimmerExercise.class);
            em.changeRaccomandation(action, exercise.getId(), exercise.getInsertionDate(), exercise.getUserId());

        }else if (action.equalsIgnoreCase("ApproveAll") || action.equalsIgnoreCase("RemoveAll")) {
            if(action.equalsIgnoreCase("ApproveAll")){
                action = "Approve";
            }else if (action.equalsIgnoreCase("RemoveAll")){
                action = "Remove";
            }
            em.changeMultipleReccomandation(action, Integer.parseInt(request.getParameter("userId")));
        }
        response.sendRedirect(referer);
    }
}