package model.service.exercise;

import model.DAO.DAOExercise;
import model.DAO.DAOExerciseGlossary;
import model.entity.Exercise;
import model.entity.ExerciseGlossary;
import model.entity.SlimmerExercise;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Questa classe provvede alle funzionalità per la gestione degli esercizi.
 */
public class ExerciseManager implements ExerciseManagerInterface {
    private final DAOExerciseGlossary daoEG = new DAOExerciseGlossary();
    private final DAOExercise daoE = new DAOExercise();

    public ExerciseGlossary getExercise(int exerciseID) {
        return daoEG.getExerciseByCode(exerciseID);
    }

    public Blob getExecution(int exerciseID, int userID, Date insertionDate) {
        return daoE.getExerciseExecution(userID, exerciseID, insertionDate);
    }

    public boolean saveExecution(int userID, int exerciseId, Date insertDate, Blob execution) {

        return daoE.setExerciseExecution(userID, exerciseId, insertDate, execution);
    }

    public boolean saveEvaluation(int userID, int exerciseId, Date insertDate, int evaluation) {

        return daoE.setExerciseEvaluation(userID, exerciseId, insertDate, evaluation);
    }

    public List<Exercise> retrieveAllPatientExerciseDone(int userID){
        return daoE.retrieveAllPatientExerciseDone(userID);
    }

    public List<Exercise> retrievePatientExerciseDone(int patientID) {
        return daoE.retrievePatientExerciseDone(patientID);
    }

    public List<SlimmerExercise> retrieveDoneExercises(int patientId) {
        return daoE.retrieveDoneExercises(patientId);
    }

    public List<SlimmerExercise> retrieveNotDoneExercises(int patientId) {
        return daoE.retrieveNotDoneExercises(patientId);
    }

    public List<SlimmerExercise> retrieveAiRaccomandation(int therapistId){
        return daoE.getExerciseToApprove(therapistId);
    }

    public boolean changeRaccomandation(String action, int exerciseId, Date insertDate, int userId){
        if(action.equalsIgnoreCase("Approve")){
            return daoE.approveExercise(exerciseId, insertDate, userId);
        }else{
            return daoE.deleteExercise(exerciseId, insertDate, userId);
        }
    }

    public boolean changeMultipleReccomandation(String action, int userId){
        if(action.equalsIgnoreCase("Approve")){
            return daoE.approveMultipleExercise(userId);
        }else{
            return daoE.deleteMultipleExercise(userId);
        }
    }

    public Map<String,Integer> retrieveAllStats(int id)
    {
        return daoE.retrieveAllStatsPatientExerciseDone(id);
    }

    public List<ExerciseGlossary> retrieveAllPatientExerciseGlossaryNotDone(int userID) { return daoEG.retrieveAllPatientExerciseGlossaryNotDone(userID);}

    public List<ExerciseGlossary> retrieveAllPatientExerciseGlossaryDone(int userID) { return daoEG.retrieveAllPatientExerciseGlossaryDone(userID);}

    public boolean AddExerciseRecommendation(int idExercise, int idPatient) { return daoE.AddExerciseRecommendation(idExercise,idPatient);}
}
