package model.service.user;

import model.entity.User;

/**
 * Interfaccia per la gestione dei dati dell'utente
 */
public interface UserDataInterface {

    /**
     * Permette di ricercare un utente dato il suo id
     * @param idOrEmail è l'ID od Email per ricercare un utente, univoci
     * @return una classe Utente con i suoi dati correlati
     */
    User getUser(Object idOrEmail);
    /**
     * Genera il nuovo utente
     * @param email è l'email inserita durante la registrazione
     * @param password è la password criptata
     * @param therapistId è l'eventuale valore per assegnare l'utente ad un terapista
     * @return ID della nuova utenza generata
     */
    int createUser(String email, String password, int therapistId);
    /**
     * metodo necessario per la prima registrazione al sito. Controlla la presenza o meno di account con la stessa email
     * @param email è l'email da controllare
     * @return True se può registrarsi, False altrimenti
     */
    boolean checkIfEmailExists(String email);

    /**
     * si occupa di modificare l'email dell'utente
     * @param value contiene la nuova email
     * @return True se termina con successo. False altrimenti
     */
    //boolean modifyEmail(String value);

    /**
     * si occupa di modificare il consenso al tracciamento
     * @param value contiene True o False
     * @return True se termina con successo. False altrimenti
     */
    boolean updateAnalyticsPreference(String id, Boolean value);

    /**
     * si occupa di modificare il consenso alle notifiche mail
     * @param value contiene True o False
     * @return True se termina con successo. False altrimenti
     */
    //boolean modifyEmailNotification(boolean value); //TODO

    /**
     * si occupa di modificare la fascia oraria per le notifiche
     * @param id contiene
     * @param value contiene l'orario delle notifiche espresso come 12:34-13:00
     * @return True se termina con successo. False altrimenti
     */
    boolean updateEmailTime(String id, String value);
}
