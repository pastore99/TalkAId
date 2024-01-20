package model.DAO;

import model.entity.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOSchedule {
    private static final Logger logger = LoggerFactory.getLogger(DAOSchedule.class);

    private Connection connection;

    public DAOSchedule(Connection connection) {
        this.connection = connection;
    }

    public DAOSchedule() {
        try {
            this.connection = DAOConnection.getConnection();
        } catch (SQLException e) {
            logger.error("Error getting connection", e);
        }
    }
    private Schedule getScheduleFromResultSet(ResultSet resultSet) throws SQLException {
        Schedule schedule = new Schedule();

        schedule.setIdTherapist(resultSet.getInt("ID_therapist"));
        schedule.setDate(resultSet.getDate("Date"));
        schedule.setTimeSlot(resultSet.getString("TimeSlot"));
        schedule.setReserved(resultSet.getInt("Reserved"));

        return schedule;
    }
    public void createNewSchedule(int idTherapist, Date date, String timeSlot) {
        PreparedStatement pstmt = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "INSERT INTO schedule (ID_therapist, Date, TimeSlot) VALUES (?, ?, ?);";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idTherapist);
            pstmt.setDate(2, date);
            pstmt.setString(3, timeSlot);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
    }
    public void modifySchedule(int idTherapist, Date date, String timeSlot, int reserved, Date ndate, String ntimeSlot) {
        PreparedStatement pstmt = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "UPDATE schedule SET Date = ?, TimeSlot = ?, Reserved = ? WHERE ID_therapist = ? AND Date = ? AND TimeSlot = ?;";

            pstmt = connection.prepareStatement(sql);
            pstmt.setDate(1, ndate);
            pstmt.setString(2, ntimeSlot);
            pstmt.setInt(3, reserved);
            pstmt.setInt(4, idTherapist);
            pstmt.setDate(5, date);
            pstmt.setString(6, timeSlot);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
    }
    public void deleteSchedule(int idTherapist, Date date, String timeSlot) {
        PreparedStatement pstmt = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "DELETE FROM schedule WHERE ID_therapist = ? AND Date = ? AND TimeSlot = ?;";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idTherapist);
            pstmt.setDate(2, date);
            pstmt.setString(3, timeSlot);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
    }
    public List<Schedule> retrieveAllPatientSchedules(int reserved) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Schedule> schedules = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String query = "SELECT * FROM schedule WHERE Reserved = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reserved);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Schedule schedule = getScheduleFromResultSet(resultSet);
                schedules.add(schedule);
            }

        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }

        return schedules;
    }
    public List<Schedule> retrieveAllTherapistSchedules(int idTherapist) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Schedule> schedules = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String query = "SELECT * FROM schedule WHERE ID_therapist = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idTherapist);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Schedule schedule = getScheduleFromResultSet(resultSet);
                schedules.add(schedule);
            }

        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }

        return schedules;
    }
    public List<Schedule> retrieveAllPrenotedSchedules(int idTherapist) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Schedule> schedules = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String query = "SELECT * FROM schedule WHERE Reserved != 0 AND ID_therapist =?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idTherapist);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Schedule schedule = getScheduleFromResultSet(resultSet);
                schedules.add(schedule);
            }

        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }

        return schedules;
    }
    public List<Schedule> retrieveAllNotPrenotedSchedules(int idTherapist) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Schedule> schedules = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String query = "SELECT * FROM schedule WHERE Reserved = 0 AND ID_therapist =?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idTherapist);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Schedule schedule = getScheduleFromResultSet(resultSet);
                schedules.add(schedule);
            }

        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }

        return schedules;
    }
    public int retrieveAllPrenotedSchedulesCount(int idTherapist) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "SELECT COUNT(*) FROM schedule WHERE Reserved != 0 AND ID_therapist =?;";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idTherapist);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
        return count;
    }

    //Restituisce TRUE se la data è disponibile. Restituisce FALSE se la data NON è disponibile O non è valida.
    public boolean checkData(int idTherapist, Date date, String timeSlot) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Comparing the provided date with the current date
            LocalDate localDate = LocalDate.now();
            Date currentDate = java.sql.Date.valueOf(localDate);
            if(currentDate.after(date)){
                return false;
            }

            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "SELECT COUNT(*) FROM schedule WHERE ID_therapist = ? AND Date = ? AND TimeSlot = ?;";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idTherapist);
            pstmt.setDate(2, date);
            pstmt.setString(3, timeSlot);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error query", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
        // Here we change the condition. It will return true if count > 0, meaning the data is available.
        // It returns false otherwise.
        return count == 0;
    }

}
