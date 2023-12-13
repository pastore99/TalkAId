package model.DAO;

import model.entity.License;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOLicense {

    private static License extractLicenseFromResultSet(ResultSet resultSet) throws SQLException {
        License license = new License();
        license.setSequence(resultSet.getString("Sequence"));
        license.setIdUser(resultSet.getInt("ID_User"));
        license.setExpirationDate(resultSet.getDate("ExpirationDate"));
        license.setActive(resultSet.getBoolean("Active"));
        return license;
    }

    public License getLicenseByCode(String code) {
        String query = "SELECT * FROM license WHERE Sequence = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractLicenseFromResultSet(resultSet);
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

        return null;
    }

    public void activate(License license, int userId) {
        String updateQuery = "UPDATE TalkAID2.license SET active = TRUE, ID_User = ? WHERE Sequence = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DAOConnection.getConnection();
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, license.getSequence());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DAOConnection.releaseConnection(connection);
            } catch (SQLException e) {
                // Handle the exception (e.g., log or throw)
                e.printStackTrace();
            }
        }
    }


}