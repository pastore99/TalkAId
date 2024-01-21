package model.service.condition;

import model.entity.Condition;
import java.util.ArrayList;

/**
 * Interfaccia per la gestione delle patologie.
 */
public interface ConditionInterface {

    /**
     * Restituisce le condizioni che sono state associate al paziente.
     *
     * @param id_patient l'ID del paziente
     * @return un ArrayList of Condition
     */
    ArrayList<Condition> getConditionsOfPatient(int id_patient);

    /**
     * Restituisce le condizioni che non sono state associate al paziente.
     *
     * @param id_patient l'ID del paziente
     * @return un ArrayList di Condition objects
     */
    ArrayList<model.entity.Condition> getConditionsNOTOfPatient(int id_patient);

    /**
     * Rimuove una condizione dal paziente selezionato.
     *
     * @param ID_condition  l'ID della condizione del paziente da aggiungere
     * @param ID_patient l'ID del paziente
     * @param Severity il livello di gravità della condizione
     * @return true if se è correttamente aggiunta, false altrimenti
     */
    boolean AddConditionPatient(int ID_condition, int ID_patient, int Severity);

    /**
     * Rimuove una condizione dal paziente selezionato.
     *
     * @param ID_condition l'ID della condizione del paziente da rimuoverere
     * @param ID_patient l'ID del paziente dal quale rimuoverla
     * @return true se è correttamente rimossa, false altrimenti
     */
    boolean RemoveConditionPatient(int ID_condition, int ID_patient);
}
