package model.DAO;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.*;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DAOConnectionTest {
    private DataSource dataSource;
    private Connection connection;

    @BeforeEach
    void setUp() {
        // Create a mock DataSource and Connection
        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        DAOConnection.setDataSource(dataSource); // Setting mock DataSource through setter
    }

    @Test
    void testGetConnection() throws SQLException {
        // Define the behavior of your Mock: when getConnection is called on dataSource, return Mocked connection.
        when(dataSource.getConnection()).thenReturn(connection);

        Connection receivedConnection = DAOConnection.getConnection();

        assertNotNull(receivedConnection);
        verify(dataSource, times(1)).getConnection();
    }

    @Test
    void testReleaseConnection() throws SQLException {
        // Here, we are not specifying behavior of mock, rather testing if the close method is being called
        DAOConnection.releaseConnection(connection);
        verify(connection, times(1)).close();
    }
}
