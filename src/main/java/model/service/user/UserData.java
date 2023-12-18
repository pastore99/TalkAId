package model.service.user;

import model.DAO.DAOUser;

public class UserData /*implements UserDataInterface */{
    DAOUser daoUser = new DAOUser();

    public boolean checkIfEmailExists(String email) {
        return daoUser.checkIfEmailExists(email);
    }

    public int createUser(String email, String password, int therapistId) {
        return daoUser.createUser(email, password, therapistId);
    }
}
