package model.DAO;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import java.util.ArrayList;
import model.entity.Condition;


@ExtendWith(MockitoExtension.class)
public class DAOConditionTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private DAOCondition daoCondition;

    @BeforeEach
    void setUp() throws SQLException {
        daoCondition = new DAOCondition(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testGetConditionsOfPatient() throws SQLException {
        int idPatient = 1;

        when(connection.isClosed()).thenReturn(false);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // simulate returning one row
        when(resultSet.getInt("ID_condition")).thenReturn(1);
        when(resultSet.getString("DisorderDescription")).thenReturn("test description");
        when(resultSet.getString("DisorderName")).thenReturn("test disorder");
        when(resultSet.getInt("Severity")).thenReturn(1);

        ArrayList<Condition> result = daoCondition.getConditionsOfPatient(idPatient);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test disorder", result.get(0).getDisorderName());
    }

    @Test
    public void testAddConditionPatient() throws SQLException {
        int conditionID = 1;
        int patientID = 1;
        int severity = 1;

        when(connection.isClosed()).thenReturn(false);
        when(preparedStatement.executeUpdate()).thenReturn(1);


        boolean result = daoCondition.AddConditionPatient(conditionID, patientID, severity);

        assertTrue(result);
        verify(connection, times(1)).prepareStatement("INSERT INTO PatientCondition (ID_condition, ID_patient, Severity)\n" +
                "VALUES (?, ?, ?)");
        verify(preparedStatement, times(1)).setInt(1, conditionID);
        verify(preparedStatement, times(1)).setInt(2, patientID);
        verify(preparedStatement, times(1)).setInt(3, severity);
        verify(preparedStatement, times(1)).executeUpdate();
        verify(connection, times(1)).commit();
    }

    @Test
    public void testRemoveConditionPatient() throws SQLException {
        int conditionID = 1;
        int patientID = 1;

        when(connection.isClosed()).thenReturn(false);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = daoCondition.RemoveConditionPatient(conditionID, patientID);

        assertTrue(result);
        verify(connection, times(1)).prepareStatement("DELETE FROM PatientCondition\n" +
                "WHERE ID_condition = ? AND ID_patient = ?;");
        verify(preparedStatement, times(1)).setInt(1, conditionID);
        verify(preparedStatement, times(1)).setInt(2, patientID);
        verify(preparedStatement, times(1)).executeUpdate();
        verify(connection, times(1)).commit();
    }

    @Test
    public void testGetConditionsNOTOfPatient() throws SQLException {
        int idPatient = 1;

        when(connection.isClosed()).thenReturn(false);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // simulates returning one row
        when(resultSet.getInt("ID_condition")).thenReturn(1);
        when(resultSet.getString("DisorderDescription")).thenReturn("test description");
        when(resultSet.getString("DisorderName")).thenReturn("test disorder");

        ArrayList<Condition> result = daoCondition.getConditionsNOTOfPatient(idPatient);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test disorder", result.get(0).getDisorderName());
        verify(connection, times(1)).prepareStatement("SELECT c.*\n" +
                "FROM `condition` c\n" +
                "LEFT JOIN PatientCondition pc ON c.ID_condition = pc.ID_condition AND pc.ID_patient = ?\n" +
                "WHERE pc.ID_patient IS NULL\n" +
                "ORDER BY c.DisorderName;");
        verify(preparedStatement, times(1)).setObject(1, idPatient);
        verify(preparedStatement, times(1)).executeQuery();
    }

}