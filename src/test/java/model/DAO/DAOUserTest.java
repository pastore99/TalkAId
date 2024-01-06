package model.DAO;

import model.entity.User;
import model.entity.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DAOUserTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private DAOUser daoUser;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        daoUser = new DAOUser(connection);
    }

    @Test
    public void testCheckIfEmailExists() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("count")).thenReturn(1);
        assertTrue(daoUser.checkIfEmailExists("email@test.com"));
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testCreateUser() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(123);
        assertEquals(123, daoUser.createUser("email@test.com", "password", 456));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetUserByIdOrEmail() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("ID")).thenReturn(1);
        assertNotNull(daoUser.getUserByIdOrEmail("email@test.com"));
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testResetPassword() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        assertTrue(daoUser.resetPassword("email@test.com", "newpassword"));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateUserEmailAndAddress() throws SQLException {
        daoUser = spy(new DAOUser(connection));
        doReturn(false).when(daoUser).checkIfEmailExists(anyString());
        when(preparedStatement.executeUpdate()).thenReturn(1); // Mock PreparedStatement.executeUpdate()
        doCallRealMethod().when(daoUser).updateUser(anyInt(), anyString(), anyString()); // Trigger actual method call

        String result = daoUser.updateUser(1, "test@example.com", "Test Address");

        assertEquals("Both email and address have been updated successfully.", result);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateUserEmailOnly() throws SQLException {
        daoUser = spy(new DAOUser(connection));
        doReturn(false).when(daoUser).checkIfEmailExists(anyString());
        when(preparedStatement.executeUpdate()).thenReturn(1); // Mock PreparedStatement.executeUpdate()
        doCallRealMethod().when(daoUser).updateUser(anyInt(), anyString(), anyString()); // Trigger actual method call

        String result = daoUser.updateUser(1, "test@example.com", null);

        assertEquals("Email have been updated successfully.", result);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateUserAddressOnly() throws SQLException {
        daoUser = spy(new DAOUser(connection));
        doReturn(true).when(daoUser).checkIfEmailExists(anyString());
        when(preparedStatement.executeUpdate()).thenReturn(1); // Mock PreparedStatement.executeUpdate()
        doCallRealMethod().when(daoUser).updateUser(anyInt(), anyString(), anyString()); // Trigger actual method call

        String result = daoUser.updateUser(1, null, "Test Address");

        assertEquals("Address have been updated successfully.", result);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateUserEmailExists() throws SQLException {
        daoUser = spy(new DAOUser(connection));
        doReturn(true).when(daoUser).checkIfEmailExists(anyString());
        when(preparedStatement.executeUpdate()).thenReturn(1); // Mock PreparedStatement.executeUpdate()
        doCallRealMethod().when(daoUser).updateUser(anyInt(), anyString(), anyString()); // Trigger actual method call
        when(daoUser.checkIfEmailExists(anyString())).thenReturn(true);

        String result = daoUser.updateUser(1, null, null);

        assertEquals("Invalid. No update performed.", result);
        Mockito.verify(preparedStatement, Mockito.never()).executeUpdate();
    }

    @Test
    public void testUpdateAnalyticsPreference() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        assertTrue(daoUser.updateAnalyticsPreference("1", true));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateEmailTime() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        assertTrue(daoUser.updateEmailTime("1", "10:00"));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteUserByIdOrEmail() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        assertTrue(daoUser.deleteUserByIdOrEmail("1"));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testCheckIfEmailExists_SQLException() throws SQLException {
        doThrow(SQLException.class).when(preparedStatement).executeQuery();
        assertFalse(daoUser.checkIfEmailExists("email@test.com"));
        verify(preparedStatement, times(1)).executeQuery();
    }
    @Test
    public void testCreateUser_SQLException() throws SQLException {
        doThrow(SQLException.class).when(preparedStatement).executeUpdate();
        assertEquals(-1, daoUser.createUser("email@test.com", "password", 456));
        verify(preparedStatement, times(1)).executeUpdate();
    }
    @Test
    public void testGetUserByIdOrEmail_SQLException() throws SQLException {
        doThrow(SQLException.class).when(preparedStatement).executeQuery();
        assertNull(daoUser.getUserByIdOrEmail("email@test.com"));
        verify(preparedStatement, times(1)).executeQuery();
    }
    @Test
    public void testResetPassword_SQLException() throws SQLException {
        doThrow(SQLException.class).when(preparedStatement).executeUpdate();
        assertFalse(daoUser.resetPassword("email@test.com", "newpassword"));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateUser_SQLException() throws SQLException {
        doThrow(SQLException.class).when(preparedStatement).executeUpdate();
        assertEquals("Update not possible due to a server connection issue.", daoUser.updateUser(1, "test@example.com", "Test Address"));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateAnalyticsPreference_SQLException() throws SQLException {
        doThrow(SQLException.class).when(preparedStatement).executeUpdate();
        assertFalse(daoUser.updateAnalyticsPreference("1", true));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateEmailTime_SQLException() throws SQLException {
        doThrow(SQLException.class).when(preparedStatement).executeUpdate();
        assertFalse(daoUser.updateEmailTime("1", "10:00"));
        verify(preparedStatement, times(1)).executeUpdate();
    }
    @Test
    public void testDeleteUserByIdOrEmail_SQLException() throws SQLException {
        doThrow(SQLException.class).when(preparedStatement).executeUpdate();
        assertFalse(daoUser.deleteUserByIdOrEmail("1"));
        verify(preparedStatement, times(1)).executeUpdate();
    }

}

