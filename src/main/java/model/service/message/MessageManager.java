package model.service.message;

import model.DAO.DAOMessage;
import model.entity.Message;
import model.entity.User;
import model.service.user.UserData;

import java.util.ArrayList;
import java.util.List;

public class MessageManager implements MessageManagerInterface {
    public MessageManager() {
        this.db = new DAOMessage();
        this.db2 = new UserData();
    }
    DAOMessage db;
    UserData db2;

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
        User user = db2.getUser(userId);
        if(db2.isTherapist(user)) {
            contacts.addAll(db.retrieveUserIdsByTherapist(userId));
        }
        else { //se è un paziente
            contacts.add(user.getIdTherapist());
        }
        contacts.add(0); //Notifications
        return contacts;
    }
}
