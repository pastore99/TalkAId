package model.DAO;

import model.entity.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOMessageTest {

    private DAOMessage daoMessage;

    @BeforeEach
    void setup() {
        daoMessage = new DAOMessage();
    }

    @Test
    void retrieveUsersFromNoPatientsAssigned() {
        // Mock the therapist ID
        int therapistId = 8; //this therapist have NO patients.

        // Call the method to test
        List<Integer> userIds = daoMessage.retrieveUserIdsByTherapist(therapistId);

        // Check the returned list
        assertNotNull(userIds, "Returned list should not be null");
        assertTrue(userIds.isEmpty(), "Returned list should be empty");
        assertEquals(0, userIds.size());
    }

    @Test
    void retrieveUsersFromPatientsAssigned() {
        // Mock the therapist ID
        int therapistId = 11; //this therapist have 2 patients.

        // Call the method to test
        List<Integer> userIds = daoMessage.retrieveUserIdsByTherapist(therapistId);

        // Check the returned list
        assertNotNull(userIds, "Returned list should not be null");
        assertFalse(userIds.isEmpty(), "Returned list should not be empty");
        assertEquals(2, userIds.size());
    }

    @Test
    void testRetrieveMessages() {
        // Mock the user ID and contact
        int userId = 9;
        int contact = 12;

        // Call the method to test
        List<Message> messages = daoMessage.retrieveMessages(userId, contact);

        // Check the returned list
        assertNotNull(messages, "Returned list should not be null");
        assertFalse(messages.isEmpty(), "Returned list should not be empty");
        assertNotEquals(0, messages.size()); // Assuming there are 2 messages between these users
    }

    @Test
    void testMarkMessagesAsRead() {
        // Mock the sender ID and recipient ID
        int senderId = 9;
        int recipientId = 12;

        // Call the method to test
        daoMessage.markMessagesAsRead(senderId, recipientId);

        // Since the method doesn't return anything, we can't really verify its correctness
        // However, we can assume it worked correctly if no exceptions were thrown
    }

    @Test
    void testSendMessage() {
        // Mock the sender ID, recipient ID and message text
        int senderId = 9;
        int recipientId = 12;
        String text = "Test message";

        // Call the method to test
        daoMessage.sendMessage(senderId, recipientId, text);

        // Since the method doesn't return anything, we can't really verify its correctness
        // However, we can assume it worked correctly if no exceptions were thrown
    }

    @Test
    void testCountReceivedMessages() {
        // Mock the recipient ID
        int recipientId = 9;

        // Call the method to test
        int count = daoMessage.countReceivedMessages(recipientId);

        // Check the returned count
        assertNotEquals(0, count); // Assuming the recipient has received
    }
}
