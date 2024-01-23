package model.service.user;

import model.entity.User;

/**
 * Interfaccia per la gestione dei dati dell'utente
 */
public interface UserDataInterface {

    /**
     * Permette di ricercare un utente dato il suo id
     * @param idOrEmail è l'ID o Email per ricercare un utente, univoci
     * @return una classe Utente con i suoi dati correlati
     */
    User getUser(Object idOrEmail);
    /**
     * Genera il nuovo utente
     * @param email è l'email inserita durante la registrazione
     * @param password è la password criptata
     * @param therapistId è l'eventuale valore per assegnare l'utente a un terapista
     * @return ID della nuova utenza generata
     */
    int createUser(String email, String password, int therapistId);
    /**
     * Metodo necessario per la prima registrazione al sito. Controlla la presenza o meno di account con la stessa email
     * @param email è l'email da controllare
     * @return True se può registrarsi, False altrimenti
     */
    boolean checkIfEmailExists(String email);

    /**
     * Si occupa di modificare il consenso al tracciamento
     * @param value contiene True o False
     * @return True se termina con successo. False altrimenti
     */
    boolean updateAnalyticsPreference(String id, Boolean value);

    /**
     * Si occupa di modificare la fascia oraria per le notifiche
     * @param id contiene
     * @param value contiene l'orario delle notifiche espresso come 12:34-13:00
     * @return True se termina con successo. False altrimenti
     */
    boolean updateEmailTime(String id, String value);
}
