package model.entity;


import java.io.Serializable;

public class ExerciseGlossary implements Serializable {
    private int idExercise;
    private String exerciseName;
    private String exerciseDescription;
    private String type;
    private int difficulty;
    private String initialState;
    private String solution;
    private String target;

    // Getter and Setter methods

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getInitialState() { return initialState; }

    public void setInitialState(String initialState) { this.initialState = initialState; }

    public String getSolution() { return solution; }

    public void setSolution(String solution) { this.solution = solution; }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }


}

