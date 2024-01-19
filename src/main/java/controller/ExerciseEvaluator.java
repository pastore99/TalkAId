package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.debatty.java.stringsimilarity.Levenshtein;
import model.entity.ExerciseGlossary;
import model.service.exercise.ExerciseManager;
import model.service.exercise.SpeechRecognition;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;


@WebServlet("/exerciseEvaluator")
@MultipartConfig
public class ExerciseEvaluator extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession s = request.getSession();
        String contentType = request.getContentType();
        ExerciseManager em = new ExerciseManager();

        int exerciseId = (int) s.getAttribute("exerciseID");
        int userId = (int) s.getAttribute("id");
        Date d = (Date) s.getAttribute("insertionDate");

        int score = 0;

        if ("application/json".equals(contentType)) {
            score = evaluateNoAudio(exerciseId, userId, d);
        } else {
            try {
                score = evaluateAudio(exerciseId, userId, d);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        em.saveEvaluation(userId, exerciseId, d, score);
    }


    private int evaluateNoAudio(int exerciseId, int userId, Date date){
        Gson gson = new Gson();

        ExerciseGlossary baseExercise = new ExerciseManager().getExercise(exerciseId);
        String executionJSON = getJSONfromBlob(exerciseId, userId, date);
        String type = baseExercise.getType();

        int score;

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

    private int evaluateAudio(int exerciseId, int userId, Date d) throws IOException, ExecutionException, InterruptedException {
        InputStream audioExecution = getAudiofromBlob(exerciseId, userId, d);
        String audioText = null;
        if (audioExecution!=null){
            SpeechRecognition s = new SpeechRecognition();
            audioText = s.azureSTT(audioExecution);
            audioExecution.close();
        }

        if(audioText != null) {
            ExerciseGlossary baseExercise = new ExerciseManager().getExercise(exerciseId);
            String exerciseType = baseExercise.getType();
            Gson g = new Gson();

            if (baseExercise.getType().equals("READTEXT")){
                Levenshtein l = new Levenshtein();
                String solution = g.fromJson(baseExercise.getInitialState(), String.class);
                double distance = l.distance(solution, audioText);
                double result = ((solution.length()-distance)/solution.length())*100;

                return (int) Math.round(result);

            }else if(exerciseType.equals("READIMAGES")){
                Type solutionType = new TypeToken<Set<String>>(){}.getType();
                Set<String> solution = g.fromJson(baseExercise.getSolution(), solutionType);
                String execution = audioText.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "");

                double right = 0;
                double total = solution.size();

                String[] wordsArray = execution.split("\\s+");
                Set<String> wordSet = new HashSet<>(Arrays.asList(wordsArray));

                for(String w: wordSet){
                    if(solution.contains(w)){
                        right++;
                    }
                }
                double result = ((right/total)*100);

                return (int) Math.round(result);
            }
        }

        return 0;
    }

    private InputStream getAudiofromBlob(int exerciseId, int userId, Date d){
        Blob executionBlob = new ExerciseManager().getExecution(exerciseId, userId, d);
        try (InputStream audioInputStream = executionBlob.getBinaryStream()) {
            return audioInputStream;
        } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
        }
    }
}