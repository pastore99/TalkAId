package model.entity;

public class PatientCondition {
    private int idCondition;
    private int idPatient;
    private int severity;

    // Getter and Setter methods

    public int getIdCondition() {
        return idCondition;
    }

    public void setIdCondition(int idCondition) {
        this.idCondition = idCondition;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
}

