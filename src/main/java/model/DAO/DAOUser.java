package model.DAO;

import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import model.service.encryption.Encryption;

import javax.mail.Address;

/**
 * DAOUser is a class that provides methods for accessing the User table in the database.
 */
public class DAOUser {

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

    /**
     * Creates a new user in the User table.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @param therapistId The ID of the therapist associated with the user.
     * @return The ID of the newly created user, or -1 if an error occurs.
     */
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

    /**
     * Retrieves a User from the User table based on an ID or an email.
     *
     * @param idOrEmail Either an Integer representing the User's ID or a String representing the User's email.
     * @return The User object if found, or null if not found.
     */
    public User getUserByIdOrEmail(Object idOrEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = DAOConnection.getConnection();
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

    /**
     * Resets a user's password.
     *
     * @param email The email of the user.
     * @param newPassword The new password to set for the user.
     * @return true if the password was successfully updated; false otherwise.
     */
    public boolean resetPassword(String email, String newPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get connection
            connection = DAOConnection.getConnection();

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
            e.printStackTrace();
        } finally {
            try {
                // Close everything properly
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

    public String updateUser(int idUser, String Email, String address) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (Email != null && address!=null) {
            if (checkIfEmailExists(Email)) {
                try {
                    // Get connection
                    connection = DAOConnection.getConnection();

                    // Query to update password for the given email
                    String query = "UPDATE user SET Email = ?, Address=? WHERE ID = ?";

                    // Prepare the statement
                    preparedStatement = connection.prepareStatement(query);

                    // Set the parameters
                    preparedStatement.setString(1, Email);
                    preparedStatement.setString(2, address);
                    preparedStatement.setInt(3, idUser);

                    // Execute the update query
                    int rowsModified = preparedStatement.executeUpdate();

                    // If rowsModified is greater than 0, then a row has been updated.
                    // So, return true. If not, return false.
                    return "Aggioranmento Email e Address riuscito";
                } catch (SQLException e) {
                    // Handle the exception (e.g., log or throw)
                    e.printStackTrace();
                    return "Aggiornamento Email e Address non riuscito";
                } finally {
                    try {
                        // Close everything properly
                        if (preparedStatement != null) preparedStatement.close();
                        DAOConnection.releaseConnection(connection);
                    } catch (SQLException e) {
                        // Handle the exception (e.g., log or throw)
                        e.printStackTrace();
                    }
                }

            } else {
                try {
                    // Get connection
                    connection = DAOConnection.getConnection();

                    // Query to update password for the given email
                    String query = "UPDATE user SET Address=? WHERE ID = ?";

                    // Prepare the statement
                    preparedStatement = connection.prepareStatement(query);

                    // Set the parameters
                    preparedStatement.setString(1, address);
                    preparedStatement.setInt(2, idUser);

                    // Execute the update query
                    int rowsModified = preparedStatement.executeUpdate();

                    // If rowsModified is greater than 0, then a row has been updated.
                    // So, return true. If not, return false.
                    return "Aggioranmento Address riuscito ma l'Email inserità e già usata scegliere un'altra Email";
                } catch (SQLException e) {
                    // Handle the exception (e.g., log or throw)
                    e.printStackTrace();
                    return "Aggiornamento Address non riuscito ma l'Email inserità e già usata scegliere un'altra Email";
                } finally {
                    try {
                        // Close everything properly
                        if (preparedStatement != null) preparedStatement.close();
                        DAOConnection.releaseConnection(connection);
                    } catch (SQLException e) {
                        // Handle the exception (e.g., log or throw)
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        if (Email != null) {
            if (checkIfEmailExists(Email)) {
                try {
                    connection = DAOConnection.getConnection();

                    // Query to update password for the given email
                    String query = "UPDATE user SET Email = ? WHERE ID = ?";

                    // Prepare the statement
                    preparedStatement = connection.prepareStatement(query);

                    // Set the parameters
                    preparedStatement.setString(1, Email);
                    preparedStatement.setInt(2, idUser);

                    // Execute the update query
                    int rowsModified = preparedStatement.executeUpdate();

                    // If rowsModified is greater than 0, then a row has been updated.
                    // So, return true. If not, return false.
                    return "Aggioranmento Email riuscito";
                } catch (SQLException e) {
                    // Handle the exception (e.g., log or throw)
                    e.printStackTrace();
                    return "Aggiornamento Email non riuscito";
                } finally {
                    try {
                        // Close everything properly
                        if (preparedStatement != null) preparedStatement.close();
                        DAOConnection.releaseConnection(connection);
                    } catch (SQLException e) {
                        // Handle the exception (e.g., log or throw)
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                return "l'Email inserità e già usata scegliere un'altra Email";
            }
        }
        else
        {
            try {
                connection = DAOConnection.getConnection();

                // Query to update password for the given email
                String query = "UPDATE user SET Address = ? WHERE ID = ?";

                // Prepare the statement
                preparedStatement = connection.prepareStatement(query);

                // Set the parameters
                preparedStatement.setString(1, address);
                preparedStatement.setInt(2, idUser);

                // Execute the update query
                int rowsModified = preparedStatement.executeUpdate();

                // If rowsModified is greater than 0, then a row has been updated.
                // So, return true. If not, return false.
                return "Aggioranmento Address riuscito";
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
                return "Aggiornamento non riuscito di Address";
            } finally {
                try {
                    // Close everything properly
                    if (preparedStatement != null) preparedStatement.close();
                    DAOConnection.releaseConnection(connection);
                } catch (SQLException e) {
                    // Handle the exception (e.g., log or throw)
                    e.printStackTrace();
                }
            }
        }
    }
    public boolean ControlPassword(int id, String Password)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOConnection.getConnection();

            // Query to check if the email exists
            String query = "SELECT Password FROM user WHERE ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("Password");
                Encryption encryption = new Encryption();
                return encryption.verifyPassword(Password, password);
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

    /**
     * Updates the user's analytics choice in the User table.
     *
     * @param userId The id of the user.
     * @param value  The analytics choice value to set for the user.
     * @return true if the choice was successfully updated; false otherwise.
     */
    public boolean updateAnalyticsPreference(String userId, boolean value) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get connection
            connection = DAOConnection.getConnection();

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
            e.printStackTrace();
        } finally {
            try {
                // Close everything properly
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

    /**
     * Updates the user's Email Time in the User table.
     *
     * @param id The id of the user.
     * @param value The email time value to set for the user.
     * @return true if the email time was successfully updated; false otherwise.
     */
    public boolean updateEmailTime(String id, String value) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get connection
            connection = DAOConnection.getConnection();

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
            e.printStackTrace();
        } finally {
            try {
                // Close everything properly
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

    /**
     * Deletes a user from the User table based on an ID or an email.
     *
     * @param idOrEmail Either an Integer representing the User's ID or a String representing the User's email.
     * @return true if the user was successfully deleted; false otherwise.
     */
    public boolean deleteUserByIdOrEmail(Object idOrEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DAOConnection.getConnection();
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
        } finally {
            try {
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
}
