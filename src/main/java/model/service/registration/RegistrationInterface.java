package model.service.registration;
/**
 * Interfaccia per la classe Registrazione
 */
public interface RegistrationInterface {

    /**
     * Effettua i vari controlli sfruttando gli altri sottosistemi
     * @param licenseCode è il codice della licenza da validare
     * @param email è l'email inserita da ricercarne l'unicità
     * @param password è la password inserita da criptare
     * @param name è il nome utente per la sua anagrafica
     * @param surname è il cognome utente per la sua anagrafica
     * @return un codice di errore in base alla casistica
     *      *         0 - No error.
     *      *         1 - Invalid license.
     *      *         2 - Invalid email.
     *      *         3 - Unable to create user.
     *      *         4 - Unable to generate personal info.
     */
    int registerNewUser(String licenseCode, String email, String password, String name, String surname);
}