package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.entity.ExerciseGlossary;
import model.service.exercise.ExerciseManager;
import model.service.exercise.ExerciseManagerInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.Type;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

@WebServlet("/exerciseEvaluator")
@MultipartConfig
public class ExerciseEvaluator extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession s = request.getSession();
        String contentType = request.getContentType();
        ExerciseManager em = new ExerciseManager();

        int exerciseId = Integer.parseInt((String) s.getAttribute("exerciseID"));
        //int userId = Integer.parseInt((String) s.getAttribute("id"));
        //Date d = Date.valueOf((String) s.getAttribute("insertDate");

        Date d = Date.valueOf("2024-01-07"); //TODO: prendilo dalla session
        int userId = 8; //TODO: prendilo dalla session


        if ("application/json".equals(contentType)) {
            int score = evaluateNoAudio(exerciseId, userId, d);
            em.saveEvaluation(userId, exerciseId, d, score);
        } //else {
           // evaluateAudio(request);
       // }
    }

    private int evaluateNoAudio(int exerciseId, int userId, Date date){
        Gson gson = new Gson();

        ExerciseGlossary baseExercise = new ExerciseManager().getExercise(exerciseId);
        String executionJSON = getJSONfromBlob(exerciseId, userId, date);
        String type = baseExercise.getType();

        int score = 0;

        switch (type) {
            case "CROSSWORD" -> {
                String[][] solution = gson.fromJson(baseExercise.getSolution(), String[][].class);
                String[][] execution = gson.fromJson(executionJSON, String[][].class);

                score = evaluateCrossword(execution, solution);
            }
            case "IMAGESTOTEXT", "TEXTTOIMAGES" -> {
                Type solutionType = new TypeToken<Map<String, String>>() {}.getType();
                Map<String, String> solution = gson.fromJson(baseExercise.getSolution(), solutionType);
                Map<String, String> execution = gson.fromJson(executionJSON, solutionType);

                score = evaluateITTnTTI(execution, solution);
            }
            case "RIGHTTEXT" -> {
                Type solutionType = new TypeToken<Set<String>>(){}.getType();
                Set<String> solution = gson.fromJson(baseExercise.getSolution(), solutionType);
                Set<String> execution = gson.fromJson(executionJSON, solutionType);

                score = evaluateRightText(execution, solution);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

        return score;
    }

    private String getJSONfromBlob(int exerciseId, int userId, Date d){
        Blob executionBlob = new ExerciseManager().getExecution(exerciseId, userId, d);
        StringBuilder stringBuilder = new StringBuilder();

        try (InputStream binaryStream = executionBlob.getBinaryStream();
             InputStreamReader inputStreamReader = new InputStreamReader(binaryStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private int evaluateRightText(Set<String> execution, Set<String> solution) {
        double right = 0;
        int total = solution.size();
        for (String s : solution) {
            if (execution.contains(s.toUpperCase())){
                right++;
            }
        }
        return (int)((right /total)*100);
    }

    private int evaluateITTnTTI(Map<String, String> execution, Map<String, String> solution) {
        double right = 0;
        int total = solution.size();

        for (Map.Entry<String, String> entry : execution.entrySet()) {
            String k = entry.getKey();
            String executionValue = entry.getValue();
            String solutionValue = solution.get(k);

            if (executionValue != null) {
                if (executionValue.equals(solutionValue.toUpperCase())) {
                    right++;
                }
            }
        }
        return (int)((right /total)*100);
    }

    private int evaluateCrossword(String[][] execution, String[][] solution) {
        double right = 0;
        int total = 0;

        for (int i = 0; i < execution.length; i++) {
            for (int j = 0; j < execution[0].length; j++) {
                if (!execution[i][j].equals("#")){
                    if (execution[i][j].equals(solution[i][j].toUpperCase())) {
                        right++;
                    }
                    total++;
                }
            }
        }
        return (int)((right /total)*100);
    }

    //    private void evaluateAudio(HttpServletRequest r){
    //        //TODO: chiamata all'adapter, per python che valuta l'audio
    //    }

}