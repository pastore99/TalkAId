package model.service.condition;

import model.DAO.DAOCondition;

import java.util.ArrayList;

public class Condition {

    DAOCondition c=new DAOCondition();

    public ArrayList<model.entity.Condition> getConditionsOfPatient(int id_patient) { return c.getConditionsOfPatient(id_patient);}

    public ArrayList<model.entity.Condition> getConditionsNOTOfPatient(int id_patient) { return c.getConditionsNOTOfPatient(id_patient);}

    public boolean AddConditionPatient(int ID_condition, int ID_patient, int Severity) {return  c.AddConditionPatient(ID_condition, ID_patient,Severity);}

    public boolean RemoveConditionPatient(int ID_condition, int ID_patient) {return   c.RemoveConditionPatient(ID_condition,ID_patient);}
}
