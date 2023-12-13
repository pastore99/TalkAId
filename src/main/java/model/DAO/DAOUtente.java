package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtente {

    public boolean checkIfEmailExists(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOConnection.getConnection();

            // Query to check if the email exists
            String query = "SELECT COUNT(*) AS count FROM user WHERE Email = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                // If count is greater than 0, the email exists
                return count > 0;
            }

        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            e.printStackTrace();
        } finally {
            try {
                // Close resources in the reverse order of their creation
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }

        // Default to false if an exception occurs
        return false;
    }

    public int createUser(String email, String password, int therapistId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOConnection.getConnection();

            // Query to insert a new user and retrieve the generated ID
            String query = "INSERT INTO user (Email, Password, ID_Therapist) VALUES (?, ?, ?)";

            // Specify that we want to retrieve the generated keys
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, therapistId);

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Retrieve the generated ID
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                // Return the generated ID
                return resultSet.getInt(1);
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

        // Default to -1 if an exception occurs
        return -1;
    }
}
