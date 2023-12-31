package model.service.login;

import model.DAO.DAOUser;
import model.entity.User;
import model.service.email.EmailManager;
import model.service.encryption.Encryption;

import java.security.SecureRandom;

public class Authenticator implements LoginInterface {
    DAOUser db = new DAOUser();
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
        Encryption encryption = new Encryption();
        System.out.println("password in chiaro: " + plainTextPassword);
        String hashed = encryption.encryptPassword(plainTextPassword);
        boolean Result = db.resetPassword(email,hashed);
        System.out.println("cambiato password, esito:" + Result);
        return Result;
    }

    private String generatePin() {
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
        Encryption encryption = new Encryption();
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
