package model.entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the Message class
        Message message = new Message();

        // Set values using setter methods
        int idMessage = 1;
        int sender = 2;
        int recipient = 3;
        boolean read = true;
        String body = "Test Body";
        Timestamp sent = Timestamp.valueOf("2022-01-01 12:00:00");

        message.setIdMessage(idMessage);
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setRead(read);
        message.setBody(body);
        message.setSent(sent);

        // Test getter methods
        assertEquals(idMessage, message.getIdMessage(), "getIdMessage() should return the correct value");
        assertEquals(sender, message.getSender(), "getSender() should return the correct value");
        assertEquals(recipient, message.getRecipient(), "getRecipient() should return the correct value");
        assertEquals(read, message.isRead(), "isRead() should return the correct value");
        assertEquals(body, message.getBody(), "getBody() should return the correct value");
        assertEquals(sent, message.getSent(), "getSent() should return the correct value");
    }

    @Test
    void testToString() {
        // Create an instance of the Message class
        Message message = new Message();

        // Call toString method
        String result = message.toString();

        // Add more assertions based on the expected output of toString
        assertNotNull(result, "toString() result should not be null");
    }

    @Test
    void testNotNull() {
        // Create an instance of the Message class
        Message message = new Message();

        // Test that the object is not null
        assertNotNull(message, "Message object should not be null");
    }
}
