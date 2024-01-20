package model.DAO;

import model.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe DAOMessage provvede ai metodi per manipolare le informazioni dei Message dal database.
 */
public class DAOMessage {
    private static final Logger logger = LoggerFactory.getLogger(DAOMessage.class);

    private Connection connection;

    public DAOMessage(Connection connection) {
        this.connection = connection;
    }

    public DAOMessage() {
        try {
            this.connection = DAOConnection.getConnection();
        } catch (SQLException e) {
            logger.error("Error getting connection", e);
        }
    }
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Integer> userIds = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

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
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        return userIds;
    }

    public List<Message> retrieveMessages(int userId, int contact) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Message> messages = new ArrayList<>();

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

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
            logger.error("Error query", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                logger.error("Error finally", e);
            }
        }

        return messages;
    }

    public void markMessagesAsRead(int senderId, int recipientId) {
        PreparedStatement pstmt = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "UPDATE message SET `Read` = TRUE WHERE Sender = ? AND Recipient = ?;";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, recipientId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            // Handle exceptions (e.g., print stack trace, log error, etc.)
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

    public void sendMessage(int sender, int recipient, String text) {
        PreparedStatement pstmt = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "INSERT INTO message (Sender, Recipient, Body) VALUES (?, ?, ?);";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sender);
            pstmt.setInt(2, recipient);
            pstmt.setString(3, text);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            // Handle exceptions (e.g., print stack trace, log error, etc.)
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

    public int countReceivedMessages(int recipientId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String sql = "SELECT COUNT(*) FROM message WHERE Recipient = ?;";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, recipientId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            // Handle exceptions (e.g., print stack trace, log error, etc.)
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

    public void deleteLastInsertedMessage() {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;
            stmt = connection.createStatement();

            String sql = "SELECT MAX(ID_message) FROM message";

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int lastId = rs.getInt(1);
                sql = "DELETE FROM message WHERE ID_message = " + lastId;
                stmt.executeUpdate(sql);
            }

        } catch (SQLException e) {
            // Handle exceptions (e.g., print stack trace, log error, etc.)
            logger.error("Error query", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                logger.error("Error finally", e);
            }
        }
    }
}
