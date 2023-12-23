package model.service.registration;
import model.service.encryption.Encryption;
import model.service.license.LicenseActivation;
import model.entity.License;
import model.service.user.UserData;
import model.service.user.UserRegistry;

public class Registration implements RegistrationInterface {

    /**
     * Registers a user with the provided information.
     *
     * @param licenseCode The license code to validate the license.
     * @param email The email address of the user.
     * @param password The password for the user.
     * @param name The name of the user.
     * @param surname The surname of the user.
     * @return An integer representing the error code based on the following cases:
     *         0 - No error.
     *         1 - Invalid license.
     *         2 - Invalid email.
     *         3 - Unable to create user.
     *         4 - Unable to generate personal info.
     */
    public int register(String licenseCode, String email, String password, String name, String surname) {
        LicenseActivation la = new LicenseActivation();
        UserData ud = new UserData();
        UserRegistry ur = new UserRegistry();
        License license;

        license = la.getLicense(licenseCode);
        if(la.isActivable(license)) {
            if(!ud.checkIfEmailExists(email)) {
                Encryption encryption = new Encryption();
                String hashed = encryption.encryptPassword(password);
                int theNewId = ud.createUser(email, hashed, la.isForTherapist(license));

                if(theNewId >= 0) {
                    if(ur.firstAccess(theNewId, name, surname)) {
                        return 0; //nessun errore
                    }
                    return 4; //non è stato possibile generare l'anagrafica
                }
                return 3; //non è stato possibile generare l'utenza

            }
            return 2; //email non valida
        }
        return 1; //licenza non valida

    }

    public boolean resetFromOldPassword(String email, String oldpw, String newpw) {
        return false;
    }

    public boolean resetPassword(String email) {
        return false;
    }
}
