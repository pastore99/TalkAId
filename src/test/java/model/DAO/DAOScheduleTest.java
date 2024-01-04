package model.DAO;
import model.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DAOScheduleTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private DAOSchedule daoSchedule;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        daoSchedule = new DAOSchedule(connection);
    }

    @Test
    public void testCreateNewSchedule() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        daoSchedule.createNewSchedule(1, Date.valueOf("2023-03-20"), "08:00 to 09:00");
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testModifySchedule() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        daoSchedule.modifySchedule(1, Date.valueOf("2023-03-20"), "08:00 to 09:00", 1, Date.valueOf("2023-03-21"), "09:00 to 10:00");
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteSchedule() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        daoSchedule.deleteSchedule(1, Date.valueOf("2023-03-20"), "08:00 to 09:00");
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testRetrieveAllPatientSchedules() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        Schedule mockScheduler = new Schedule();
        mockScheduler.setIdTherapist(1);
        when(resultSet.getInt("ID_therapist")).thenReturn(mockScheduler.getIdTherapist());
        List<Schedule> schedules = daoSchedule.retrieveAllPatientSchedules(1);
        assertEquals(1, schedules.size());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testRetrieveAllTherapistSchedules() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("ID_therapist")).thenReturn(1);
        List<Schedule> schedules = daoSchedule.retrieveAllTherapistSchedules(1);
        assertEquals(1, schedules.size());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testRetrieveAllPrenotedSchedules() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("ID_therapist")).thenReturn(1);
        List<Schedule> schedules = daoSchedule.retrieveAllPrenotedSchedules(1);
        assertEquals(1, schedules.size());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testRetrieveAllNotPrenotedSchedules() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("ID_therapist")).thenReturn(1);
        List<Schedule> schedules = daoSchedule.retrieveAllNotPrenotedSchedules(1);
        assertEquals(1, schedules.size());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testRetrieveAllPrenotedSchedulesCount() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(1, daoSchedule.retrieveAllPrenotedSchedulesCount(1));
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testCheckData() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
        assertEquals(1, daoSchedule.checkData(1, Date.valueOf("2023-03-21"), "10:00 to 11:00"));
        verify(preparedStatement, times(1)).executeQuery();
    }

}
