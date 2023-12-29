package model.DAO;

import model.entity.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOMessage {

    private Message getMessageFromResultSet(ResultSet resultSet) throws SQLException {
        Message message = new Message();

        message.setIdMessage(resultSet.getInt("ID_message"));
        message.setSender(resultSet.getInt("Sender"));
        message.setRecipient(resultSet.getInt("Recipient"));
        message.setRead(resultSet.getBoolean("Read"));
        message.setBody(resultSet.getString("Body"));
        message.setSent(resultSet.getTimestamp("sent"));

        return message;
    }

    /**
     * Retrieves a list of user IDs based on the therapist ID.
     *
     * @param therapistId The ID of the therapist.
     * @return A list of user IDs associated with the specified therapist.
     */
    public List<Integer> retrieveUserIdsByTherapist(int therapistId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Integer> userIds = new ArrayList<>();

        try {
            connection = DAOConnection.getConnection();

            // Query to retrieve user IDs associated with the specified therapist
            String query = "SELECT ID FROM user WHERE ID_Therapist = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, therapistId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("ID");
                userIds.add(userId);
            }

        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }

        return userIds;
    }

    public List<Message> retrieveMessages(int userId, int contact) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Message> messages = new ArrayList<>();

        try {
            connection = DAOConnection.getConnection();

            // Query to retrieve messages between the two users
            String query = "SELECT * FROM message WHERE (Sender = ? AND Recipient = ?) OR (Sender = ? AND Recipient = ?) ORDER BY sent";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, contact);
            preparedStatement.setInt(3, contact);
            preparedStatement.setInt(4, userId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Message message = getMessageFromResultSet(resultSet);
                messages.add(message);
            }

        } catch (SQLException e) {
            // Handle the exception (e.g., log or throw)
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }

        return messages;
    }

    public void markMessagesAsRead(int senderId, int recipientId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DAOConnection.getConnection();

            String sql = "UPDATE message SET `Read` = TRUE WHERE Sender = ? AND Recipient = ?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, recipientId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            // Handle exceptions (e.g., print stack trace, log error, etc.)
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(int sender, int recipient, String text) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DAOConnection.getConnection();

            String sql = "INSERT INTO message (Sender, Recipient, Body) VALUES (?, ?, ?);";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sender);
            pstmt.setInt(2, recipient);
            pstmt.setString(3, text);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            // Handle exceptions (e.g., print stack trace, log error, etc.)
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int countReceivedMessages(int recipientId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = DAOConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM message WHERE Recipient = ?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, recipientId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            // Handle exceptions (e.g., print stack trace, log error, etc.)
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DAOConnection.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }
}
