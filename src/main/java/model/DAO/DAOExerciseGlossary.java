package model.DAO;

import model.entity.ExerciseGlossary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The DAOExerciseGlossary class provides methods for retrieving Exercise information from a database.
 */
public class DAOExerciseGlossary {
    /**
     * This method extracts Exercise object data from a ResultSet
     *
     * @param resultSet ResultSet object from ExerciseGlossary table
     * @return exercise object containing data from resultSet
     * @throws SQLException if there is any SQL related error
     */
    private static ExerciseGlossary extractExerciseFromResultSet(ResultSet resultSet) throws SQLException {
        ExerciseGlossary exercise = new ExerciseGlossary();
        exercise.setIdExercise(resultSet.getInt("ID_exercise"));
        exercise.setExerciseName(resultSet.getString("ExerciseName"));
        exercise.setExerciseDescription(resultSet.getString("ExerciseDescription"));
        exercise.setType(resultSet.getString("Type"));
        exercise.setDifficulty(resultSet.getInt("Difficulty"));
        exercise.setTarget(resultSet.getString("Target"));
        exercise.setInitialState(resultSet.getString("InitialState"));
        exercise.setSolution(resultSet.getString("Solution"));

        return exercise;
    }

    /**
     * Search for an Exercise by its code in the database.
     *
     * @param code the ExerciseID code to search for.
     * @return the Exercise if it is found, else null.
     */
    public ExerciseGlossary getExerciseByCode(int code) {
        String query = "SELECT * FROM exercise_glossary WHERE ID_exercise = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, code);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractExerciseFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
