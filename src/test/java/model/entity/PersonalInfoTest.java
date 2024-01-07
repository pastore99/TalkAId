package model.entity;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonalInfoTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the PersonalInfo class
        PersonalInfo personalInfo = new PersonalInfo();

        // Set values using setter methods
        int idUser = 1;
        String firstname = "John";
        String lastname = "Doe";
        Date dateOfBirth = Date.valueOf("1990-01-01");
        String gender = "Male";
        String address = "123 Main St";
        String ssn = "123-45-6789";
        String phone = "555-1234";

        personalInfo.setIdUser(idUser);
        personalInfo.setFirstname(firstname);
        personalInfo.setLastname(lastname);
        personalInfo.setDateOfBirth(dateOfBirth);
        personalInfo.setGender(gender);
        personalInfo.setAddress(address);
        personalInfo.setSsn(ssn);
        personalInfo.setPhone(phone);

        // Test getter methods
        assertEquals(idUser, personalInfo.getIdUser(), "getIdUser() should return the correct value");
        assertEquals(firstname, personalInfo.getFirstname(), "getFirstname() should return the correct value");
        assertEquals(lastname, personalInfo.getLastname(), "getLastname() should return the correct value");
        assertEquals(dateOfBirth, personalInfo.getDateOfBirth(), "getDateOfBirth() should return the correct value");
        assertEquals(gender, personalInfo.getGender(), "getGender() should return the correct value");
        assertEquals(address, personalInfo.getAddress(), "getAddress() should return the correct value");
        assertEquals(ssn, personalInfo.getSsn(), "getSsn() should return the correct value");
        assertEquals(phone, personalInfo.getPhone(), "getPhone() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the PersonalInfo class
        PersonalInfo personalInfo = new PersonalInfo();

        // Test that the object is not null
        assertNotNull(personalInfo, "PersonalInfo object should not be null");
    }
}

