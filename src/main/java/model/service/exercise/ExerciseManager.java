package model.service.exercise;

import model.DAO.DAOExercise;
import model.DAO.DAOExerciseGlossary;
import model.entity.ExerciseGlossary;

import java.sql.Blob;
import java.sql.Date;

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
}
