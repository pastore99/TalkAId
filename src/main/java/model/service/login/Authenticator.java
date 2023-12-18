package model.service.login;

import model.DAO.DAOUser;
import model.entity.User;
import model.service.encryption.Encryption;

public class Authenticator implements LoginInterface {
    DAOUser db = new DAOUser();
    @Override
    public String resetPassword(String email) {
        //TODO
        return null;
    }

    @Override
    public int authenticate(String email, String password) {
        User toCheck = db.getUserByIdOrEmail(email);
        Encryption encryption = new Encryption();
        if(toCheck != null) {
            if (encryption.verifyPassword(toCheck.getPassword(), password)) {
                return toCheck.getId();
            } else {
                return -2; //le password non coincidono
            }
        }
        return -1; //utente non trovato
    }
}
