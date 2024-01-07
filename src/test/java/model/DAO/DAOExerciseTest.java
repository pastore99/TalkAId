package model.DAO;

import model.entity.Exercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DAOExerciseTest {
    private DAOExercise daoExercise;
    private Connection connectionMock;
    private PreparedStatement preparedStatementMock;
    private ResultSet resultSetMock;

    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        preparedStatementMock = mock(PreparedStatement.class);
        resultSetMock = mock(ResultSet.class);
        daoExercise = new DAOExercise(connectionMock);

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(connectionMock.isClosed()).thenReturn(false);
    }

    @Test
    void testGetExerciseByPk() throws SQLException {
        int userId = 1;
        int exerciseId = 1;
        Date insertDate = java.sql.Date.valueOf("2023-12-01");

        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("ID_user")).thenReturn(userId);
        when(resultSetMock.getInt("ID_exercise")).thenReturn(exerciseId);
        when(resultSetMock.getDate("InsertionDate")).thenReturn(insertDate);
        // Add other mock configurations for the remaining columns in the resultSet

        Exercise exercise = daoExercise.getExerciseByPk(userId, exerciseId, insertDate);

        assertNotNull(exercise);
        assertEquals(userId, exercise.getIdUser());
        assertEquals(exerciseId, exercise.getIdExercise());
        assertEquals(insertDate, exercise.getInsertionDate());
        // Add other assertions for the remaining columns

        verify(preparedStatementMock, times(1)).setInt(1, userId);
        verify(preparedStatementMock, times(1)).setInt(2, exerciseId);
        verify(preparedStatementMock, times(1)).setDate(3, insertDate);
        verify(preparedStatementMock, times(1)).executeQuery();
    }

    @Test
    void testSetExerciseExecution() throws SQLException {
        int userId = 1;
        int exerciseId = 1;
        Date insertDate = java.sql.Date.valueOf("2023-12-01");
        Blob blob = mock(Blob.class);

        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        boolean result = daoExercise.setExerciseExecution(userId, exerciseId, insertDate, blob);

        assertTrue(result);

        verify(preparedStatementMock, times(1)).setBlob(1, blob);
        verify(preparedStatementMock, times(1)).setInt(2, userId);
        verify(preparedStatementMock, times(1)).setInt(3, exerciseId);
        verify(preparedStatementMock, times(1)).setDate(4, insertDate);
        verify(preparedStatementMock, times(1)).executeUpdate();
    }

    @Test
    void testSetExerciseEvaluation() throws SQLException {
        int userId = 1;
        int exerciseId = 1;
        Date insertDate = java.sql.Date.valueOf("2023-12-01");
        int evaluation = 5;

        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        boolean result = daoExercise.setExerciseEvaluation(userId, exerciseId, insertDate, evaluation);

        assertTrue(result);

        verify(preparedStatementMock, times(1)).setInt(1, evaluation);
        verify(preparedStatementMock, times(1)).setInt(2, userId);
        verify(preparedStatementMock, times(1)).setInt(3, exerciseId);
        verify(preparedStatementMock, times(1)).setDate(4, insertDate);
        verify(preparedStatementMock, times(1)).executeUpdate();
    }

    @Test
    void testSetExerciseCompletionDate() throws SQLException {
        int userId = 1;
        int exerciseId = 1;
        Date insertDate = java.sql.Date.valueOf("2023-12-01");
        Date completionDate = java.sql.Date.valueOf("2023-12-31");

        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        boolean result = daoExercise.setExerciseCompletionDate(userId, exerciseId, insertDate, completionDate);

        assertTrue(result);

        verify(preparedStatementMock, times(1)).setDate(1, completionDate);
        verify(preparedStatementMock, times(1)).setInt(2, userId);
        verify(preparedStatementMock, times(1)).setInt(3, exerciseId);
        verify(preparedStatementMock, times(1)).setDate(4, insertDate);
        verify(preparedStatementMock, times(1)).executeUpdate();
    }

    @Test
    void testSetExerciseFeedback() throws SQLException {
        int userId = 1;
        int exerciseId = 1;
        Date insertDate = java.sql.Date.valueOf("2023-12-01");
        int feedback = 5;

        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        boolean result = daoExercise.setExerciseFeedback(userId, exerciseId, insertDate, feedback);

        assertTrue(result);
        verify(preparedStatementMock, times(1)).setInt(1, feedback);
        verify(preparedStatementMock, times(1)).setInt(2, userId);
        verify(preparedStatementMock, times(1)).setInt(3, exerciseId);
        verify(preparedStatementMock, times(1)).setDate(4, insertDate);
        verify(preparedStatementMock, times(1)).executeUpdate();
    }
}
