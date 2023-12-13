package model.service.registration;
/**
 * Interfaccia per la classe Registrazione
 */
public interface RegistrationInterface {

    /**
     * effettua i vari controlli sfruttando gli altri sottosistemi
     * @param licenseCode è il codice della licenza da validare
     * @param email è l'email inserita da ricercarne l'unicità
     * @param password è la password inserita da criptare
     * @param name è il nome utente per la sua anagrafica
     * @param surname è il cognome utente per la sua anagrafica
     * @return un codice di errore in base alla casistica
     */
    int register(String licenseCode, String email, String password, String name, String surname);

    /**
     * cambia la password da una vecchia a quella nuova
     * @param email è l'email inserita dell'account
     * @param oldpw è la password inserita da controllare la validità e sostituire
     * @param newpw è la nuova password
     * @return True se è stata cambiata con successo. False altrimenti
     */
    boolean resetFromOldPassword(String email, String oldpw, String newpw);

    /**
     * avvia il recupero password tramite email
     * @param email è l'email inserita dell'account
     * @return True se è stata cambiata con successo. False altrimenti
     */
    boolean resetPassword(String email);
}