package model.service.user;

import model.DAO.DAOUser;
import model.entity.User;

import java.sql.Connection;

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

    public String updateUser(int idUser, String Email, String address)
    {
        return db.updateUser(idUser, Email, address);
    }

}
