package model.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import java.sql.*;
import static org.mockito.Mockito.*;

class DAOPersonalInfoTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private DAOPersonalInfo daoPersonalInfo;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        daoPersonalInfo = new DAOPersonalInfo(connection);
    }

    @Test
    public void testCreateRegistry() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        assertTrue(daoPersonalInfo.createRegistry(1, "Test", "Mockito"));
    }

    @Test
    public void testGetPersonalInfo() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("ID_user")).thenReturn(1);
        when(resultSet.getString("Firstname")).thenReturn("Test Name");
        assertNotNull(daoPersonalInfo.getPersonalInfo(1));
    }

    @Test
    public void testDeleteRegistry() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        assertTrue(daoPersonalInfo.deleteRegistry(1));
    }

    @Test
    public void testUpdatePersonalInfoFromId() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        assertTrue(daoPersonalInfo.updatePersonalInfofromId(1, "Test First", "Test Last", "1234567890"));
    }

    @Test
    public void testUpdatePersonalInfoAndUserFromId() throws SQLException {
        // Provide test parameters
        int id = 10; // This should be a valid ID from your test database
        String firstname = "NewFirstName";
        String lastname = "NewLastName";
        String phone = "NewPhone";
        String email = "newemail@example.com";
        String address = "New Address";

        // All execution of executeUpdate() returns 1 in this test
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = daoPersonalInfo.updatePersonalInfoAndUserFromId(id, firstname, lastname, phone, email, address);

        // Assert that both updates were successful
        assertTrue(result);

        // Verify the first prepared statement was set up correctly
        verify(preparedStatement, times(1)).setString(1, firstname);
        verify(preparedStatement, times(1)).setString(2, lastname);
        verify(preparedStatement, times(1)).setString(3, phone);
        verify(preparedStatement, times(1)).setString(4, address);
        verify(preparedStatement, times(1)).setInt(5, id);

        // Verify the second prepared statement was set up correctly
        verify(preparedStatement, times(1)).setString(1, email);
        verify(preparedStatement, times(1)).setInt(2, id);
    }
}

