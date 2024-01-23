package model.service.telemetry;

import model.DAO.DAOAnalytics;

/**
 * Questa classe provvede alle funzionalità per il prelievo di statistiche di utilizzo
 */
public class AnalyticsManager implements AnalyticsManagerInterface{
    public void storeAnalytics(int userId, String type, String description) {
        new DAOAnalytics().storeAnalytics(userId, type, description);
    }
}
