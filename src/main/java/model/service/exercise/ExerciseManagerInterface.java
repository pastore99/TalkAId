package model.service.exercise;

import model.entity.Exercise;
import model.entity.ExerciseGlossary;
import model.entity.SlimmerExercise;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

public interface ExerciseManagerInterface {

    /**
     * Recupera i dettagli dell'esecuzione per un esercizio specifico, un utente e una data di inserimento.
     *
     * @param exerciseID   L'ID dell'esercizio.
     * @param userID       L'ID dell'utente.
     * @param insertionDate La data di inserimento.
     * @return Un Blob che rappresenta l'esecuzione dell'esercizio, o null se non trovato.
     */
    public Blob getExecution(int exerciseID, int userID, Date insertionDate);

    /**
     * Salva i dettagli dell'esecuzione per un utente specifico, un esercizio e una data di inserimento.
     *
     * @param userID      L'ID dell'utente.
     * @param exerciseId  L'ID dell'esercizio.
     * @param insertDate  La data di inserimento.
     * @param execution   Il Blob che rappresenta l'esecuzione dell'esercizio.
     * @return true se l'esecuzione viene salvata con successo, false altrimenti.
     */
    public boolean saveExecution(int userID, int exerciseId, Date insertDate, Blob execution);

    /**
     * Salva i dettagli della valutazione per un utente specifico, un esercizio e una data di inserimento.
     *
     * @param userID      L'ID dell'utente.
     * @param exerciseId  L'ID dell'esercizio.
     * @param insertDate  La data di inserimento.
     * @param evaluation  Il punteggio della valutazione.
     * @return true se la valutazione viene salvata con successo, false altrimenti.
     */
    public boolean saveEvaluation(int userID, int exerciseId, Date insertDate, int evaluation);

    /**
     * Recupera una lista di tutti gli esercizi fatti da un paziente specifico.
     *
     * @param userID L'ID del paziente.
     * @return Una lista di oggetti Exercise che rappresentano gli esercizi fatti dal paziente.
     */
    public List<Exercise> retrieveAllPatientExerciseDone(int userID);

    /**
     * Recupera una lista di esercizi che un paziente ha già fatto.
     *
     * @param patientId L'ID del paziente.
     * @return Una lista di oggetti SlimmerExercise che rappresentano gli esercizi fatti dal paziente.
     */
    public List<SlimmerExercise> retrieveDoneExercises(int patientId);

    /**
     * Recupera una lista di esercizi che un paziente non ha ancora fatto.
     *
     * @param patientId L'ID del paziente.
     * @return Una lista di oggetti SlimmerExercise che rappresentano gli esercizi non fatti dal paziente.
     */
    public List<SlimmerExercise> retrieveNotDoneExercises(int patientId);
}
