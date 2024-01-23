package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.debatty.java.stringsimilarity.Levenshtein;
import model.entity.ExerciseGlossary;
import model.service.exercise.ExerciseManager;
import model.service.exercise.SpeechRecognition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * Servlet che si occupa di valutare un esercizio e di salvarne il risultato sul database
 */
@WebServlet("/exerciseEvaluator")
@MultipartConfig
public class ExerciseEvaluator extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ExerciseEvaluator.class);
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
                logger.error("Error evaluating Audio", e);
                Thread.currentThread().interrupt();
            }
        }
        em.saveEvaluation(userId, exerciseId, d, score);
    }

    /**
     * Si occupa di valutare gli esercizi di scrittura, che non necessitano di riconoscimento vocale
     * @param exerciseId è l'id dell'esercizio da valutare
     * @param userId è l'id dell'utente che ha eseguito l'esercizio
     * @param date è la data in cui l'esercizio è stato assegnato all'utente
     * @return la valutazione dell'esercizio
     */
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

    /**
     * Si occupa di trasformare il BLOB presente nel database in una string JSON
     * @param exerciseId è l'id dell'esercizio da valutare
     * @param userId è l'id dell'utente che ha eseguito l'esercizio
     * @param d è la data in cui l'esercizio è stato assegnato all'utente
     * @return la stringa JSON costruita
     */
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
            logger.error("Error parsing Blob to JSON", e);
        }

        return stringBuilder.toString();
    }

    /**
     * Si occupa della valutazione degli esercizi di tipo "RIGHT TEXT"
     * @param execution l'esecuzione dell'esercizio da parte dell'utente
     * @param solution la soluzione dell'esercizio con cui poter valutare l'esecuzione
     * @return la valutazione dell'esercizio
     */
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

    /**
     * Si occupa della valutazione degli esercizi di tipo "IMAGES TO TEXT" e "TEXT TO IMAGES"
     * @param execution l'esecuzione dell'esercizio da parte dell'utente
     * @param solution la soluzione dell'esercizio con cui poter valutare l'esecuzione
     * @return la valutazione dell'esercizio
     */
    private int evaluateITTnTTI(Map<String, String> execution, Map<String, String> solution) {
        double right = 0;
        int total = solution.size();

        for (Map.Entry<String, String> entry : execution.entrySet()) {
            String k = entry.getKey();
            String executionValue = entry.getValue();
            String solutionValue = solution.get(k);

            if (executionValue != null && executionValue.equals(solutionValue.toUpperCase())) {
                right++;
            }
        }
        return (int)((right /total)*100);
    }

    /**
     * Si occupa della valutazione degli esercizi di tipo "CROSSWORD"
     * @param execution l'esecuzione dell'esercizio da parte dell'utente
     * @param solution la soluzione dell'esercizio con cui poter valutare l'esecuzione
     * @return la valutazione dell'esercizio
     */
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
        if(total != 0){
            return (int)((right /total)*100);
        }

        return 0;
    }

    /**
     * Si occupa di valutare gli esercizi di lettura, che necessitano di riconoscimento vocale
     * @param exerciseId l'id dell'esercizio eseguito
     * @param userId l'id dell'utente che ha eseguito l'esercizio
     * @param d la data in cui è stata assegnata l'esecuzione dell'esercizio all'utente
     * @return la valutazione dell'esercizio
     * @throws IOException in caso di errore dell'Input Stream
     * @throws ExecutionException in caso di errore nella conversione dell'audio in testo tramite Azure
     * @throws InterruptedException in caso di interruzione durante la conversione dell'audio in testo tramite Azure
     */
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

    /**
     * Si occupa di creare l'InputStream audio dal BLOB presente nel database
     * @param exerciseId l'id dell'esercizio
     * @param userId l'id dell'utente
     * @param d la data in cui è stato assegnato l'esercizio all'utente
     * @return l'audio in formato InputStream
     */
    private InputStream getAudiofromBlob(int exerciseId, int userId, Date d){
        Blob executionBlob = new ExerciseManager().getExecution(exerciseId, userId, d);
        try (InputStream audioInputStream = executionBlob.getBinaryStream()) {
            return audioInputStream;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}