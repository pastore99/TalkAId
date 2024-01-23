package model.entity;


import org.junit.jupiter.api.Test;
import java.sql.Blob;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {

    @Test
    public void testExercise(){
        Exercise exercise = new Exercise();

        int idUser = 1;
        int idExercise = 2;
        Date dateInsertion = new Date(2024, 1, 1);
        Date dateCompletion = new Date(2024, 1, 1);
        Blob blob = null; // Assuming blob as null for this unit test
        int evaluation = 10;
        int recommended = 1;
        int feedback = -1;

        exercise.setIdUser(idUser);
        exercise.setIdExercise(idExercise);
        exercise.setInsertionDate(dateInsertion);
        exercise.setCompletionDate(dateCompletion);
        exercise.setExecution(blob);
        exercise.setEvaluation(evaluation);
        exercise.setRecommended(recommended);
        exercise.setFeedback(feedback);

        assertEquals(idUser, exercise.getIdUser());
        assertEquals(idExercise, exercise.getIdExercise());
        assertEquals(dateInsertion, exercise.getInsertionDate());
        assertEquals(dateCompletion, exercise.getCompletionDate());
        assertEquals(blob, exercise.getExecution());
        assertEquals(evaluation, exercise.getEvaluation());
        assertEquals(recommended, exercise.getRecommended());
        assertEquals(feedback, exercise.getFeedback());
    }
}
