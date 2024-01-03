package model.DAO;

import model.entity.License;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DAOLicenseTest {

    private DAOLicense daoLicense;
    private String generatedLicense;
    private String generatedInvitation;

    @BeforeEach
    void setup() {
        daoLicense = new DAOLicense();
    }

    @Test
    void testGetLicenseByCode() {
        // Mock the license code
        String code = "0D47AB8F";

        // Call the method to test
        License license = daoLicense.getLicenseByCode(code);

        // Check the returned license
        assertNotNull(license, "License should not be null");
        assertEquals(code, license.getSequence(), "Sequence should match");
        // Add more assertions based on your specific requirements
    }

    @Test
    void testGenerateLicense() {
        // Call the method to generate a license
        generatedLicense = daoLicense.generateLicense();

        // Check if the generated license sequence is not null
        assertNotNull(generatedLicense, "Generated license sequence should not be null");
        assertEquals(8, generatedLicense.length(), "Generated license sequence should have the specified length");

        boolean result1 = daoLicense.deleteLicense(generatedLicense);
        assertTrue(result1, "The license should be deleted");

        boolean result3 = daoLicense.deleteLicense(generatedLicense);
        assertFalse(result3, "The license can't be deleted");
    }

    @Test
    void testGenerateInvitation() {
        // Mock therapist ID
        int therapistId = 123;

        // Call the method to generate an invitation
        generatedInvitation = daoLicense.generateInvitation(therapistId);

        // Check if the generated invitation sequence is not null
        assertNotNull(generatedInvitation, "Generated invitation sequence should not be null");
        assertEquals(4, generatedInvitation.length(), "Generated invitation sequence should have the specified length");
        // Additional assertions if needed

        boolean result2 = daoLicense.deleteLicense(generatedInvitation);
        assertTrue(result2, "The invitation should be deleted");


        boolean result4 = daoLicense.deleteLicense(generatedInvitation);
        assertFalse(result4, "The invitation can't be deleted");
    }


    @Test
    void testActivateLicense() {
        // Mock the license and user ID
        License license = new License();
        license.setSequence("BDC3");
        license.setIdUser(4);
        license.setExpirationDate(java.sql.Date.valueOf("2024-06-27"));
        license.setActive(true);

        int userId = 4;

        // Call the method to test
        daoLicense.activate(license, userId);

        // After activation, let's retrieve the updated license
        License updatedLicense = daoLicense.getLicenseByCode(license.getSequence());

        // Check the updated license
        assertNotNull(updatedLicense, "Updated license should not be null");
        assertEquals(userId, updatedLicense.getIdUser(), "User ID should be updated");
        assertTrue(updatedLicense.isActive(), "License should be activated");
        // Add more assertions based on your specific requirements
    }
}

