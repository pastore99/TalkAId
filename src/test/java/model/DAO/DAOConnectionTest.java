package model.DAO;

import model.service.encryption.Encryption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DAOConnectionTest {

    private static Connection connection;

    @BeforeAll
    static void setUp() {
        try {
            // Set up any necessary configurations or preparations before the tests
            connection = DAOConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetConnection() {
        assertNotNull(connection, "Connection should not be null");
    }

    // Add more tests as needed for your specific use cases

    @AfterAll
    static void tearDown() {
        try {
            // Release any resources or perform cleanup after the tests
            DAOConnection.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
