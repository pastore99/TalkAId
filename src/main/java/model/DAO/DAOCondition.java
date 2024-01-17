package model.DAO;

import model.entity.Condition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCondition {
    private Connection connection;
    public DAOCondition(Connection connection) {this.connection=connection;}

    public DAOCondition() {
        try{
            this.connection=DAOConnection.getConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Condition getConditionFromResultSet(ResultSet resultSet) throws SQLException {
        Condition c = new Condition();

        c.setIdCondition(resultSet.getInt("ID_condition"));
        c.setDisorderDescription(resultSet.getString("DisorderDescription"));
        c.setDisorderName(resultSet.getString("DisorderName"));
        c.setSeverity(-1);

        return c;
    }

    private Condition getPeronalConditionFromResultSet(ResultSet resultSet) throws SQLException {
        Condition c = new Condition();

        c.setIdCondition(resultSet.getInt("ID_condition"));
        c.setDisorderDescription(resultSet.getString("DisorderDescription"));
        c.setDisorderName(resultSet.getString("DisorderName"));
        c.setSeverity(resultSet.getInt("Severity"));

        return c;
    }

    public ArrayList<Condition> getConditionsOfPatient(int id_patient) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Condition> list_PersonalCondition=new ArrayList<>();
        try {

            connection = connection.isClosed() ? DAOConnection.getConnection():connection;
            String query = null;

            query = "SELECT c.ID_Condition,c.DisorderName, c.DisorderDescription, pc.Severity\n" +
                    "FROM `condition` c\n" +
                    "JOIN PatientCondition pc ON c.ID_condition = pc.ID_condition\n" +
                    "WHERE pc.ID_patient = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, id_patient);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list_PersonalCondition.add(getPeronalConditionFromResultSet(resultSet));
            }

            return list_PersonalCondition;
        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }
        return null; // or you may throw an exception here
    }

    public ArrayList<Condition> getConditionsNOTOfPatient(int id_patient) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Condition> list_PersonalCondition=new ArrayList<>();
        try {

            connection = connection.isClosed() ? DAOConnection.getConnection():connection;
            String query = null;

            query = "SELECT c.*\n" +
                    "FROM `condition` c\n" +
                    "LEFT JOIN PatientCondition pc ON c.ID_condition = pc.ID_condition AND pc.ID_patient = ?\n" +
                    "WHERE pc.ID_patient IS NULL\n" +
                    "ORDER BY c.DisorderName;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, id_patient);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list_PersonalCondition.add(getConditionFromResultSet(resultSet));
            }
            return list_PersonalCondition;
        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }
        return null; // or you may throw an exception here
    }


    public boolean AddConditionPatient(int ID_condition, int ID_patient, int Severity) {

        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection():connection;
            connection.setAutoCommit(false);  // Start a transaction


            String query = "INSERT INTO PatientCondition (ID_condition, ID_patient, Severity)\n" +
                    "VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID_condition);
            preparedStatement.setInt(2, ID_patient);
            preparedStatement.setInt(3, Severity);
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

        return false;  // Default to false if an exception occurs
    }

    public boolean RemoveConditionPatient(int ID_condition, int ID_patient) {

        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection():connection;
            connection.setAutoCommit(false);  // Start a transaction


            String query = "DELETE FROM PatientCondition\n" +
                    "WHERE ID_condition = ? AND ID_patient = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID_condition);
            preparedStatement.setInt(2, ID_patient);
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

        return false;  // Default to false if an exception occurs
    }
}
