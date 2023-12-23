package model.service.user;

import model.DAO.DAOPersonalInfo;
import model.entity.PersonalInfo;

public class UserRegistry implements UserRegistryInterface {
    DAOPersonalInfo db = new DAOPersonalInfo();

    public boolean firstAccess(int id, String name, String surname) {//TODO e da inserire nel metodo di registrazione!
        System.out.println("hello, this is a placeholder");
        return true; //TODO Remove it!
    }

    public PersonalInfo getPersonalInfo(int id) {
        return db.getPersonalInfo(id);
    }

}
