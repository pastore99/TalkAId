package model.service.message;

import model.entity.Message;

import java.util.List;

public interface MessageManagerInterface {

    /**
     * Marks the messages between a sender and a recipient as read in the database.
     * This method updates the 'Read' field of the messages from the sender to the recipient to TRUE.
     *
     * @param senderId    the ID of the sender
     * @param recipientId the ID of the recipient
     */
    void markMessagesAsRead(int senderId, int recipientId);

    /**
     * Counts the number of received messages for a given recipient.
     *
     * @param recipientId the ID of the recipient
     * @return the number of received messages for the recipient
     */
    int countReceivedMessages(int recipientId);

    /**
     * Sends a message from a sender to a recipient by inserting it into the database.
     *
     * @param sender       the ID of the sender
     * @param recipientId  the ID of the recipient
     * @param text         the content of the message
     */
    void sendMessage(int sender, int recipientId, String text);

    /**
     * Retrieves the messages between a user and a contact.
     *
     * @param userId  the ID of the user
     * @param contact the ID of the contact
     * @return the list of messages between the user and the contact
     */
    List<Message> retrieveMessages(int userId, int contact);

    /**
     * Retrieves the number of unread messages for a specific conversation between a user and a contact.
     *
     * @param userId  the ID of the user
     * @param contact the ID of the contact
     * @return the number of unread messages for the conversation
     */
    int getUnreadMessagesForConversation(int userId, int contact);

    /**
     * Retrieves all the contacts of a user.
     *
     * @param userId the ID of the user
     * @return a list of integers representing the IDs of the contacts
     */
    List<Integer> retrieveAllTheContacts(int userId);
}
