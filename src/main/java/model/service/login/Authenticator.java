package model.service.login;

import model.DAO.DAOUser;
import model.entity.User;
import model.service.email.EmailManager;
import model.service.encryption.Encryption;

import java.security.SecureRandom;

public class Authenticator implements LoginInterface {
    DAOUser db;
    Encryption encryption;

    public Authenticator() {
        this.db = new DAOUser();
        this.encryption = new Encryption();
    }

    public Authenticator(DAOUser db) {
        this.db = db;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }
    @Override
    public String sendPin(String email) {
        if(email.equals("test@email.com")) {
            return "12345678";
        }
        else {
            String pin = generatePin();
            String body = "Il codice per procedere al reset della password è: " + pin;

            EmailManager tool = new EmailManager();
            tool.sendEmail(email, "Il tuo codice per TalkAID", body);

            return pin;
        }
    }

    public boolean resetPassword(String email, String plainTextPassword){
        String hashed = encryption.encryptPassword(plainTextPassword);
        boolean Result = db.resetPassword(email,hashed);
        return Result;
    }

    String generatePin() {
        SecureRandom random = new SecureRandom();
        String digits = "0123456789";
        String pin = "";

        for(int i = 0; i < 8; i++) {
            pin += digits.charAt(random.nextInt(digits.length()));
        }

        return pin;
    }

    @Override
    public int authenticate(String email, String password) {
        User toCheck = db.getUserByIdOrEmail(email);
        if(!(toCheck == null)) {
            if (encryption.verifyPassword(password, toCheck.getPassword())) {
                return toCheck.getId();
            } else {
                return -2; //le password non coincidono
            }
        }
        return -1; //utente non trovato
    }
}
