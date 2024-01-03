package model.DAO;

import model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOUserTest {

    private DAOUser daoUser;

    @BeforeEach
    void setup() {
        daoUser = new DAOUser();
    }

    @Test
    void testCheckIfEmailExists() {
        // Existing email in the table
        String existingEmail = "johndoa@example.com";

        // Call the method to test
        boolean emailExists = daoUser.checkIfEmailExists(existingEmail);

        // Check the result
        assertTrue(emailExists, "Existing email should be found in the table");

        // Non-existing email in the table
        String nonExistingEmail = "nonexistent@example.com";

        // Call the method to test
        boolean nonExistingEmailExists = daoUser.checkIfEmailExists(nonExistingEmail);

        // Check the result
        assertFalse(nonExistingEmailExists, "Non-existing email should not be found in the table");
    }

    @Test
    void testCreateUser() {
        // Mock user data
        String email = "newuser@example.com";
        String password = "password123";
        int therapistId = 8;

        // Call the method to test
        int createdUserId = daoUser.createUser(email, password, therapistId);

        // Check the result
        assertTrue(createdUserId > 0, "New user should be created with a positive ID");

        // Clean up: Delete the created user
        assertTrue(daoUser.deleteUserByIdOrEmail(createdUserId));
        assertFalse(daoUser.deleteUserByIdOrEmail("newuser@example.com"));

    }

    @Test
    void testGetUserByIdOrEmail() {
        // Existing user ID in the table
        int existingUserId = 8;

        // Call the method to test
        User existingUserById = daoUser.getUserByIdOrEmail(existingUserId);

        // Check the result
        assertNotNull(existingUserById, "Existing user by ID should be found in the table");
        assertEquals(existingUserId, existingUserById.getId(), "User ID should match");

        // Existing email in the table
        String existingEmail = "johndoa@example.com";

        // Call the method to test
        User existingUserByEmail = daoUser.getUserByIdOrEmail(existingEmail);

        // Check the result
        assertNotNull(existingUserByEmail, "Existing user by email should be found in the table");
        assertEquals(existingEmail, existingUserByEmail.getEmail(), "User email should match");

        // Non-existing ID or email
        Object nonExistingIdOrEmail = "nonexistent";

        // Call the method to test
        User nonExistingUser = daoUser.getUserByIdOrEmail(nonExistingIdOrEmail);

        // Check the result
        assertNull(nonExistingUser, "Non-existing user should not be found in the table");
    }

    @Test
    void testResetPassword() {
        // Mock user email and new password
        String userEmail = "johndoa@example.com";
        String newPassword = "newpassword123";

        // Call the method to test
        boolean passwordReset = daoUser.resetPassword(userEmail, newPassword);

        // Check the result
        assertTrue(passwordReset, "Password should be successfully reset");

        daoUser.resetPassword(userEmail, "$2a$12$WE/ZQ5SqrkMPjNT57Mje.ePEVdUEm8tTeIlldM35DEuLEVQYuUxmm");
    }

    @Test
    void testUpdateAnalyticsPreference() {
        // Mock user ID and analytics value
        String userId = "8";
        boolean newAnalyticsValue = true;

        // Call the method to test
        boolean analyticsUpdated = daoUser.updateAnalyticsPreference(userId, newAnalyticsValue);

        // Check the result
        assertTrue(analyticsUpdated, "Analytics preference should be successfully updated");

        daoUser.updateAnalyticsPreference(userId, false);
    }

    @Test
    void testUpdateEmailTime() {
        // Mock user ID and new email time
        String userId = "8";
        String newEmailTime = "18:30|19:00";

        // Call the method to test
        boolean emailTimeUpdated = daoUser.updateEmailTime(userId, newEmailTime);

        // Check the result
        assertTrue(emailTimeUpdated, "Email time should be successfully updated");

        // Clean up: Reset the email time back to the original
        daoUser.updateEmailTime(userId, "17:16|17:19");
    }
}

