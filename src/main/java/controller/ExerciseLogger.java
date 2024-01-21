package controller;

import model.service.exercise.ExerciseManager;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.rowset.serial.SerialBlob;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Servlet che occupa di salvare l'esecuzione degli esercizi nel database
 */
@WebServlet("/exerciseLogger")
@MultipartConfig(fileSizeThreshold=1024*1024*10, // 10 MB
        maxFileSize=1024*1024*50,      // 50 MB
        maxRequestSize=1024*1024*100)  // 100 MB
public class ExerciseLogger extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ExerciseLogger.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String contentType = request.getContentType();
        try{
            if ("application/json".equals(contentType)) {
                handleNoAudioExercise(request);
            } else {
                handleAudioExercise(request);
            }
        }catch(ServletException | IOException e){
            logger.error("Error handling exercise", e);
        }

        RequestDispatcher d = request.getRequestDispatcher("/exerciseEvaluator");
        try{
            d.forward(request, response);
        }catch(ServletException | IOException e){
            logger.error("Error forwarding", e);
        }
    }

    /**
     * Si occupa degli esercizi di scrittura
     * @param request contiene l'esecuzione dell'esercizio
     * @throws IOException nel caso in cui ci sono errori nella request
     */
    private void handleNoAudioExercise(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String execution = stringBuilder.toString();

        try {
            byte[] bytes = execution.getBytes(StandardCharsets.UTF_8);
            Blob executionBlob = new SerialBlob(bytes);
            saveInDB(request, executionBlob);

        } catch (SQLException e) {
            logger.error("Error saving in DB", e);
        }
    }

    /**
     * Si occupa degli esercizi di lettura
     * @param request contiene l'esecuzione dell'esercizio
     * @throws ServletException in caso di errore nella request
     * @throws IOException in caso di errore nell'InputStream
     */
    private void handleAudioExercise(HttpServletRequest request) throws ServletException, IOException {

        try {
            Part audioPart = request.getPart("audioFile");
            try (InputStream audioInputStream = audioPart.getInputStream()) {
                Blob audioBlob = new SerialBlob(IOUtils.toByteArray(audioInputStream));
                saveInDB(request, audioBlob);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Si occupa di salvare l'esecuzione nel database
     * @param request contiene la sessione, che a sua volta contiene i dati necessari per salvare l'esecuzione
     * @param execution l'esecuzione dell'esercizio da salvare
     * @return true se ha successo il salvataggio, false altrimenti
     */
    private boolean saveInDB(HttpServletRequest request, Blob execution){
        ExerciseManager em = new ExerciseManager();
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        int exerciseId = (int) session.getAttribute("exerciseID");
        Date insertDate = (Date) session.getAttribute("insertionDate");

        return em.saveExecution(userId, exerciseId, insertDate, execution);
    }
}