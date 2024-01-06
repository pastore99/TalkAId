package model.DAO;

import model.entity.Exercise;

import java.sql.*;

/**
 * The DAOExercise class provides methods for retrieving Exercise information from a database.
 */
public class DAOExercise {

    private Connection connection;

    public DAOExercise(Connection connection) {
        this.connection = connection;
    }

    public DAOExercise() {
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
    private static Exercise extractExerciseFromResultSet(ResultSet resultSet) throws SQLException {
        Exercise exercise = new Exercise();
        exercise.setIdUser(resultSet.getInt("ID_user"));
        exercise.setIdExercise(resultSet.getInt("ID_exercise"));
        exercise.setInsertionDate(resultSet.getDate("InsertionDate"));
        exercise.setCompletionDate(resultSet.getDate("CompletionDate"));
        exercise.setExecution(resultSet.getBlob("Execution"));
        exercise.setEvaluation(resultSet.getInt("Evaluation"));
        exercise.setRecommended(resultSet.getInt("Recommended"));
        exercise.setFeedback(resultSet.getInt("Feedback"));

        return exercise;
    }

    /**
     * Search for an Exercise by its pk in the database.
     *
     * @param userID the id of the user that has the exercise.
     * @param exerciseID the id of the exercise.
     * @param insertDate the date in witch the user got the exercise to do.
     * @return the Exercise if it is found, else null.
     */
    public Exercise getExerciseByPk(int userID, int exerciseID, Date insertDate) {
        String query = "SELECT * FROM exercise WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, exerciseID);
            preparedStatement.setDate(3, insertDate);

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

    public boolean setExerciseExecution(int userID, int exerciseID, Date insertDate, Blob execution) {
        String query = "UPDATE exercise SET Execution = ? WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ?;";
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBlob(1, execution);
            preparedStatement.setInt(2, userID);
            preparedStatement.setInt(3, exerciseID);
            preparedStatement.setDate(4, insertDate);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean setExerciseEvaluation(int userID, int exerciseID, Date insertDate, int evaluation) {
        String query = "UPDATE exercise SET Evaluation = ? WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ?;";
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, evaluation);
            preparedStatement.setInt(2, userID);
            preparedStatement.setInt(3, exerciseID);
            preparedStatement.setDate(4, insertDate);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean setExerciseCompletionDate(int userID, int exerciseID, Date insertDate, Date completion) {
        String query = "UPDATE exercise SET CompletionDate = ? WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ?;";
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, completion);
            preparedStatement.setInt(2, userID);
            preparedStatement.setInt(3, exerciseID);
            preparedStatement.setDate(4, insertDate);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean setExerciseFeedback(int userID, int exerciseID, Date insertDate, int feedback) {
        String query = "UPDATE exercise SET Feedback = ? WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ?;";
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, feedback);
            preparedStatement.setInt(2, userID);
            preparedStatement.setInt(3, exerciseID);
            preparedStatement.setDate(4, insertDate);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
