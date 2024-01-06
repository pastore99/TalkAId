package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseGlossaryTest {

    @Test
    public void testExerciseGlossary() {
        ExerciseGlossary exerciseGlossary = new ExerciseGlossary();

        int id = 1;
        String name = "Test Exercise";
        String description = "Test Description";
        String type = "Type 1";
        int difficulty = 3;
        String initialState = "Initial";
        String solution = "Solution";
        String target = "Target";

        exerciseGlossary.setIdExercise(id);
        exerciseGlossary.setExerciseName(name);
        exerciseGlossary.setExerciseDescription(description);
        exerciseGlossary.setType(type);
        exerciseGlossary.setDifficulty(difficulty);
        exerciseGlossary.setInitialState(initialState);
        exerciseGlossary.setSolution(solution);
        exerciseGlossary.setTarget(target);

        assertEquals(id, exerciseGlossary.getIdExercise());
        assertEquals(name, exerciseGlossary.getExerciseName());
        assertEquals(description, exerciseGlossary.getExerciseDescription());
        assertEquals(type, exerciseGlossary.getType());
        assertEquals(difficulty, exerciseGlossary.getDifficulty());
        assertEquals(initialState, exerciseGlossary.getInitialState());
        assertEquals(solution, exerciseGlossary.getSolution());
        assertEquals(target, exerciseGlossary.getTarget());
    }
}
