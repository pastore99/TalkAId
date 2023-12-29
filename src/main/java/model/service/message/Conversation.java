package model.service.message;

import model.DAO.DAOMessage;
import model.entity.Message;
import model.entity.User;
import model.service.user.UserData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conversation {
    private int userId;
    private int unreadMessages;
    private final Map<Integer, List<Message>> conversations = new HashMap<>(); //Integer è l'ID dell'Utente con cui chatta. La lista dei messaggi è la lista dei messaggi con quell'utente
    DAOMessage db = new DAOMessage();

    public int getUserId() {
        return userId;
    }


    public int getUnreadMessagesCounter() {
        return unreadMessages;
    }

    public Map<Integer, List<Message>> getConversations() {
        return conversations;
    }

    public Conversation(){}

    public void markMessagesAsRead(int senderId, int recipientId) {
        db.markMessagesAsRead(senderId, recipientId);
    }

    public int countReceivedMessages(int recipientId) {
        return db.countReceivedMessages(recipientId);
    }

    public void sendMessage(int sender, int recipientId, String text) {
        db.sendMessage(sender, recipientId, text);
    }
    public List<Message> retrieveMessages(int userId, int contact) {
        return db.retrieveMessages(userId, contact);
    }
    public int getUnreadMessagesForConversation(int userId, int contact){
        int unreadCounter = 0;
        List<Message> messages = db.retrieveMessages(userId, contact);
        for (Message message : messages) {
            if (message.getRecipient() == userId && !message.isRead()) {
                unreadCounter++;
            }
        }
        return unreadCounter;
    }

    public List<Integer> retrieveAllTheContacts(int userId){
        List<Integer> contacts = new ArrayList<>();
        UserData check = new UserData();
        User user = check.getUserByIdOrEmail(userId);
        if(check.isTherapist(user)) {
            contacts.addAll(db.retrieveUserIdsByTherapist(userId));
        }
        else { //se è un paziente
            contacts.add(user.getIdTherapist());
        }
        contacts.add(0); //Notifications
        return contacts;
    }
}
