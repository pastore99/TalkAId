package model.entity;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExerciseTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the Exercise class
        Exercise exercise = new Exercise();

        // Set values using setter methods
        int idUser = 1;
        int idExercise = 2;
        Date insertionDate = Date.valueOf("2022-01-01");
        Date completionDate = Date.valueOf("2022-01-02");
        byte[] execution = "Test Execution".getBytes();
        int evaluation = 5;
        int recommended = 1;
        int feedback = 3;

        exercise.setIdUser(idUser);
        exercise.setIdExercise(idExercise);
        exercise.setInsertionDate(insertionDate);
        exercise.setCompletionDate(completionDate);
        exercise.setExecution(execution);
        exercise.setEvaluation(evaluation);
        exercise.setRecommended(recommended);
        exercise.setFeedback(feedback);

        // Test getter methods
        assertEquals(idUser, exercise.getIdUser(), "getIdUser() should return the correct value");
        assertEquals(idExercise, exercise.getIdExercise(), "getIdExercise() should return the correct value");
        assertEquals(insertionDate, exercise.getInsertionDate(), "getInsertionDate() should return the correct value");
        assertEquals(completionDate, exercise.getCompletionDate(), "getCompletionDate() should return the correct value");
        assertEquals(execution, exercise.getExecution(), "getExecution() should return the correct value");
        assertEquals(evaluation, exercise.getEvaluation(), "getEvaluation() should return the correct value");
        assertEquals(recommended, exercise.getRecommended(), "getRecommended() should return the correct value");
        assertEquals(feedback, exercise.getFeedback(), "getFeedback() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the Exercise class
        Exercise exercise = new Exercise();

        // Test that the object is not null
        assertNotNull(exercise, "Exercise object should not be null");
    }
}

