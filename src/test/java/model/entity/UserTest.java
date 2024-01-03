package model.entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the User class
        User user = new User();

        // Set values using setter methods
        int id = 1;
        String email = "test@example.com";
        String password = "testPassword";
        int idTherapist = 2;
        Timestamp activationDate = Timestamp.valueOf("2022-01-01 12:00:00");
        boolean active = true;
        boolean analytics = false;
        boolean emailNotifications = true;
        String notificationTime = "09:00 AM";

        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setIdTherapist(idTherapist);
        user.setActivationDate(activationDate);
        user.setActive(active);
        user.setAnalytics(analytics);
        user.setEmailNotifications(emailNotifications);
        user.setNotificationTime(notificationTime);

        // Test getter methods
        assertEquals(id, user.getId(), "getId() should return the correct value");
        assertEquals(email, user.getEmail(), "getEmail() should return the correct value");
        assertEquals(password, user.getPassword(), "getPassword() should return the correct value");
        assertEquals(idTherapist, user.getIdTherapist(), "getIdTherapist() should return the correct value");
        assertEquals(activationDate, user.getActivationDate(), "getActivationDate() should return the correct value");
        assertEquals(active, user.isActive(), "isActive() should return the correct value");
        assertEquals(analytics, user.isAnalytics(), "isAnalytics() should return the correct value");
        assertEquals(emailNotifications, user.isEmailNotifications(), "isEmailNotifications() should return the correct value");
        assertEquals(notificationTime, user.getNotificationTime(), "getNotificationTime() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the User class
        User user = new User();

        // Test that the object is not null
        assertNotNull(user, "User object should not be null");
    }
}

