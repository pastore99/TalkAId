package model.service.personalinfo;

import model.DAO.DAOPersonalInfo;

public class PersonalInfo {
    DAOPersonalInfo personalInfoDAO = new DAOPersonalInfo();
    public boolean createRegistry(int id, String name, String surname) {return personalInfoDAO.createRegistry(id, name, surname);}
}
