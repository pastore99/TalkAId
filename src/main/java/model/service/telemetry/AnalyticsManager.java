package model.service.telemetry;

import model.DAO.DAOAnalytics;

public class AnalyticsManager {
    public void storeAnalytics(int userId, String type, String description) {
        new DAOAnalytics().storeAnalytics(userId, type, description);
    }
}
