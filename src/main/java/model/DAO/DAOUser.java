package model.DAO;

import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        // Your existing code here
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
        // Your existing code here
    }

    /**
     * Retrieves a User from the User table based on an ID or an email.
     *
     * @param idOrEmail Either an Integer representing the User's ID or a String representing the User's email.
     * @return The User object if found, or null if not found.
     */
    public User getUserByIdOrEmail(Object idOrEmail) {
        // Your existing code here
    }

    /**
     * Resets a user's password.
     *
     * @param email The email of the user.
     * @param newPassword The new password to set for the user.
     * @return true if the password was successfully updated; false otherwise.
     */
    public boolean resetPassword(String email, String newPassword) {
        // Your existing code here
    }
}