package model.entity;

import java.sql.Blob;
import java.sql.Date;

public class Exercise {
    private int idUser;
    private int idExercise;
    private Date insertionDate;
    private Date completionDate;
    private Blob execution;
    private int evaluation;
    private int recommended;
    private int feedback;

    // Getter and Setter methods

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Blob getExecution() {
        return execution;
    }

    public void setExecution(Blob execution) {
        this.execution = execution;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }
    @Override
    public String toString() {
        return "Exercise{" +
                "idUser=" + idUser +
                ", idExercise=" + idExercise +
                ", insertionDate=" + insertionDate +
                ", completionDate=" + completionDate +
                ", evaluation=" + evaluation +
                ", recommended=" + recommended +
                ", feedback=" + feedback +
                '}';
    }
}

