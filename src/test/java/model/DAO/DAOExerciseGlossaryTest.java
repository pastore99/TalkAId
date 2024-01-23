package model.DAO;

import model.entity.ExerciseGlossary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DAOExerciseGlossaryTest {
    private DAOExerciseGlossary daoExerciseGlossary;
    private Connection connectionMock;
    private PreparedStatement preparedStatementMock;
    private ResultSet resultSetMock;

    @BeforeEach
    void setUp() throws SQLException {
        // Mock all SQL objects
        connectionMock = mock(Connection.class);
        preparedStatementMock = mock(PreparedStatement.class);
        resultSetMock = mock(ResultSet.class);

        // When a new prepared statement is created, return the mock
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Create a new DAOExerciseGlossary instance with the mock connection
        daoExerciseGlossary = new DAOExerciseGlossary(connectionMock);
    }

    @Test
    void testGetExerciseByCode() throws SQLException {
        int code = 1;
        when(connectionMock.isClosed()).thenReturn(false);
        when(connectionMock.prepareStatement(any())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("ID_exercise")).thenReturn(code);
        when(resultSetMock.getString("ExerciseName")).thenReturn("Example Exercise");
        // Mock other columns as necessary...

        ExerciseGlossary exerciseGlossary = daoExerciseGlossary.getExerciseByCode(code);

        assertNotNull(exerciseGlossary);
        assertEquals(code, exerciseGlossary.getIdExercise());
        assertEquals("Example Exercise", exerciseGlossary.getExerciseName());
        // Assert other columns as necessary...

        verify(connectionMock, times(1)).prepareStatement(any());
        verify(preparedStatementMock, times(1)).setInt(1, code);
        verify(preparedStatementMock, times(1)).executeQuery();
    }
}
