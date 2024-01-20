package model.service.email;

public interface EmailManagerInterface {

    /**
     * Permette l'invio di un'email specificando l'indirizzo, il titolo e il corpo.
     *
     * @param toAddress l'indirizzo email
     * @param subject   il titolo
     * @param body      il corpo
     */
    void sendEmail(String toAddress, String subject, String body);
}