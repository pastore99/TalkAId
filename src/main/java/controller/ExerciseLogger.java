package controller;

import model.service.exercise.ExerciseManager;
import org.apache.commons.io.IOUtils;

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

@WebServlet("/exerciseLogger")
@MultipartConfig(fileSizeThreshold=1024*1024*10, // 10 MB
        maxFileSize=1024*1024*50,      // 50 MB
        maxRequestSize=1024*1024*100)  // 100 MB
public class ExerciseLogger extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            handleNoAudioExercise(request);
        } else {
            handleAudioExercise(request);
        }
        RequestDispatcher d = request.getRequestDispatcher("/exerciseEvaluator");
        d.forward(request, response);
    }

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
            e.printStackTrace();
        }
    }

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

    private boolean saveInDB(HttpServletRequest request, Blob execution){
        ExerciseManager em = new ExerciseManager();
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        int exerciseId = (int) session.getAttribute("exerciseID");
        Date insertDate = (Date) session.getAttribute("insertionDate");

        return em.saveExecution(userId, exerciseId, insertDate, execution);
    }
}