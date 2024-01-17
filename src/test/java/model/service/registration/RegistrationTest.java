package model.service.registration;

import model.DAO.DAOConnection;
import model.DAO.DAOLicense;
import model.DAO.DAOPersonalInfo;
import model.DAO.DAOUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationTest {

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

    /*
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