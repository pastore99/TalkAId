package model.service.exercise;

import model.entity.ExerciseGlossary;

import java.sql.Blob;
import java.sql.Date;

public interface ExerciseManagerInterface {

    /**
     * Preleva un esercizio dal Database
     * @param exerciseID l'id dell'esercizio
     * @return l'oggetto esercizio
     */
    ExerciseGlossary getExercise(int exerciseID);

    /**
     * Preleva un'esecuzione di un esercizio
     * @param exerciseID l'id dell'esercizio
     * @param userID l'id dell'utente che ha svolto l'esercizio
     * @return un BLOB contenente l'esecuzione dell'esercizio
     */
    Blob getExecution(int exerciseID, int userID);

    /**
     * Salva l'esecuzione di un esercizio
     *
     * @param userID l'id dell'utente che ha eseguito l'esercizio
     * @param exerciseId l'id dell'esercizio eseguito
     * @param insertDate la data in cui l'esercizio è stato assegnato all'utente
     * @param execution l'esecuzione dell'esercizio
     * @return True se riesce a salvarla, false altrimenti
     */
    boolean saveExecution(int userID, int exerciseId, Date insertDate, Blob execution);
}
