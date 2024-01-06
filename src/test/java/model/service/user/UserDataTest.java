package model.service.user;

import model.DAO.DAOUser;
import model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDataTest {

    private UserData ud;
    private DAOUser daoUser;
    private User user;
    private final int id = 1;

    @BeforeEach
    void setup() {
        // Mock the UserData and DAOUser classes
        ud = mock(UserData.class);
        daoUser = mock(DAOUser.class);

        // Create a real User object for some tests
        user = new User();
        user.setEmail("unittest@example.com");
        user.setIdTherapist(9);
    }

    @Test
    void createUser() {
        // Given
        String email = "unittest2@example.com";
        String password = "password";
        int therapistId = 999;

        // Assuming that DAOUser has a createUser method which is called internally by UserData's createUser method
        when(daoUser.createUser(email, password, therapistId)).thenReturn(1);

        UserData ud = new UserData(daoUser); // Replace this with appropriate constructor or setter method to inject the mock DAOUser.

        // When
        int result = ud.createUser(email, password, therapistId);

        // Then
        verify(daoUser).createUser(email, password, therapistId); // Checks if daoUser's createUser method was called with these parameters
        assertTrue(result > 0);
    }

    @Test
    void checkIfEmailExists(){
        when(ud.checkIfEmailExists("unittest@example.com")).thenReturn(true);
        assertTrue(ud.checkIfEmailExists("unittest@example.com"), "this has been generated before, it must exists");
        verify(ud, times(1)).checkIfEmailExists("unittest@example.com");
    }

    @Test
    void getUser() {
        when(ud.getUser("unittest@example.com")).thenReturn(user);
        User returned = ud.getUser("unittest@example.com");
        assertEquals(returned.getEmail(), user.getEmail(), "Email should match");
        verify(ud).getUser("unittest@example.com");
    }

    @Test
    void isTherapist() {
        when(ud.isTherapist(user)).thenReturn(false);
        assertFalse(ud.isTherapist(user), "User should not be a therapist");
        user.setIdTherapist(0);
        when(ud.isTherapist(user)).thenReturn(true);
        assertTrue(ud.isTherapist(user), "User should be a therapist");
        verify(ud, times(2)).isTherapist(user);
    }

    @Test
    void updateAnalyticsPreference() {
        String userId = Integer.toString(id);
        when(ud.updateAnalyticsPreference(userId, true)).thenReturn(true);
        boolean result = ud.updateAnalyticsPreference(userId, true);
        assertTrue(result, "Analytics preference should be updated");
        verify(ud).updateAnalyticsPreference(userId, true);
    }

    @Test
    void updateEmailTime() {
        String userId = Integer.toString(id);
        when(ud.updateEmailTime(userId, "11:00|12:00")).thenReturn(true);
        boolean result = ud.updateEmailTime(userId, "11:00|12:00");
        assertTrue(result, "Email time should be updated");
        verify(ud).updateEmailTime(userId, "11:00|12:00");
    }

}