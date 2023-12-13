package model.service.registration;
import model.service.encryption.Encryption;
import model.service.license.LicenseActivation;
import model.entity.License;
import model.service.user.UserData;
import model.service.user.UserRegistry;

public class Registration implements RegistrationInterface {

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
                int id = ud.createUser(email, hashed, la.isForTherapist(license));

                if(id >= 0) {
                    if(ur.firstAccess(id, name, surname)) {
                        return 0;
                    }
                    return 4;
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
