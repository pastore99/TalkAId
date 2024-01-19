package controller;

import model.service.exercise.ExerciseManager;
import model.service.message.MessageManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/exerciseRecommendation")
public class ExerciseRecommendation extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String referer = request.getHeader("Referer");
        ExerciseManager exerciseService= new ExerciseManager();

        int idExercise = 0;
        int idPatient = 0;
        try{
            idExercise = Integer.parseInt(request.getParameter("idExercise"));
            idPatient = Integer.parseInt(request.getParameter("idPatient"));
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        exerciseService.AddExerciseRecommendation(idExercise,idPatient);
        new MessageManager().sendMessage(0,idPatient,"Hai un nuovo esercizio da fare");
        try{
            response.sendRedirect(referer);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
