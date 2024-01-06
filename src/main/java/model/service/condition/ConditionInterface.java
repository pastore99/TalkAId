package model.service.condition;

import model.entity.Condition;

import java.util.ArrayList;

public interface ConditionInterface {
    ArrayList<Condition> getConditionsOfPatient(int id_patient);
    ArrayList<model.entity.Condition> getConditionsNOTOfPatient(int id_patient);
    boolean AddConditionPatient(int ID_condition, int ID_patient, int Severity);
    boolean RemoveConditionPatient(int ID_condition, int ID_patient);
}
