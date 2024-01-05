package model.service.user;

import model.DAO.DAOPersonalInfo;
import model.DAO.DAOUser;
import model.entity.PersonalInfo;

import java.sql.Connection;

public class UserRegistry implements UserRegistryInterface {
    DAOPersonalInfo db;
    public UserRegistry() {
        this.db = new DAOPersonalInfo();}
    public UserRegistry(Connection connection) {
        this.db = new DAOPersonalInfo(connection);
    }
    public UserRegistry(DAOPersonalInfo db) {
        this.db = db;
    }

    public boolean firstAccess(int id, String name, String surname) {//TODO e da inserire nel metodo di registrazione!
        return db.createRegistry(id, name, surname);
    }

    public PersonalInfo getPersonalInfo(int id) {
        return db.getPersonalInfo(id);
    }

    public boolean updatePersonaInfo(int id, String FirstName, String LastName, String Phone, String email, String address)
    {
        return db.updatePersonalInfoAndUserfromId(id, FirstName, LastName, Phone, email, address);
    }
}
