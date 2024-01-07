package model.DAO;

import model.entity.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DAOMessageTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private DAOMessage daoMessage;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        daoMessage = new DAOMessage(connection);
    }

    @Test
    public void testRetrieveUserIdsByTherapist() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("ID")).thenReturn(1);
        List<Integer> userIds = daoMessage.retrieveUserIdsByTherapist(1);
        assertEquals(1, userIds.size());
        verify(connection, times(1)).prepareStatement(any(String.class));
    }

    @Test
    void testRetrieveMessages() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        Message mockedMessage = new Message();
        mockedMessage.setIdMessage(1);
        when(resultSet.getInt("ID_message")).thenReturn(mockedMessage.getIdMessage());
        List<Message> messages = daoMessage.retrieveMessages(1, 2);
        assertEquals(1, messages.size());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    void testMarkMessagesAsRead() throws SQLException {
        daoMessage.markMessagesAsRead(1, 2);
        verify(preparedStatement, times(1)).executeUpdate();
    }
    @Test
    public void testSendMessage() throws SQLException {
        daoMessage.sendMessage(1, 2, "Hello World");
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testCountReceivedMessages() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(5);
        int count = daoMessage.countReceivedMessages(1);
        assertEquals(5, count);
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testDeleteLastInsertedMessage() throws SQLException {
        Statement statement = mock(Statement.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
        daoMessage.deleteLastInsertedMessage();
        verify(connection, times(1)).createStatement();
    }

}
