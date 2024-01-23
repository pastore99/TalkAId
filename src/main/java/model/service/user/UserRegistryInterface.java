package model.service.user;

import model.entity.PersonalInfo;

/**
 * Interfaccia per la gestione dei dati personali dell'utente
 */
public interface UserRegistryInterface {

    /**
     * Inserisce le informazioni base nell'anagrafica
     * @param id è l'id dell'utente creato in precedenza
     * @param name è il nome
     * @param surname è il cognome
     * @return True se è andato a buon fine. False altrimenti
     */
    boolean firstAccess(int id, String name, String surname);

    PersonalInfo getPersonalInfo(int id);
}
