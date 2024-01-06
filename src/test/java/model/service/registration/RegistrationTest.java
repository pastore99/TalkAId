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

    boolean deleteLastLicense() {
        Statement statement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            // Get database connection
            connection = DAOConnection.getConnection();
            connection.setAutoCommit(false); // start transaction

            statement = connection.createStatement();
            // Get the expiration date of the latest license
            resultSet = statement.executeQuery("SELECT MAX(ExpirationDate) AS latest_exp_date FROM license");
            if(resultSet.next()) {
                Date latestDate = resultSet.getDate("latest_exp_date");
                // Prepare DELETE statement with latest expiration date
                int rowsAffected = statement.executeUpdate("DELETE FROM license WHERE ExpirationDate = '"+latestDate+"'");
                connection.commit(); // end transaction
                return rowsAffected > 0;
            }
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } finally {
            try {
                if (statement != null) statement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
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
        System.out.println("l'ho cancellato l'user?: " + new DAOUser().deleteUserByIdOrEmail(email));
    }

    @Test
    void registerNewPatient() {
        // Prepare test data
        String licenseCode = new DAOLicense().generateInvitation(999);
        System.out.println(licenseCode);
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
        assertEquals(2, result, "Can't login succesfully because of the wrong LicenseCode.");

        new DAOLicense().deleteLicense(licenseCode);
    }

    @Test
    void invitePatient() {
        boolean result = registration.invitePatient(9, "test@test.com", "patientName", "patientSurname");
        assertEquals(true, result, "Patient should be invited successfully.");
        deleteLastLicense();
    }

    @Test
    void dontInvitePatient() {
        boolean result = registration.invitePatient(9, "doc1@example.com", "patientName", "patientSurname");
        assertEquals(false, result, "Patient should NOT be invited successfully.");
    }
}