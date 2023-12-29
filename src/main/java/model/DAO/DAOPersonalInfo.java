package model.DAO;

import model.entity.PersonalInfo;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOPersonalInfo {

    private PersonalInfo getPersonalInfoFromResultSet(ResultSet resultSet) throws SQLException {
        PersonalInfo pi = new PersonalInfo();

        pi.setIdUser(resultSet.getInt("ID_user"));
        pi.setFirstname(resultSet.getString("Firstname"));
        pi.setLastname(resultSet.getString("Lastname"));
        pi.setDateOfBirth(resultSet.getDate("DateOfBirth"));
        pi.setGender(resultSet.getString("Gender"));
        pi.setAddress(resultSet.getString("Address"));
        pi.setSsn(resultSet.getString("SSN"));
        pi.setPhone(resultSet.getString("Phone"));
        return pi;
    }
    public boolean createRegistry(int id, String name, String surname) {
        Connection connection = null;
        PreparedStatement preparedStatementPersonalInfo = null;

        try {
            connection = DAOConnection.getConnection();
            connection.setAutoCommit(false);  // Start a transaction

            // Insert user data into personal_info table
            String queryAnagrafica = "INSERT INTO personal_info (ID_user, Firstname, Lastname) VALUES (?, ?, ?)";
            preparedStatementPersonalInfo = connection.prepareStatement(queryAnagrafica);
            preparedStatementPersonalInfo.setInt(1, id);
            preparedStatementPersonalInfo.setString(2, name);
            preparedStatementPersonalInfo.setString(3, surname);
            preparedStatementPersonalInfo.executeUpdate();

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
                if (preparedStatementPersonalInfo != null) preparedStatementPersonalInfo.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }

        return false;  // Default to false if an exception occurs
    }

    public PersonalInfo getPersonalInfoById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = DAOConnection.getConnection();
            String query = null;


                query = "SELECT * FROM personal_info  WHERE ID_user = ?";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getPersonalInfoFromResultSet(resultSet);
            }

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


}

