package model.DAO;

import model.entity.License;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DAOLicenseTest {

    @Mock
    Connection connection;

    @Mock
    PreparedStatement preparedStatement;

    @Mock
    ResultSet resultSet;

    DAOLicense daoLicense;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        // Instruct Mockito to mock SQL Statement and ResultSet behaviour
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false); // return true the first time, then false

        daoLicense = new DAOLicense(connection);
    }

    @Test
    void testGetLicenseByCode() throws SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Simulate a License in result set
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("Sequence")).thenReturn("abc123");
        when(resultSet.getInt("ID_User")).thenReturn(12345);
        when(resultSet.getDate("ExpirationDate")).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getBoolean("Active")).thenReturn(true);

        License license = daoLicense.getLicenseByCode("abc123");

        assertEquals("abc123", license.getSequence());
        assertEquals(12345, license.getIdUser());
        assertTrue(license.isActive());
    }

    @Test
    void testDeleteLicense() throws SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = daoLicense.deleteLicense("abc123");

        assertTrue(result);
    }

    @Test
    void testActivate() throws SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        // assuming one row will be updated, therefore 1 will be returned.
        when(preparedStatement.executeUpdate()).thenReturn(1);
        License license = new License();
        license.setSequence("abc123");

        daoLicense.activate(license, 12345);

        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testGenerateLicense() throws SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        // assuming one row will be updated, therefore 1 will be returned.
        when(preparedStatement.executeUpdate()).thenReturn(1);

        String generatedLicense = daoLicense.generateLicense();

        assertNotNull(generatedLicense);
        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testGenerateInvitation() throws SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        // assuming one row will be updated, therefore 1 will be returned.
        when(preparedStatement.executeUpdate()).thenReturn(1);

        String generatedInvitation = daoLicense.generateInvitation(12345);

        assertNotNull(generatedInvitation);
        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(preparedStatement, times(1)).executeUpdate();
    }
}