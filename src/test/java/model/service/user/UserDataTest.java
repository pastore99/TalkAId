package model.service.user;


import model.DAO.DAOUser;
import model.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class UserDataTest {

    private UserData ud;
    private int id;
    @BeforeEach
    void setup() { ud = new UserData(); }

    @Test
    void createUser() {
        // Arrange
        String email = "unittest@example.com";
        String password = "password";
        int therapistId = 999;
        if(ud.checkIfEmailExists("unittest@example.com")) {
            new DAOUser().deleteUserByIdOrEmail("unittest@example.com");
        }
        // Act
        int result = ud.createUser(email, password, therapistId);
        // Assert
        assertTrue(result > 0, "The result should be positive");
        new DAOUser().deleteUserByIdOrEmail(id);
        id = result;
    }

    @Test
    void checkIfEmailExists(){
        assertTrue(ud.checkIfEmailExists("doc1@example.com"), "this has been generated before, it must exists");
    }

    @Test
    void getUser() {
        User user = ud.getUser("doc1@example.com");
        assertNotNull(user, " User with email unittest@example.com must exist");
        if (user != null)
            assertEquals("doc1@example.com", user.getEmail(), "Email should match");
    }

    @Test
    void isTherapist() {
        User user = new User();
        user.setIdTherapist(0);
        boolean result = ud.isTherapist(user);
        assertTrue(result, "User should be a therapist");

        User user2 = new User();
        user.setIdTherapist(9);
        boolean result2 = ud.isTherapist(user);
        assertFalse(result2, "User should not be a therapist");
    }

    @Test
    void updateAnalyticsPreference() {


        String userId = Integer.toString(id);
        boolean result = ud.updateAnalyticsPreference(userId, true);
        assertTrue(result, "Analytics preference should be updated");
    }

    @Test
    void updateEmailTime() {
        String userId = Integer.toString(id);
        boolean result = ud.updateEmailTime(userId, "11:00|12:00");
        assertTrue(result, "Email time should be updated");
    }

}
