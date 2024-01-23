package model.entity;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LicenseTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the License class
        License license = new License();

        // Set values using setter methods
        String sequence = "Test Sequence";
        int idUser = 1;
        Date expirationDate = Date.valueOf("2022-01-01");
        boolean active = true;

        license.setSequence(sequence);
        license.setIdUser(idUser);
        license.setExpirationDate(expirationDate);
        license.setActive(active);

        // Test getter methods
        assertEquals(sequence, license.getSequence(), "getSequence() should return the correct value");
        assertEquals(idUser, license.getIdUser(), "getIdUser() should return the correct value");
        assertEquals(expirationDate, license.getExpirationDate(), "getExpirationDate() should return the correct value");
        assertEquals(active, license.isActive(), "isActive() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the License class
        License license = new License();

        // Test that the object is not null
        assertNotNull(license, "License object should not be null");
    }
}

