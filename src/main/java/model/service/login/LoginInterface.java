package model.service.login;

public interface LoginInterface {

    /**
     * autentica un utente verificandone i dati di accesso
     * @param email è l'email inserita
     * @param password è la password inserita
     * @return l'ID dell'utente. -1 se le credenziali sono errate
     *
     */
    public int authenticate(String email, String password);

    /**
     * permette di effettuare il reset della password
     * @param email è l'email inserita
     * @return il codice di verifica inviato tramite email
     */
    public String resetPassword(String email);

}
