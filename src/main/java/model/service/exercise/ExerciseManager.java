package model.service.exercise;

import model.DAO.DAOExerciseGlossary;
import model.entity.ExerciseGlossary;

import java.sql.Blob;

public class ExerciseManager implements ExerciseManagerInterface {
    private DAOExerciseGlossary dao = new DAOExerciseGlossary();
    public ExerciseGlossary getExercise(int exerciseID) {
        return dao.getExerciseByCode(exerciseID);
    }

    public Blob getExecution(int exerciseID, int userID) {
        return null;
    }

    public void saveExecution(int userID) {

    }
}
