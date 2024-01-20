package model.DAO;

import model.entity.User;
import model.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe DAOCondition provvede ai metodi per manipolare le informazioni degli User dal database.
 */
public class DAOUser {
    private static final Logger logger = LoggerFactory.getLogger(DAOUser.class);

    private Connection connection;

    public DAOUser(Connection connection) {
        this.connection = connection;
    }

    public DAOUser() {
        try {
            this.connection = DAOConnection.getConnection();
        } catch (SQLException e) {
            logger.error("Error getting connection", e);
        }
    }
    /**
     * Private helper method that takes a ResultSet object and constructs a User object from it.
     *
     * @param resultSet A ResultSet object containing a row from the User table.
     * @return A User object constructed from the ResultSet.
     * @throws SQLException If any database error occurs.
     */
    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("ID"));
        user.setEmail(resultSet.getString("Email"));
        user.setPassword(resultSet.getString("Password"));
        user.setIdTherapist(resultSet.getInt("ID_Therapist"));
        user.setActivationDate(resultSet.getTimestamp("ActivationDate"));
        user.setActive(resultSet.getBoolean("Active"));
        user.setAnalytics(resultSet.getBoolean("Analytics"));
        user.setEmailNotifications(resultSet.getBoolean("EmailNotifications"));
        user.setNotificationTime(resultSet.getString("NotificationTime"));

        return user;
    }

    /**
     * Checks if a given email is present in the User table.
     *
     * @param email The email to check.
     * @return true if the email exists in the User table; false otherwise.
     */
    public boolean checkIfEmailExists(String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

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
            logger.error("Error query", e);
        } finally {
            try {
                // Close resources in the reverse order of their creation
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        // Default to false if an exception occurs
        return false;
    }

    /**
     * Creates a new user in the User table.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @param therapistId The ID of the therapist associated with the user.
     * @return The ID of the newly created user, or -1 if an error occurs.
     */
    public int createUser(String email, String password, int therapistId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

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

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        // Default to -1 if an exception occurs
        return -1;
    }

    /**
     * Retrieves a User from the User table based on an ID or an email.
     *
     * @param idOrEmail Either an Integer representing the User's ID or a String representing the User's email.
     * @return The User object if found, or null if not found.
     */
    public User getUserByIdOrEmail(Object idOrEmail) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            String query = null;

            if (idOrEmail instanceof Integer) {
                query = "SELECT * FROM user WHERE ID = ?";
            } else if (idOrEmail instanceof String) {
                query = "SELECT * FROM user WHERE Email = ?";
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, idOrEmail);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        return null; // or you may throw an exception here
    }

    /**
     * Resets a user's password.
     *
     * @param email The email of the user.
     * @param newPassword The new password to set for the user.
     * @return true if the password was successfully updated; false otherwise.
     */
    public boolean resetPassword(String email, String newPassword) {
        PreparedStatement preparedStatement = null;

        try {
            // Get connection
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            // Query to update password for the given email
            String query = "UPDATE user SET Password = ? WHERE Email = ?";

            // Prepare the statement
            preparedStatement = connection.prepareStatement(query);

            // Set the parameters
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            // Execute the update query
            int rowsModified = preparedStatement.executeUpdate();

            // If rowsModified is greater than 0, then a row has been updated.
            // So, return true. If not, return false.
            return rowsModified > 0;
        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            logger.error("Error query", e);
        } finally {
            try {
                // Close everything properly
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        // Default to false if an exception occurs
        return false;
    }

    public String updateUser(int idUser, String email, String address) {
        PreparedStatement preparedStatement = null;
        boolean isEmailToUpdate = email != null && !checkIfEmailExists(email);
        boolean isAddressToUpdate = address != null;

        if (!isEmailToUpdate && !isAddressToUpdate) {
            return "Invalid. No update performed.";
        }

        StringBuilder queryBuilder = new StringBuilder("UPDATE user SET ");
        if (isEmailToUpdate) {
            queryBuilder.append("Email = ?");
        }
        if (isAddressToUpdate) {
            if (isEmailToUpdate) {
                queryBuilder.append(", ");
            }
            queryBuilder.append("Address = ?");
        }
        queryBuilder.append(" WHERE ID = ?");

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            preparedStatement = connection.prepareStatement(queryBuilder.toString());

            int parameterIndex = 1;
            if (isEmailToUpdate) {
                preparedStatement.setString(parameterIndex++, email);
            }
            if (isAddressToUpdate) {
                preparedStatement.setString(parameterIndex++, address);
            }
            preparedStatement.setInt(parameterIndex, idUser);

            preparedStatement.executeUpdate();

            StringBuilder successUpdateMessage = new StringBuilder();
            if (isEmailToUpdate && isAddressToUpdate) {
                successUpdateMessage.append("Both email and address");
            } else if (isEmailToUpdate) {
                successUpdateMessage.append("Email");
            } else {
                successUpdateMessage.append("Address");
            }
            successUpdateMessage.append(" have been updated successfully.");

            return successUpdateMessage.toString();

        } catch (SQLException e) {
            logger.error("Error query", e);
            return "Update not possible due to a server connection issue.";
        } finally {
        try {
            if (preparedStatement != null) preparedStatement.close();
            DAOConnection.releaseConnection(connection);
        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            logger.error("Error finally", e);
        }
    }
    }

    /**
     * Updates the user's analytics choice in the User table.
     *
     * @param userId The id of the user.
     * @param value  The analytics choice value to set for the user.
     * @return true if the choice was successfully updated; false otherwise.
     */
    public boolean updateAnalyticsPreference(String userId, boolean value) {
        PreparedStatement preparedStatement = null;

        try {
            // Get connection
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            // Query to update analytics choice for the given userId
            String query = "UPDATE user SET Analytics = ? WHERE ID = ?";

            // Prepare the statement
            preparedStatement = connection.prepareStatement(query);

            // Set the parameters
            preparedStatement.setBoolean(1, value);
            preparedStatement.setString(2, userId);

            // Execute the update query
            int rowsModified = preparedStatement.executeUpdate();

            // If rowsModified is greater than 0, then a row has been updated.
            // So, return true. If not, return false.
            return rowsModified > 0;
        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            logger.error("Error query", e);
        } finally {
            try {
                // Close everything properly
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        // Default to false if an exception occurs
        return false;
    }

    /**
     * Updates the user's Email Time in the User table.
     *
     * @param id The id of the user.
     * @param value The email time value to set for the user.
     * @return true if the email time was successfully updated; false otherwise.
     */
    public boolean updateEmailTime(String id, String value) {
        PreparedStatement preparedStatement = null;

        try {
            // Get connection
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            // Query to update Email Time for the given id
            String query = "UPDATE user SET NotificationTime = ? WHERE ID = ?";

            // Prepare the statement
            preparedStatement = connection.prepareStatement(query);

            // Set the parameters
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, id);

            // Execute the update query
            int rowsModified = preparedStatement.executeUpdate();

            // If rowsModified is greater than 0, then a row has been updated.
            // So, return true. If not, return false.
            return rowsModified > 0;
        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            logger.error("Error query", e);
        } finally {
            try {
                // Close everything properly
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error query", e);
            }
        }

        // Default to false if an exception occurs
        return false;
    }

    /**
     * Deletes a user from the User table based on an ID or an email.
     *
     * @param idOrEmail Either an Integer representing the User's ID or a String representing the User's email.
     * @return true if the user was successfully deleted; false otherwise.
     */
    public boolean deleteUserByIdOrEmail(Object idOrEmail) {
        PreparedStatement preparedStatement = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            String query = null;

            if (idOrEmail instanceof Integer) {
                query = "DELETE FROM user WHERE ID = ?";
            } else if (idOrEmail instanceof String) {
                query = "DELETE FROM user WHERE Email = ?";
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, idOrEmail);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        // Default to false if an exception occurs
        return false;
    }


    public ArrayList<UserInfo> getUsersAndPersonalInfoByIdTherapist(int idTherapist) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<UserInfo> list_user=new ArrayList<>();

        try {
            connection = DAOConnection.getConnection();
            String query = "SELECT ID,Email,ActivationDate,Firstname,Lastname,DateOfBirth,Gender,Address,SSN,Phone FROM user,personal_info WHERE ID_Therapist  = ? AND user.ID= personal_info.ID_USER;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, idTherapist);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserInfo u=new UserInfo();
                u.setId(resultSet.getInt("ID"));
                u.setEmail(resultSet.getString("Email"));
                u.setActivationDate(resultSet.getTimestamp("ActivationDate"));
                u.setFirstname(resultSet.getString("Firstname"));
                u.setLastname(resultSet.getString("Lastname"));
                u.setDateOfBirth(resultSet.getDate("DateOfBirth"));
                u.setGender(resultSet.getString("Gender"));
                u.setAddress(resultSet.getString("Address"));
                u.setSsn(resultSet.getString("SSN"));
                u.setPhone(resultSet.getString("Phone"));

                list_user.add(u);
            }
            return list_user;
        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        return null; // or you may throw an exception here
    }

    public HashMap<Integer, UserInfo> getMapUsersAndPersonalInfoByIdTherapist(int idTherapist) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Integer, UserInfo> userMap = new HashMap<>();

        try {
            connection = DAOConnection.getConnection();
            String query = "SELECT ID,Email,ActivationDate,Firstname,Lastname,DateOfBirth,Gender,Address,SSN,Phone FROM user,personal_info WHERE ID_Therapist  = ? AND user.ID= personal_info.ID_USER;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, idTherapist);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserInfo u=new UserInfo();
                int userId = resultSet.getInt("ID");
                u.setId(userId);
                u.setEmail(resultSet.getString("Email"));
                u.setActivationDate(resultSet.getTimestamp("ActivationDate"));
                u.setFirstname(resultSet.getString("Firstname"));
                u.setLastname(resultSet.getString("Lastname"));
                u.setDateOfBirth(resultSet.getDate("DateOfBirth"));
                u.setGender(resultSet.getString("Gender"));
                u.setAddress(resultSet.getString("Address"));
                u.setSsn(resultSet.getString("SSN"));
                u.setPhone(resultSet.getString("Phone"));

                userMap.put(userId, u);
            }
            return userMap;
        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        return null; // or you may throw an exception here
    }


}
