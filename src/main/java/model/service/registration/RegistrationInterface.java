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
}