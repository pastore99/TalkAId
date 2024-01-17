package model.DAO;

import model.entity.Exercise;
import model.entity.ExerciseGlossary;
import model.entity.Schedule;
import model.entity.SlimmerExercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT * FROM exercise WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ?;";
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

    public List<SlimmerExercise> retrieveNotDoneExercises(int patientId) {
        List<SlimmerExercise> exercises = new ArrayList<>();
        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            String query = "SELECT e.ID_exercise, eg.ExerciseName, e.InsertionDate, e.Evaluation FROM exercise e" +
                    " JOIN exercise_glossary eg ON e.ID_exercise = eg.ID_exercise" +
                    " WHERE e.CompletionDate IS NULL AND e.ID_user = ? ORDER BY InsertionDate ASC";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                SlimmerExercise exercise = new SlimmerExercise(
                        rs.getInt("ID_exercise"),
                        rs.getString("ExerciseName"),
                        rs.getDate("InsertionDate"),
                        rs.getInt("Evaluation")
                );
                exercises.add(exercise);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }

    public List<SlimmerExercise> retrieveDoneExercises(int patientId) {
        List<SlimmerExercise> exercises = new ArrayList<>();
        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            String query = "SELECT e.ID_exercise, eg.ExerciseName, e.InsertionDate, e.Evaluation FROM exercise e" +
                    " JOIN exercise_glossary eg ON e.ID_exercise = eg.ID_exercise" +
                    " WHERE e.CompletionDate IS NOT NULL AND e.ID_user = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                SlimmerExercise exercise = new SlimmerExercise(
                        rs.getInt("ID_exercise"),
                        rs.getString("ExerciseName"),
                        rs.getDate("InsertionDate"),
                        rs.getInt("Evaluation")
                );
                exercises.add(exercise);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }

    public List<Exercise> retrieveAllPatientExerciseDone(int userID) {
        String query = "SELECT * FROM exercise WHERE ID_user = ? AND CompletionDate IS NOT NULL ORDER BY InsertionDate DESC;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Exercise> exercises = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Exercise exercise = extractExerciseFromResultSet(resultSet);
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

    public List<Exercise> retrievePatientExerciseDone(int patientID) {
        String query = "SELECT *\n" +
                "FROM exercise\n" +
                "WHERE ID_user = ? AND Evaluation IS NOT NULL\n" +
                "ORDER BY InsertionDate;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Exercise> exercises = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Exercise exercise = extractExerciseFromResultSet(resultSet);
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

    public Blob getExerciseExecution(int userID, int exerciseID, Date insertDate) {
        String query = "SELECT Execution FROM exercise WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ?";
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
                return resultSet.getBlob("Execution");
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
        String query = "UPDATE exercise SET Evaluation = ? , CompletionDate = CURRENT_DATE WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ?;";
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

    public boolean AddExerciseRecommendation(int idExercise, int idPatient) {

        PreparedStatement preparedStatement= null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            connection.setAutoCommit(false);  // Start a transaction


            String query = "INSERT INTO exercise (ID_user, ID_exercise, InsertionDate, CompletionDate, Execution, Evaluation, Recommended, Feedback)\n" +
                    "VALUES (?,?,CURRENT_DATE,NULL,NULL,NULL,2,NULL);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idPatient);
            preparedStatement.setInt(2, idExercise);
            preparedStatement.executeUpdate();

            connection.commit();  // Commit the transaction
            return true;  // User created successfully

        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();  // Rollback the transaction in case of an exception
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }
        return false;
    }

}
