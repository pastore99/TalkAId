package model.DAO;

import model.entity.ExerciseGlossary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * The DAOExerciseGlossary class provides methods for retrieving ExerciseGlossary information from a database.
 */
public class DAOExerciseGlossary {

    private Connection connection;

    public DAOExerciseGlossary(Connection connection) {
        this.connection = connection;
    }

    public DAOExerciseGlossary() {
        try {
            this.connection = DAOConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
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

    public List<ExerciseGlossary> retrieveAllPatientExerciseGlossaryNotDone(int userID) {
        String query = "SELECT eg.* FROM exercise_glossary eg LEFT JOIN exercise e ON eg.ID_exercise = e.ID_exercise AND e.ID_user = ? WHERE e.ID_user IS NULL;\n";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ExerciseGlossary> exercises = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ExerciseGlossary exercise = extractExerciseFromResultSet(resultSet);
                exercises.add(exercise);
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

        return exercises;
    }

    public List<ExerciseGlossary> retrieveAllPatientExerciseGlossaryDone(int userID) {
        String query = "SELECT eg.* FROM exercise_glossary eg JOIN exercise e ON eg.ID_exercise = e.ID_exercise\n" +
                        "WHERE e.ID_user = ?;\n";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ExerciseGlossary> exercises = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ExerciseGlossary exercise = extractExerciseFromResultSet(resultSet);
                exercises.add(exercise);
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

        return exercises;
    }
}
