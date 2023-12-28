package model.service.PersonalInfo;

import model.entity.PersonalInfo;

public  interface PersonalInfoDataInterface
{
    public boolean createRegistry(int id, String name, String surname);

    public PersonalInfo getPersonalInfo(int id);

    public void updatePersonaInfofromId(int id, String FirstName, String LastName, String Phone);
}