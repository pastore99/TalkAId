package model.service.user;

import model.entity.User;

/**
 * Interfaccia per la gestione dei dati dell'utente
 */
public interface UserDataInterface {

    /**
     * Permette di ricercare un utente dato il suo id
     * @param userId è il codice univoco per ricercare un utente
     * @return una classe Utente con i suoi dati correlati
     */
    User getUser(int userId);
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
    boolean modifyEmail(String value);

    /**
     * si occupa di modificare il consenso al tracciamento
     * @param value contiene True o False
     * @return True se termina con successo. False altrimenti
     */
    boolean modifyAnalytics(boolean value);

    /**
     * si occupa di modificare il consenso alle notifiche mail
     * @param value contiene True o False
     * @return True se termina con successo. False altrimenti
     */
    boolean modifyEmailNotification(boolean value);

    /**
     * si occupa di modificare la fascia oraria per le notifiche
     * @param time contiene l'orario delle notifiche espresso come 12:34
     * @return True se termina con successo. False altrimenti
     */
    boolean modifyTime(String time);
}
