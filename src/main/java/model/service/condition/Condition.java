package model.service.condition;

import model.DAO.DAOCondition;

import java.util.ArrayList;

public class Condition {

    DAOCondition c=new DAOCondition();

    public ArrayList<model.entity.Condition> getConditionsOfPatient(int id_patient) { return c.getConditionsOfPatient(id_patient);}

    public ArrayList<model.entity.Condition> getConditionsNOTOfPatient(int id_patient) { return c.getConditionsNOTOfPatient(id_patient);}
}
