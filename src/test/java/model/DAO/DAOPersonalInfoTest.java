package model.DAO;

import model.DAO.DAOPersonalInfo;
import model.entity.PersonalInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class DAOPersonalInfoTest {

    private DAOPersonalInfo daoPersonalInfo;
    private int createdUserId = 0;

    @BeforeEach
    void setUp() {
        daoPersonalInfo = new DAOPersonalInfo();
        String email = "newuser@example.com";
        String password = "password123";
        int therapistId = 8;
        // Call the method to test
        createdUserId =  new DAOUser().createUser(email, password, therapistId);
    }

    @Test
    void testCreateRegistryAndRetrievePersonalInfo() {
        // Mock user data
        String firstName = "Tester";
        String lastName = "Tester";

        // Call the method to create a registry
        boolean registryCreated = daoPersonalInfo.createRegistry(createdUserId, firstName, lastName);

        // Check if the registry creation was successful
        assertTrue(registryCreated, "User registry should be created successfully");

        // Call the method to retrieve personal information
        PersonalInfo retrievedPersonalInfo = daoPersonalInfo.getPersonalInfo(createdUserId);

        // Check if personal information is retrieved successfully
        assertNotNull(retrievedPersonalInfo, "Retrieved personal information should not be null");
        assertEquals(createdUserId, retrievedPersonalInfo.getIdUser(), "User IDs should match");
        assertEquals(firstName, retrievedPersonalInfo.getFirstname(), "First names should match");
        assertEquals(lastName, retrievedPersonalInfo.getLastname(), "Last names should match");

        // Clean up:
        assertTrue(daoPersonalInfo.deleteRegistry(createdUserId));
        new DAOUser().deleteUserByIdOrEmail(createdUserId);
        assertFalse(daoPersonalInfo.deleteRegistry(createdUserId));
    }
}

