package model.DAO;

import model.entity.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Date;

public class DAOSchedule {

    private Connection connection;

    public DAOSchedule(Connection connection) {
        this.connection = connection;
    }

    public DAOSchedule() {
        try {
            this.connection = DAOConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public int checkData(int idTherapist, Date date, String timeSlot) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
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
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

}
