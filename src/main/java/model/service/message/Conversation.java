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

    public Conversation(int userId){
        this.userId = userId;
        List<Integer> contacts= retrieveAllTheContacts(userId);
        forEachContactRetrieveTheMessages(userId, contacts);
        updateUnreadCounter();
    }

    private void updateUnreadCounter() {
        unreadMessages = 0;
        for (List<Message> messages : conversations.values()) {
            for (Message message : messages) {
                if (message.getRecipient() == userId && !message.isRead()) {
                    unreadMessages++;
                }
            }
        }
    }

    private void forEachContactRetrieveTheMessages(int userId, List<Integer> contacts) {
        for (int contact : contacts) {
            conversations.put(contact, new ArrayList<>());
            conversations.get(contact).addAll(db.retrieveMessages(userId, contact));
        }
    }

    private List<Integer> retrieveAllTheContacts(int userId){
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
