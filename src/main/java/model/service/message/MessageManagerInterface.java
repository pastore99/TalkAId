package model.service.message;

import model.entity.Message;

import java.util.List;

/**
 * Interfaccia per la gestione dei messaggi
 */
public interface MessageManagerInterface {

    /**
     * Contrassegna i messaggi tra un mittente e un destinatario come letti nel database.
     * Questo metodo aggiorna il campo 'Letto' dei messaggi dal mittente al destinatario su VERO.
     *
     * @param senderId    the ID of the sender
     * @param recipientId the ID of the recipient
     */
    void markMessagesAsRead(int senderId, int recipientId);

    /**
     * Conta il numero di messaggi ricevuti.
     *
     * @param recipientId the ID of the recipient
     * @return the number of received messages for the recipient
     */
    int countReceivedMessages(int recipientId);

    /**
     * Invia un messaggio salvandolo su un database.
     *
     * @param sender       the ID of the sender
     * @param recipientId  the ID of the recipient
     * @param text         the content of the message
     */
    void sendMessage(int sender, int recipientId, String text);

    /**
     * Ottiene la lista dei messaggi in una conversazione.
     *
     * @param userId  the ID of the user
     * @param contact the ID of the contact
     * @return the list of messages between the user and the contact
     */
    List<Message> retrieveMessages(int userId, int contact);

    /**
     * Conta il numero di messaggi non letti in una conversazione.
     *
     * @param userId  the ID of the user
     * @param contact the ID of the contact
     * @return the number of unread messages for the conversation
     */
    int getUnreadMessagesForConversation(int userId, int contact);

    /**
     * Restituisce tutti i contatti di un utente.
     *
     * @param userId the ID of the user
     * @return a list of integers representing the IDs of the contacts
     */
    List<Integer> retrieveAllTheContacts(int userId);
}
