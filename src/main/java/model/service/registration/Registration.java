package model.service.registration;

import model.entity.License;
import model.service.email.EmailManager;
import model.service.encryption.Encryption;
import model.service.license.LicenseActivation;
import model.service.user.UserData;
import model.service.user.UserRegistry;

public class Registration implements RegistrationInterface {


    @Override
    public int registerNewUser(String licenseCode, String email, String password, String name, String surname) {
        License license = validateLicense(licenseCode);
        if (license != null) {
            if (isEmailExists(email)) {
                return 2; //email non valida
            }
            String hashed = encryptPassword(password);
            int theNewId = createNewUser(email, hashed, license);
            if (theNewId >= 0) {
                if (createUserPersonalInformation(theNewId, name, surname)) {
                    LicenseActivation la = new LicenseActivation();
                    la.activate(license, theNewId);
                    return 0; // no error
                }
                return 4; //non è stato possibile generare l'anagrafica
            }
            return 3; //non è stato possibile generare l'utenza

        }
        return 1; //licenza non valida
    }

    /**
     * Validates license
     */
    private License validateLicense(String licenseCode){
        LicenseActivation la = new LicenseActivation();
        License license = la.getLicense(licenseCode);
        return la.isActivable(license) ? license : null;
    }

    /**
     * Checks if an email already exists or not.
     */
    private boolean isEmailExists(String email){
        UserData ud = new UserData();
        return ud.checkIfEmailExists(email);
    }

    /**
     * Encrypts user password
     */
    private String encryptPassword(String password){
        Encryption encryption = new Encryption();
        return encryption.encryptPassword(password);
    }

    /**
     * Creates a new user.
     */
    private int createNewUser(String email, String hashed, License license){
        UserData ud = new UserData();
        LicenseActivation la = new LicenseActivation();
        return ud.createUser(email, hashed, la.isForTherapist(license));
    }

    /**
     * Creates a user personal info.
     */
    private boolean createUserPersonalInformation(int theNewId, String name, String surname){
        UserRegistry ur = new UserRegistry();
        return ur.firstAccess(theNewId, name, surname);
    }

    public boolean invitePatient(int therapistId, String patientEmail, String patientName, String patientSurname){
        UserData ud = new UserData();

        if(!ud.checkIfEmailExists(patientEmail)) {
            EmailManager tool = new EmailManager();
            LicenseActivation la = new LicenseActivation();

            String pin = la.generatePin(therapistId);
            if(pin!=null){
                String body = "Salve "+ patientSurname+ " " + patientName + "\n. il tuo logopedista ti ha invitato a TalkAID! Ecco il tuo codice per registrarti al sito: "+ pin;

                tool.sendEmail(patientEmail, "Sei stato invitato a TalkAID!", body);
                return true;
            }
        }
        return false;
    }
}

