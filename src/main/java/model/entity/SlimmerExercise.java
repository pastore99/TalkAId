package model.entity;

import java.sql.Date;

public class SlimmerExercise {
    private int id;
    private String name;
    private Date insertionDate;

    private int evaluation;

    public SlimmerExercise(int id, String name, Date insertionDate, int evaluation) {
        this.id = id;
        this.name = name;
        this.insertionDate = insertionDate;
        this.evaluation = evaluation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int vote) {
        this.evaluation = evaluation;
    }
}