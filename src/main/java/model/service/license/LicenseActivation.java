package model.service.license;

import model.DAO.DAOLicense;
import model.entity.License;

/**
 * Questa classe provvede alle funzionalità per gestire le licenze.
 */
public class LicenseActivation implements LicenseActivationInterface {
    DAOLicense daoLicense;

    public LicenseActivation() {
        this.daoLicense = new DAOLicense();
    }

    public LicenseActivation(DAOLicense daoLicense) {
        this.daoLicense = daoLicense;
    }

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
        if (license != null && license.getSequence().length()==4) {
            return license.getIdUser();
        }
        return 0;
    }

    public void activate(License license, int userId) {
        daoLicense.activate(license, userId);
    }

    public String generatePin(int therapistId) {
        return daoLicense.generateInvitation(therapistId);
    }

    public String generateLicense() {
        return daoLicense.generateLicense();
    }
}