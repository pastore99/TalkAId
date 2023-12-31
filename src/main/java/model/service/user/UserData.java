package model.service.user;

import model.DAO.DAOUser;
import model.entity.User;

public class UserData implements UserDataInterface {
    DAOUser daoUser = new DAOUser();

    public boolean checkIfEmailExists(String email) {
        return daoUser.checkIfEmailExists(email);
    }

    public int createUser(String email, String password, int therapistId) {
        return daoUser.createUser(email, password, therapistId);
    }

    public User getUser(Object idOrEmail) {
        return daoUser.getUserByIdOrEmail(idOrEmail);
    }

    public boolean isTherapist(User user){
        return user.getIdTherapist() == 0;
    }

    public boolean updateAnalyticsPreference(String id, Boolean value) {
        return daoUser.updateAnalyticsPreference(id, value);
    }

    public boolean updateEmailTime(String id, String value) {
        return daoUser.updateEmailTime(id, value);
    }
}
