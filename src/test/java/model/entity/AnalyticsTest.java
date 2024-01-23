package model.entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AnalyticsTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the Analytics class
        Analytics analytics = new Analytics();

        // Set values using setter methods
        int userId = 1;
        Timestamp timestamp = Timestamp.valueOf("2022-01-01 12:00:00");
        String description = "Test Description";
        String type = "Test Type";

        analytics.setIdUser(userId);
        analytics.setTimestamp(timestamp);
        analytics.setDescription(description);
        analytics.setType(type);

        // Test getter methods
        assertEquals(userId, analytics.getIdUser(), "getIdUser() should return the correct value");
        assertEquals(timestamp, analytics.getTimestamp(), "getTimestamp() should return the correct value");
        assertEquals(description, analytics.getDescription(), "getDescription() should return the correct value");
        assertEquals(type, analytics.getType(), "getType() should return the correct value");
    }

    @Test
    void testConstructor() {
        // Create an instance of the Analytics class using the constructor
        int userId = 1;
        Timestamp timestamp = Timestamp.valueOf("2022-01-01 12:00:00");
        String description = "Test Description";
        String type = "Test Type";

        Analytics analytics = new Analytics();
        analytics.setIdUser(userId);
        analytics.setTimestamp(timestamp);
        analytics.setDescription(description);
        analytics.setType(type);

        // Test getter methods
        assertEquals(userId, analytics.getIdUser(), "getIdUser() should return the correct value");
        assertEquals(timestamp, analytics.getTimestamp(), "getTimestamp() should return the correct value");
        assertEquals(description, analytics.getDescription(), "getDescription() should return the correct value");
        assertEquals(type, analytics.getType(), "getType() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the Analytics class
        Analytics analytics = new Analytics();

        // Test that the object is not null
        assertNotNull(analytics, "Analytics object should not be null");
    }
}
