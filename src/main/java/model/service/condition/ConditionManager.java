package model.service.condition;

import model.DAO.DAOCondition;
import model.entity.Condition;

import java.util.ArrayList;

public class ConditionManager implements ConditionInterface {
    DAOCondition c=new DAOCondition();
    public ArrayList<Condition> getConditionsOfPatient(int id_patient) { return c.getConditionsOfPatient(id_patient);}
    public ArrayList<Condition> getConditionsNOTOfPatient(int id_patient) { return c.getConditionsNOTOfPatient(id_patient);}
    public boolean AddConditionPatient(int ID_condition, int ID_patient, int Severity) {return  c.AddConditionPatient(ID_condition, ID_patient,Severity);}
    public boolean RemoveConditionPatient(int ID_condition, int ID_patient) {return   c.RemoveConditionPatient(ID_condition,ID_patient);}
}
