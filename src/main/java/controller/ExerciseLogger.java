package controller;

import model.service.exercise.ExerciseManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/exerciseLogger")
public class ExerciseLogger extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String execution = stringBuilder.toString();

        ExerciseManager em = new ExerciseManager();
        int userId = (int) request.getAttribute("userId");
        int exerciseId = (int) request.getAttribute("exerciseId");
        Date insertDate = (Date) request.getAttribute("insertDate");

        try {
            byte[] bytes = execution.getBytes(StandardCharsets.UTF_8);
            Blob executionBlob = new SerialBlob(bytes);
            em.saveExecution(userId, exerciseId, insertDate, executionBlob);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}