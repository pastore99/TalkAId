package model.service.user;

import model.DAO.DAOUtente;

public class UserData implements UserDataInterface {
    DAOUtente daoUtente = new DAOUtente();

    public boolean checkIfEmailExists(String email) {
        return daoUtente.checkIfEmailExists(email);
    }

    public int createUser(String email, String password, int therapistId) {
        return daoUtente.createUser(email, password, therapistId);
    }
}
