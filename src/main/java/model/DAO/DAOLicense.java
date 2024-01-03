package model.DAO;

import model.entity.License;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DAOLicense class provides methods for retrieving and activating licenses from a database.
 */
public class DAOLicense {

    /**
     * This method extracts License object data from a ResultSet
     *
     * @param resultSet ResultSet object from license table
     * @return License object containing data from resultSet
     * @throws SQLException if there is any SQL related error
     */
    private static License extractLicenseFromResultSet(ResultSet resultSet) throws SQLException {
        License license = new License();
        license.setSequence(resultSet.getString("Sequence"));
        license.setIdUser(resultSet.getInt("ID_User"));
        license.setExpirationDate(resultSet.getDate("ExpirationDate"));
        license.setActive(resultSet.getBoolean("Active"));
        return license;
    }

    /**
     * Search for a license by its code in the database.
     *
     * @param code the license code to search for.
     * @return the License if it is found, else null.
     */
    public License getLicenseByCode(String code) {
        String query = "SELECT * FROM license WHERE Sequence = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractLicenseFromResultSet(resultSet);
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

    /**
     * Activate a license and link it to a user.
     *
     * @param license the License object to activate.
     * @param userId the ID of the user to link to the license.
     */
    public void activate(License license, int userId) {
        String updateQuery = "UPDATE TalkAID2.license SET active = TRUE, ID_User = ? WHERE Sequence = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DAOConnection.getConnection();
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, license.getSequence());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }
    }

    /**
     * Generate a new License to sell
     * @return the sequence of the License if operation succeed, none otherwise
     */
    public String generateLicense(){
        final int length = 8;
        License l = new License(length);
        String insertQuery =  "INSERT INTO TalkAID2.license (Sequence, ID_User, ExpirationDate) VALUES (?, ?, ?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DAOConnection.getConnection();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, l.getSequence());
            preparedStatement.setInt(2, 0);
            preparedStatement.setDate(3, null);
            preparedStatement.executeUpdate();
            return l.getSequence();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }
    }


    /**
     * Generate an invitation code for a new patient
     * @param userId the ID of a speech therapist
     * @return the sequence of the invitation if operation succeed, none otherwise
     */
    public String generateInvitation(int userId){
        final int length = 4;
        License l = new License(length);
        String insertQuery =  "INSERT INTO TalkAID2.license (Sequence, ID_User, ExpirationDate) VALUES (?,?,?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DAOConnection.getConnection();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, l.getSequence());
            preparedStatement.setInt(2, userId);
            preparedStatement.setDate(3, l.getExpirationDate());
            preparedStatement.executeUpdate();
            return l.getSequence();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }
    }

    public boolean deleteLicense(String code) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DAOConnection.getConnection();

            // Prepare the SQL query
            String query = "DELETE FROM TalkAID2.license WHERE Sequence = ?";
            preparedStatement = connection.prepareStatement(query);

            // Set parameter for the prepared statement
            preparedStatement.setString(1, code);

            // Execute update and return boolean based on the affected rows
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the statement and release the connection
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // If exception occurs, return false
        return false;
    }

}