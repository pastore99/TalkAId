package model.entity;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInfoTest {

    @Test
    public void testUserInfoConstructorAndGetters() {
        // Define some sample data
        int id = 1;
        String email = "test@example.com";
        Timestamp activationDate = new Timestamp(System.currentTimeMillis());
        String firstname = "John";
        String lastname = "Doe";
        Date dateOfBirth = new Date(System.currentTimeMillis());
        String gender = "Male";
        String address = "123 Main St";
        String ssn = "123-45-6789";
        String phone = "123-456-7890";

        // Create a new UserInfo object
        UserInfo userInfo = new UserInfo(id, email, activationDate, firstname, lastname, dateOfBirth, gender, address, ssn, phone);

        // Use the getter methods to retrieve the data and check that it matches the sample data
        assertEquals(id, userInfo.getId());
        assertEquals(email, userInfo.getEmail());
        assertEquals(activationDate, userInfo.getActivationDate());
        assertEquals(firstname, userInfo.getFirstname());
        assertEquals(lastname, userInfo.getLastname());
        assertEquals(dateOfBirth, userInfo.getDateOfBirth());
        assertEquals(gender, userInfo.getGender());
        assertEquals(address, userInfo.getAddress());
        assertEquals(ssn, userInfo.getSsn());
        assertEquals(phone, userInfo.getPhone());
    }
}