package model.entity;

import java.sql.Date;

public class SlimmerExercise {
    private int id;
    private String name;
    private Date insertionDate;

    private String description;

    private int feedback;

    private int difficulty;

    private String target;

    private String type;

    private int userId;

    public SlimmerExercise(int id, int userId, String name, String description, int feedback, Date insertionDate, int difficulty, String target, String type) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.feedback = feedback;
        this.insertionDate = insertionDate;
        this.difficulty = difficulty;
        this.target = target;
        this.type = type;
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

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
