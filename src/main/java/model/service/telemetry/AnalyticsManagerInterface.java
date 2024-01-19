package model.service.telemetry;

/**
 * Contiene metodi per il salvataggio e manipolazione di informazioni di telemetria degli utenti.
 */
public interface AnalyticsManagerInterface {

    /**
     * Salva i dati di telemetria di un utente, se possibile.
     *
     * @param userId      l'ID dell'utente.
     * @param type        la tipologia di informazione salvata.
     * @param description descrizione estesa dell'osservazione.
     */
    void storeAnalytics(int userId, String type, String description);
}
