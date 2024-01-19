package model.service.login;

public interface LoginInterface {

    /**
     * Autentica un utente verificandone i dati di accesso
     * @param email è l'email inserita
     * @param password è la password inserita
     * @return l'ID dell'utente. -1 se le credenziali sono errate
     *
     */
    int authenticate(String email, String password);

    /**
     * Permette di effettuare il reset della password inviando un PIN di verifica all'email
     * @param email è l'email inserita
     * @return il codice di verifica inviato tramite email
     */
    String sendPin(String email);

    /**
     * Resetta la password utente.
     *
     * @param email              è l'email inserita.
     * @param plainTextPassword  la nuova password dell'utente.
     * @return True se la password è stata correttamente salvata False altrimenti.
     */
    boolean resetPassword(String email, String plainTextPassword);

}
