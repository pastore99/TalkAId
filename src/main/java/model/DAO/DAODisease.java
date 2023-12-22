package model.DAO;

import model.entity.Condition;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAODisease {

    private Condition getConditionFromResultSet(ResultSet resultSet) throws SQLException {
        Condition condition = new Condition();

        condition.setIdCondition(resultSet.getInt("ID"));
        condition.setDisorderName(resultSet.getString("Name_Disorder"));
        condition.setDisorderDescription(resultSet.getString("Disorder_Description"));
        return condition;
    }

    public Condition getConditionByID(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = DAOConnection.getConnection();
            String query = null;

            query = "SELECT * FROM condition WHERE ID = ?";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getConditionFromResultSet(resultSet);
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
        return null; // or you may throw an exception here
    }

}
