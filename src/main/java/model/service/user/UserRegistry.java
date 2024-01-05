package model.service.user;

import model.DAO.DAOPersonalInfo;
import model.entity.PersonalInfo;

public class UserRegistry implements UserRegistryInterface {
    DAOPersonalInfo db = new DAOPersonalInfo();

    public boolean firstAccess(int id, String name, String surname) {//TODO e da inserire nel metodo di registrazione!
        return db.createRegistry(id, name, surname);
    }

    public PersonalInfo getPersonalInfo(int id) {
        return db.getPersonalInfo(id);
    }

    public boolean updatePersonaInfofromId(int id, String FirstName, String LastName, String Phone,String email, String address)
    {
        return db.updatePersonalInfoAndUserFromId(id, FirstName, LastName, Phone, email, address);
    }
}
