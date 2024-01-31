package model.DAO;

import model.entity.Exercise;
import model.entity.SlimmerExercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe DAOExercise provvede ai metodi per manipolare le informazioni degli Exercise dal database.
 */
public class DAOExercise {
    private static final Logger logger = LoggerFactory.getLogger(DAOExercise.class);

    private Connection connection;

    public DAOExercise(Connection connection) {
        this.connection = connection;
    }

    public DAOExercise() {
        try {
            this.connection = DAOConnection.getConnection();
        } catch (SQLException e) {
            logger.error("Error getting connection", e);
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
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }

        return null;
    }

    public List<SlimmerExercise> retrieveNotDoneExercises(int patientId) {
        List<SlimmerExercise> exercises = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            String query = "SELECT e.ID_exercise, e.ID_user, eg.ExerciseName, e.InsertionDate, eg.ExerciseDescription, e.Feedback, eg.Difficulty, eg.Target, eg.Type, e.Evaluation FROM exercise e" +
                    " JOIN exercise_glossary eg ON e.ID_exercise = eg.ID_exercise" +
                    " WHERE e.CompletionDate IS NULL AND e.ID_user = ? AND e.Recommended != 0 ORDER BY InsertionDate";

            stmt = connection.prepareStatement(query);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                SlimmerExercise exercise = new SlimmerExercise(
                        rs.getInt("ID_exercise"),
                        rs.getInt("ID_user"),
                        rs.getString("ExerciseName"),
                        rs.getString("ExerciseDescription"),
                        rs.getInt("Feedback"),
                        rs.getDate("InsertionDate"),
                        rs.getInt("Difficulty"),
                        rs.getString("Target"),
                        rs.getString("Type"),
                        rs.getInt("Evaluation")
                );
                exercises.add(exercise);
            }
        } catch(SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
        return exercises;
    }

    public List<SlimmerExercise> retrieveDoneExercises(int patientId) {
        List<SlimmerExercise> exercises = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            String query = "SELECT e.ID_exercise, e.ID_user, eg.ExerciseName, e.InsertionDate, eg.ExerciseDescription, e.Feedback, eg.Difficulty, eg.Target, eg.Type, e.Evaluation FROM exercise e" +
                    " JOIN exercise_glossary eg ON e.ID_exercise = eg.ID_exercise" +
                    " WHERE e.CompletionDate IS NOT NULL AND e.ID_user = ?";

            stmt = connection.prepareStatement(query);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                SlimmerExercise exercise = new SlimmerExercise(
                        rs.getInt("ID_exercise"),
                        rs.getInt("ID_user"),
                        rs.getString("ExerciseName"),
                        rs.getString("ExerciseDescription"),
                        rs.getInt("Feedback"),
                        rs.getDate("InsertionDate"),
                        rs.getInt("Difficulty"),
                        rs.getString("Target"),
                        rs.getString("Type"),
                        rs.getInt("Evaluation")
                );
                exercises.add(exercise);
            }
        } catch(SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
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
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
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
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
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
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
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
            logger.error("Error query", e);
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
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
            logger.error("Error query", e);
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error query", e);
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
            logger.error("Error query", e);
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
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
            logger.error("Error query", e);
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
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
            logger.error("Error query", e);
            try {
                if (connection != null) {
                    connection.rollback();  // Rollback the transaction in case of an exception
                }
            } catch (SQLException ex) {
                logger.error("Error rollback", e);
            }
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }
        return false;
    }


    public List<SlimmerExercise> getExerciseToApprove(int therapistId){
        List<SlimmerExercise> exercises = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            String query = "SELECT e.ID_exercise, e.ID_user, eg.ExerciseName, e.InsertionDate, eg.ExerciseDescription, e.Feedback, eg.Difficulty, eg.Target, eg.Type, e.Evaluation " +
                    "FROM exercise e JOIN exercise_glossary eg ON e.ID_exercise = eg.ID_exercise JOIN user u ON e.ID_user = u.ID " +
                    "WHERE e.CompletionDate IS NULL AND u.ID_Therapist = ? AND e.Recommended = 0";

            stmt = connection.prepareStatement(query);
            stmt.setInt(1, therapistId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                SlimmerExercise exercise = new SlimmerExercise(
                        rs.getInt("ID_exercise"),
                        rs.getInt("ID_user"),
                        rs.getString("ExerciseName"),
                        rs.getString("ExerciseDescription"),
                        rs.getInt("Feedback"),
                        rs.getDate("InsertionDate"),
                        rs.getInt("Difficulty"),
                        rs.getString("Target"),
                        rs.getString("Type"),
                        rs.getInt("Evaluation")
                );
                exercises.add(exercise);
            }
        } catch(SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }
        return exercises;
    }

    public boolean approveExercise(int exerciseId, Date insertDate, int userId){
        String query = "UPDATE exercise SET Recommended = 1 WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ? AND Recommended = 0;";
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, exerciseId);
            preparedStatement.setDate(3, insertDate);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("Error query", e);
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
    }

    public boolean deleteExercise(int exerciseId, Date insertDate, int userId){
        String query = "DELETE FROM exercise WHERE ID_user = ? AND ID_exercise = ? AND InsertionDate = ? AND Recommended = 0;";
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, exerciseId);
            preparedStatement.setDate(3, insertDate);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("Error query", e);
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
    }

    public boolean approveMultipleExercise(int userId){
        String query = "UPDATE exercise SET Recommended = 1 WHERE ID_user = ? AND Recommended = 0;";
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("Error query", e);
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
    }

    public boolean deleteMultipleExercise(int userId){
        String query = "DELETE FROM exercise WHERE ID_user = ? AND Recommended = 0;";
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("Error query", e);
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
    }

    public Map<String, Integer> retrieveAllStatsPatientExerciseDone(int userID) {
        String query = "SELECT eg.Type, " +
                "COUNT(CASE WHEN e.CompletionDate IS NOT NULL THEN e.ID_exercise END) as CompletedCount, " +
                "COUNT(e.ID_exercise) as TotalAssignedCount, " +
                "IFNULL(COUNT(CASE WHEN e.CompletionDate IS NOT NULL THEN e.ID_exercise END) / NULLIF(COUNT(e.ID_exercise), 0) * 100, 0) as CompletionPercentage " +
                "FROM exercise_glossary eg " +
                "LEFT JOIN exercise e ON eg.ID_exercise = e.ID_exercise AND e.ID_user = ? " +
                "GROUP BY eg.Type; ";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, Integer> result = new HashMap<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String exerciseType = resultSet.getString("Type"); // Fix: Use "Type" instead of "ExerciseType"
                int completionPercentage = Math.round((float) resultSet.getDouble("CompletionPercentage"));

                result.put(exerciseType, completionPercentage);
            }

        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }

        return result;
    }

}
