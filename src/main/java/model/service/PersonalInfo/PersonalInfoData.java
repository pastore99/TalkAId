package model.service.PersonalInfo;

import model.DAO.DAOPersonalInfo;
import model.entity.PersonalInfo;

public class PersonalInfoData implements PersonalInfoDataInterface
{
    DAOPersonalInfo daoInfo= new DAOPersonalInfo();

    public PersonalInfo getPersonalInfo(int id)
    {
        return daoInfo.getPersonalInfo(id);
    }

    public boolean createRegistry(int id, String name, String surname)
    {
        return  daoInfo.createRegistry(id, name, surname);
    }

    public boolean updatePersonaInfofromId(int id, String FirstName, String LastName, String Phone)
    {
        return daoInfo.updatePersonalInfofromId(id, FirstName, LastName, Phone);
    }
}
