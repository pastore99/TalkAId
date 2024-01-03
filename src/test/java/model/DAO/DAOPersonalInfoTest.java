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
}

