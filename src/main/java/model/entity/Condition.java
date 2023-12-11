package model.entity;

public class Condition {
    private int idCondition;
    private String disorderDescription;
    private String disorderName;

    // Getter and Setter methods

    public int getIdCondition() {
        return idCondition;
    }

    public void setIdCondition(int idCondition) {
        this.idCondition = idCondition;
    }

    public String getDisorderDescription() {
        return disorderDescription;
    }

    public void setDisorderDescription(String disorderDescription) {
        this.disorderDescription = disorderDescription;
    }

    public String getDisorderName() {
        return disorderName;
    }

    public void setDisorderName(String disorderName) {
        this.disorderName = disorderName;
    }
}
