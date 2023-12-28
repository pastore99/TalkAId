package model.service.user;

import model.DAO.DAOUser;
import model.entity.User;
import model.entity.UserInfo;

import java.util.ArrayList;

public class UserData /*implements UserDataInterface */{
    DAOUser daoUser = new DAOUser();

    public boolean checkIfEmailExists(String email) {
        return daoUser.checkIfEmailExists(email);
    }

    public int createUser(String email, String password, int therapistId) { return daoUser.createUser(email, password, therapistId);}
    public User getUserByIdOrEmail(Object idOrEmail){return daoUser.getUserByIdOrEmail(idOrEmail);}

    public ArrayList<User> getUsersByIdTherapist(int ID_Therapist ){return daoUser.getUsersByIdTherapist(ID_Therapist);}
    public ArrayList<UserInfo> getUsersAndPersonalInfoByIdTherapist(int ID_Therapist) {return daoUser.getUsersAndPersonalInfoByIdTherapist(ID_Therapist);}
}
