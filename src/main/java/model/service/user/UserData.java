package model.service.user;

import model.DAO.DAOUser;
import model.entity.User;

public class UserData /*implements UserDataInterface */{
    DAOUser daoUser = new DAOUser();

    public boolean checkIfEmailExists(String email) {
        return daoUser.checkIfEmailExists(email);
    }

    public int createUser(String email, String password, int therapistId) {
        return daoUser.createUser(email, password, therapistId);
    }

    public User getUserByIdOrEmail(Object idOrEmail) {
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

    public void updateUser(int idUser, String Email, String address)
    {
        daoUser.updateUser(idUser, Email, address);
    }

    public boolean ControlPassword(int id, String Password)
    {
        daoUser.ControlPassword(id, Password);
    }
}
