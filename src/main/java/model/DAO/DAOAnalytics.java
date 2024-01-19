package model.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAnalytics {
    private static final Logger logger = LoggerFactory.getLogger(DAOAnalytics.class);

    private Connection connection;
    public DAOAnalytics(Connection connection) {this.connection=connection;}

    public DAOAnalytics() {
        try{
            this.connection=DAOConnection.getConnection();
        }catch (SQLException e) {
            logger.error("Error getting connection", e);
        }
    }
    public void storeAnalytics(int userId, String type, String description) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connection.isClosed() ? DAOConnection.getConnection() : connection;

            String query = "SELECT Analytics FROM user WHERE ID = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                boolean analyticsEnabled = resultSet.getBoolean("Analytics");

                if (analyticsEnabled) {
                    String storeAnalyticsSql = "INSERT INTO analytics (ID_user, Type, Description) VALUES (?, ?, ?);";
                    preparedStatement = connection.prepareStatement(storeAnalyticsSql);
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setString(2, type);
                    preparedStatement.setString(3, description);
                    preparedStatement.executeUpdate();
                }
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
    }
}
