package model.entity;

public class Help {
    private int idHelp;
    private int idExercise;
    private String helpText;

    // Getter and Setter methods

    public int getIdHelp() {
        return idHelp;
    }

    public void setIdHelp(int idHelp) {
        this.idHelp = idHelp;
    }

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }
}
