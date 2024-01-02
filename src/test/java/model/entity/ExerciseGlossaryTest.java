package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExerciseGlossaryTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the ExerciseGlossary class
        ExerciseGlossary exerciseGlossary = new ExerciseGlossary();

        // Set values using setter methods
        int idExercise = 1;
        String exerciseName = "Test Exercise Name";
        String exerciseDescription = "Test Exercise Description";
        String type = "Test Type";
        int difficulty = 3;
        byte[] initialState = "Test Initial State".getBytes();
        byte[] solution = "Test Solution".getBytes();
        String target = "Test Target";

        exerciseGlossary.setIdExercise(idExercise);
        exerciseGlossary.setExerciseName(exerciseName);
        exerciseGlossary.setExerciseDescription(exerciseDescription);
        exerciseGlossary.setType(type);
        exerciseGlossary.setDifficulty(difficulty);
        exerciseGlossary.setInitialState(initialState);
        exerciseGlossary.setSolution(solution);
        exerciseGlossary.setTarget(target);

        // Test getter methods
        assertEquals(idExercise, exerciseGlossary.getIdExercise(), "getIdExercise() should return the correct value");
        assertEquals(exerciseName, exerciseGlossary.getExerciseName(), "getExerciseName() should return the correct value");
        assertEquals(exerciseDescription, exerciseGlossary.getExerciseDescription(), "getExerciseDescription() should return the correct value");
        assertEquals(type, exerciseGlossary.getType(), "getType() should return the correct value");
        assertEquals(difficulty, exerciseGlossary.getDifficulty(), "getDifficulty() should return the correct value");
        assertEquals(initialState, exerciseGlossary.getInitialState(), "getInitialState() should return the correct value");
        assertEquals(solution, exerciseGlossary.getSolution(), "getSolution() should return the correct value");
        assertEquals(target, exerciseGlossary.getTarget(), "getTarget() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the ExerciseGlossary class
        ExerciseGlossary exerciseGlossary = new ExerciseGlossary();

        // Test that the object is not null
        assertNotNull(exerciseGlossary, "ExerciseGlossary object should not be null");
    }
}

