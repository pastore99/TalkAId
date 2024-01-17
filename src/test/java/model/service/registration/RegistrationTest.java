package model.service.registration;

import model.entity.License;
import model.service.license.LicenseActivation;
import model.service.user.UserData;
import model.service.user.UserRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegistrationTest {

    @Mock
    private Registration registration;

    private LicenseActivation la;
    private UserData ud;
    private UserRegistry ur;
    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);
        la = mock(LicenseActivation.class);
        ud = mock(UserData.class);
        ur = mock(UserRegistry.class);
        registration = new Registration(la, ud, ur);
    }

    @Test
    void registerNewTherapist() {
        License license = new License();
        license.setSequence("12345678");
        license.setActive(false);
        //when(registration.validateLicense(anyString())).thenReturn(license);
        when(la.getLicense(anyString())).thenReturn(license);
        when(la.isActivable(license)).thenReturn(true);
        when(la.isForTherapist(license)).thenReturn(0);
        when(registration.isEmailExists(anyString())).thenReturn(false);
        //when(registration.createNewUser(anyString(), anyString(), license)).thenReturn(1);
        when(ud.createUser("test@email.com", "StrongPwd1!23", 0)).thenReturn(55);

        when(ur.firstAccess(55, "Test", "User")).thenReturn(true);
        when(registration.createUserPersonalInformation(anyInt(), anyString(), anyString())).thenReturn(true);

        int result = registration.registerNewUser("12345678", "test@email.com", "StrongPwd1!23", "Test", "User");
        assertEquals(0, result, "New Therapist should be registered successfully.");
    }

    @Test
    void registerNewPatient() {
        License license = new License();
        license.setSequence("1234");
        license.setActive(false);
        //when(registration.validateLicense(anyString())).thenReturn(license);
        when(la.getLicense(anyString())).thenReturn(license);
        when(la.isActivable(license)).thenReturn(true);
        when(la.isForTherapist(license)).thenReturn(99);
        when(registration.isEmailExists(anyString())).thenReturn(false);
        //when(registration.createNewUser(anyString(), anyString(), license)).thenReturn(1);
        when(ud.createUser("test@email.com", "StrongPwd1!23", 0)).thenReturn(55);

        when(ur.firstAccess(55, "Test", "User")).thenReturn(true);
        when(registration.createUserPersonalInformation(anyInt(), anyString(), anyString())).thenReturn(true);

        int result = registration.registerNewUser("1234", "test@email.com", "StrongPwd1!23", "Test", "User");
        assertEquals(0, result, "New Patient should be registered successfully.");
    }


    @Test
    void invalidLicenseCodeTest() {
        License license = new License();
        license.setSequence("1234");
        license.setActive(true);
        //when(registration.validateLicense(anyString())).thenReturn(license);
        when(la.getLicense(anyString())).thenReturn(license);
        when(la.isActivable(license)).thenReturn(false);
        when(la.isForTherapist(license)).thenReturn(99);
        when(registration.isEmailExists(anyString())).thenReturn(false);
        //when(registration.createNewUser(anyString(), anyString(), license)).thenReturn(1);
        when(ud.createUser("test@email.com", "StrongPwd1!23", 0)).thenReturn(55);

        when(ur.firstAccess(55, "Test", "User")).thenReturn(true);
        when(registration.createUserPersonalInformation(anyInt(), anyString(), anyString())).thenReturn(true);

        int result = registration.registerNewUser("1234", "test@email.com", "StrongPwd1!23", "Test", "User");
        assertEquals(1, result, "License already used or not valid.");
    }

    //Unit Test Invalid
    @Test
    void invalidEmailTest() {
        License license = new License();
        license.setSequence("1234");
        license.setActive(true);
        //when(registration.validateLicense(anyString())).thenReturn(license);
        when(la.getLicense(anyString())).thenReturn(license);
        when(la.isActivable(license)).thenReturn(true);
        when(la.isForTherapist(license)).thenReturn(99);
        when(registration.isEmailExists(anyString())).thenReturn(true);
        //when(registration.createNewUser(anyString(), anyString(), license)).thenReturn(1);
        when(ud.createUser("test@email.com", "StrongPwd1!23", 0)).thenReturn(55);

        when(ur.firstAccess(55, "Test", "User")).thenReturn(true);
        when(registration.createUserPersonalInformation(anyInt(), anyString(), anyString())).thenReturn(true);

        int result = registration.registerNewUser("1234", "test@email.com", "StrongPwd1!23", "Test", "User");
        assertEquals(2, result, "Email already taken.");
    }

    @Test
    void ErrorGeneratingUser() {
        License license = new License();
        license.setSequence("12345678");
        license.setActive(false);
        //when(registration.validateLicense(anyString())).thenReturn(license);
        when(la.getLicense(anyString())).thenReturn(license);
        when(la.isActivable(license)).thenReturn(true);
        when(la.isForTherapist(license)).thenReturn(0);
        when(registration.isEmailExists(anyString())).thenReturn(false);
        //when(registration.createNewUser(anyString(), anyString(), license)).thenReturn(1);
        when(ud.createUser("test@email.com", "StrongPwd1!23", 0)).thenReturn(-1);

        //when(ur.firstAccess(55, "Test", "User")).thenReturn(true);
        //when(registration.createUserPersonalInformation(anyInt(), anyString(), anyString())).thenReturn(false);

        int result = registration.registerNewUser("12345678", "test@email.com", "StrongPwd1!23", "Test", "User");
        assertEquals(4, result, "New Therapist should be registered successfully.");
    }

    /*
    private Registration registration;

    @BeforeEach
    void setUp() {
        registration = new Registration();
    }

    //Unit Test Valido
    @Test
    void registerNewTherapist() {
        // Prepare test data
        String licenseCode = new DAOLicense().generateLicense();
        System.out.println(licenseCode);
        String email = "test@test.com";
        String password = "testPassword";
        String name = "testName";
        String surname = "testSurname";

        // Run registerNewUser method
        int result = registration.registerNewUser(licenseCode, email, password, name, surname);

        // Assert expected result (Success scenario -> result should be 0)
        assertEquals(0, result, "New user should be registered successfully.");

        new DAOLicense().deleteLicense(licenseCode);
        new DAOPersonalInfo().deleteRegistry(new DAOUser().getUserByIdOrEmail(email).getId());
    }

    //Unit Test Valido
    @Test
    void registerNewPatient() {
        // Prepare test data
        String licenseCode = new DAOLicense().generateInvitation(999);
        String email = "test2@test.com";
        String password = "testPassword";
        String name = "testName";
        String surname = "testSurname";

        // Run registerNewUser method
        int result = registration.registerNewUser(licenseCode, email, password, name, surname);

        // Assert expected result (Success scenario -> result should be 0)
        assertEquals(0, result, "New user should be registered successfully.");
        new DAOLicense().deleteLicense(licenseCode);
        new DAOPersonalInfo().deleteRegistry(new DAOUser().getUserByIdOrEmail(email).getId());
        new DAOUser().deleteUserByIdOrEmail(email);
    }

    //Unit Test Valido
    @Test
    void invalidLicenseTest() {
        // Prepare test data
        String licenseCode = "notWorking";
        String email = "test@test.com";
        String password = "testPassword";
        String name = "testName";
        String surname = "testSurname";

        // Run registerNewUser method
        int result = registration.registerNewUser(licenseCode, email, password, name, surname);

        // Assert expected result (Success scenario -> result should be 0)
        assertEquals(1, result, "Can't login succesfully because of the wrong LicenseCode.");


    }

    //Unit Test Valido
    @Test
    void invalidEmailTest() {
        // Prepare test data
        String licenseCode = new DAOLicense().generateLicense();
        String email = "doc1@example.com";
        String password = "testPassword";
        String name = "testName";
        String surname = "testSurname";

        // Run registerNewUser method
        int result = registration.registerNewUser(licenseCode, email, password, name, surname);

        // Assert expected result (Success scenario -> result should be 0)
        assertEquals(2, result, "Can't login succesfully because of the wrong email.");

        new DAOLicense().deleteLicense(licenseCode);
    }
    */
}