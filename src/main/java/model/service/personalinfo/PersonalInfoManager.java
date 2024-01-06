package model.service.personalinfo;

import model.DAO.DAOPersonalInfo;
import model.entity.PersonalInfo;

public class PersonalInfoManager {
    DAOPersonalInfo personalInfoDAO = new DAOPersonalInfo();
    public boolean createRegistry(int id, String name, String surname) {return personalInfoDAO.createRegistry(id, name, surname);}
    public PersonalInfo getPersonalInfoById(int id) {return personalInfoDAO.getPersonalInfo(id); }
}
