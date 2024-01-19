package model.service.exercise;

import model.entity.Exercise;
import model.entity.ExerciseGlossary;
import model.entity.SlimmerExercise;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ExerciseManagerInterface {

    /**
     * Recupera i dettagli dell'esecuzione per un esercizio specifico, un utente e una data di inserimento.
     *
     * @param exerciseID   L'ID dell'esercizio.
     * @param userID       L'ID dell'utente.
     * @param insertionDate La data di inserimento.
     * @return Un Blob che rappresenta l'esecuzione dell'esercizio, o null se non trovato.
     */
    Blob getExecution(int exerciseID, int userID, Date insertionDate);

    /**
     * Salva i dettagli dell'esecuzione per un utente specifico, un esercizio e una data di inserimento.
     *
     * @param userID      L'ID dell'utente.
     * @param exerciseId  L'ID dell'esercizio.
     * @param insertDate  La data di inserimento.
     * @param execution   Il Blob che rappresenta l'esecuzione dell'esercizio.
     * @return true se l'esecuzione viene salvata con successo, false altrimenti.
     */
    boolean saveExecution(int userID, int exerciseId, Date insertDate, Blob execution);

    /**
     * Salva i dettagli della valutazione per un utente specifico, un esercizio e una data di inserimento.
     *
     * @param userID      L'ID dell'utente.
     * @param exerciseId  L'ID dell'esercizio.
     * @param insertDate  La data di inserimento.
     * @param evaluation  Il punteggio della valutazione.
     * @return true se la valutazione viene salvata con successo, false altrimenti.
     */
    boolean saveEvaluation(int userID, int exerciseId, Date insertDate, int evaluation);

    /**
     * Recupera una lista di tutti gli esercizi fatti da un paziente specifico.
     *
     * @param userID L'ID del paziente.
     * @return Una lista di oggetti Exercise che rappresentano gli esercizi fatti dal paziente.
     */
    List<Exercise> retrieveAllPatientExerciseDone(int userID);

    /**
     * Recupera una lista di esercizi che un paziente ha già fatto.
     *
     * @param patientId L'ID del paziente.
     * @return Una lista di oggetti SlimmerExercise che rappresentano gli esercizi fatti dal paziente.
     */
    List<SlimmerExercise> retrieveDoneExercises(int patientId);

    /**
     * Recupera una lista di esercizi che un paziente ha già fatto.
     *
     * @param patientID L'ID del paziente.
     * @return Una lista di oggetti Exercise che rappresentano gli esercizi fatti dal paziente.
     */
    List<Exercise> retrievePatientExerciseDone(int patientID);
    /**
     * Recupera una lista di esercizi che un paziente non ha ancora fatto.
     *
     * @param patientId L'ID del paziente.
     * @return Una lista di oggetti SlimmerExercise che rappresentano gli esercizi non fatti dal paziente.
     */
    List<SlimmerExercise> retrieveNotDoneExercises(int patientId);

    /**
     * Recupera i dati riguardanti gli esercizi proposti dal paziente all'utente.
     *
     * @param id L'ID del paziente.
     * @return Una Map con chiave il tipo di esercizio e valore la percentaule di esercizi fatti rispetto a quelli non fatti.
     */
    Map<String,Integer> retrieveAllStats(int id);

    /**
     * Recupera una lista delle info degli esercizi che un paziente non ha ancora fatto.
     *
     * @param userID L'ID del paziente.
     * @return Una lista di oggetti ExerciseGlossary che rappresentano gli esercizi non fatti dal paziente.
     */
    List<ExerciseGlossary> retrieveAllPatientExerciseGlossaryNotDone(int userID);

    /**
     * Recupera una lista delle info degli esercizi che un paziente ha fatto.
     *
     * @param userID L'ID del paziente.
     * @return Una lista di oggetti ExerciseGlossary che rappresentano gli esercizi non fatti dal paziente.
     */
    List<ExerciseGlossary> retrieveAllPatientExerciseGlossaryDone(int userID);

    /**
     * Raccomanda un esercizio a un paziente
     *
     * @param idPatient      L'ID dell'utente.
     * @param idExercise  L'ID dell'esercizio.
     * @return true se la raccomandazione viene salvata con successo, false altrimenti.
     */
    boolean AddExerciseRecommendation(int idExercise, int idPatient);
}
