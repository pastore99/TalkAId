package model.DAO;

import model.entity.PersonalInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La classe DAOPersonalInfo provvede ai metodi per manipolare le informazioni delle PersonalInfo dal database.
 */
public class DAOPersonalInfo {
    private static final Logger logger = LoggerFactory.getLogger(DAOPersonalInfo.class);

    private Connection connection;

    public DAOPersonalInfo(Connection connection) {
        this.connection = connection;
    }

    public DAOPersonalInfo() {
        try {
            this.connection = DAOConnection.getConnection();
        } catch (SQLException e) {
            logger.error("Error getting connection", e);
        }
    }
    private PersonalInfo getPersonalInfoFromResultSet(ResultSet resultSet) throws SQLException {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setIdUser(resultSet.getInt("ID_user"));
        personalInfo.setFirstname(resultSet.getString("Firstname"));
        personalInfo.setLastname(resultSet.getString("Lastname"));
        personalInfo.setDateOfBirth(resultSet.getDate("DateOfBirth"));
        personalInfo.setGender(resultSet.getString("Gender"));
        personalInfo.setAddress(resultSet.getString("Address"));
        personalInfo.setSsn(resultSet.getString("SSN"));
        personalInfo.setPhone(resultSet.getString("Phone"));

        return personalInfo;
    }
    public boolean createRegistry(int id, String name, String surname) {
        PreparedStatement preparedStatementPersonalInfo = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
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
                if (preparedStatementPersonalInfo != null) preparedStatementPersonalInfo.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        return false;  // Default to false if an exception occurs
    }

    public PersonalInfo getPersonalInfo(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "SELECT * FROM personal_info WHERE ID_user = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getPersonalInfoFromResultSet(resultSet);
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

        return null; // Return null if personal_info does not exist
    }

    public boolean deleteRegistry(int createdUserId) {
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            connection.setAutoCommit(false); // Start a transaction

            String sql = "DELETE FROM personal_info WHERE ID_user = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, createdUserId);
            int rowAffected = preparedStatement.executeUpdate();

            connection.commit(); // Commit the transaction

            return rowAffected > 0; // Return true if the deletion was successful

        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            logger.error("Error query", e);
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback the transaction in case of an exception
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

        return false; // Default to false if an exception occurs
    }


    public boolean updatePersonalInfofromId(int id, String FirstName, String LastName, String Phone) {
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "UPDATE personal_info SET Firstname = ?, Lastname = ?, Phone = ? WHERE ID_user = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, FirstName);
            preparedStatement.setString(2, LastName);
            preparedStatement.setString(3, Phone);
            preparedStatement.setInt(4, id);

            int result = preparedStatement.executeUpdate();

            if (result>0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error query", e);
            return false;
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

    public boolean updatePersonalInfoAndUserFromId(int id, String FirstName, String LastName, String Phone, String Email, String Address) {
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            // Primo aggiornamento su personal_info
            String personalInfoUpdateSql = "UPDATE personal_info SET Firstname = ?, Lastname = ?, Phone = ?, Address = ? WHERE ID_user = ?";
            preparedStatement = connection.prepareStatement(personalInfoUpdateSql);
            preparedStatement.setString(1, FirstName);
            preparedStatement.setString(2, LastName);
            preparedStatement.setString(3, Phone);
            preparedStatement.setString(4, Address);
            preparedStatement.setInt(5, id);

            int personalInfoUpdateResult = preparedStatement.executeUpdate();

            // Secondo aggiornamento su user
            String userUpdateSql = "UPDATE user SET Email = ? WHERE ID = ?";
            preparedStatement = connection.prepareStatement(userUpdateSql);
            preparedStatement.setString(1, Email);
            preparedStatement.setInt(2, id);

            int userUpdateResult = preparedStatement.executeUpdate();

            if (personalInfoUpdateResult > 0 && userUpdateResult > 0) {
                return true; // Entrambi gli aggiornamenti hanno avuto successo
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
            } else {
                // Gestione di altre eccezioni
                logger.error("Errore query", e);
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
}


