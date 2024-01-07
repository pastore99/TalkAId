package model.service.exercise;

import model.DAO.DAOExercise;
import model.DAO.DAOExerciseGlossary;
import model.entity.Exercise;
import model.entity.ExerciseGlossary;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

public class ExerciseManager implements ExerciseManagerInterface {
    private final DAOExerciseGlossary daoEG = new DAOExerciseGlossary();
    private final DAOExercise daoE = new DAOExercise();
    public ExerciseGlossary getExercise(int exerciseID) {
        return daoEG.getExerciseByCode(exerciseID);
    }

    public Blob getExecution(int exerciseID, int userID) {
        return null;
    }

    public boolean saveExecution(int userID, int exerciseId, Date insertDate, Blob execution) {

        return daoE.setExerciseExecution(userID, exerciseId, insertDate, execution);
    }

    public List<Exercise> retrieveAllNewPatientExercise(int userID){
        return daoE.retrieveAllNewPatientExercise(userID);
    }

    public List<Exercise> retrieveAllPatientExerciseDone(int userID){
        return daoE.retrieveAllPatientExerciseDone(userID);
    }

    public List<Exercise> retrieveAllNewPatientExerciseNotDone(int userID){
        return daoE.retrieveAllNewPatientExerciseNotDone(userID);
    }
}
