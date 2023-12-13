package model.service.license;

import model.entity.License;
import model.DAO.DAOLicense;

public class LicenseActivation implements LicenseActivationInterface {
    DAOLicense daoLicense = new DAOLicense();

    public License getLicense(String code) {
        return daoLicense.getLicenseByCode(code);
    }

    public boolean isActivable(License license) {
        if (license != null) {
            return !license.isActive();
        }
        return false;
    }

    public int isForTherapist(License license) {
        if (license != null) {
            return license.getIdUser();
        }
        return 0;
    }

    public void activate(License license, int userId) {
        daoLicense.activate(license, userId);
    }

    public void generatePin(int therapistId) {
        //TODO
        // Implement the logic to generate a new license with a 4-character pin
        // and associate it with the provided therapistId
    }

    public void generateLicense() {
        //TODO
        // Implement the logic to generate a new license with 8 characters and therapistId 0
    }
}