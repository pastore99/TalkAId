package model.service.user;

import model.DAO.DAOUser;
import model.entity.User;
import model.entity.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questa classe provvede alle funzionalità per gestire le informazioni degli utenti.
 */
public class UserData implements UserDataInterface {
    DAOUser db;
    public UserData() {
        this.db = new DAOUser();}

    public UserData(DAOUser db) {
        this.db = db;
    }

    public boolean checkIfEmailExists(String email) {
        return db.checkIfEmailExists(email);
    }

    public int createUser(String email, String password, int therapistId) {
        return db.createUser(email, password, therapistId);
    }

    public User getUser(Object idOrEmail) {
        return db.getUserByIdOrEmail(idOrEmail);
    }

    public boolean isTherapist(User user){
        return user.getIdTherapist() == 0;
    }

    public boolean updateAnalyticsPreference(String id, Boolean value) {
        return db.updateAnalyticsPreference(id, value);
    }

    public boolean updateEmailTime(String id, String value) {
        return db.updateEmailTime(id, value);
    }

    public ArrayList<UserInfo> getUsersAndPersonalInfoByIdTherapist(int ID_Therapist) {return db.getUsersAndPersonalInfoByIdTherapist(ID_Therapist);}

    public HashMap<Integer, UserInfo> getMappedUserInfoByIdTherapist(int therapistId){ return db.getMapUsersAndPersonalInfoByIdTherapist(therapistId);}
}
