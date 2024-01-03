package model.service.license;
import model.entity.License;
public interface LicenseActivationInterface {

    /**
     * raccoglie la licenza utilizzando il sottosistema DAO
     *
     * @param code è il codice della licensa inserita
     * @return License è la licenza ottenuta, altrimenti null
     */
    License getLicense(String code);

    /**
     * verifica se la licenza è valida
     *
     * @param license è la licenza ottenuta
     * @return True se la licenza è valida, altrimenti False
     */
    boolean isActivable(License license);

    /**
     * verifica se la licenza è di un logopedista
     *
     * @param license è la licenza ottenuta
     * @return Licenza.ID_Utente, se è diverso da 0 è l'ID del logopedista da associare al paziente
     */
    int isForTherapist(License license);

    /**
     * attiva la licenza e gli associa il proprietario
     *
     * @param license è la licenza ottenuta
     * @param userId  è l'id del nuovo utente
     */
    void activate(License license, int userId);

    /**
     * genera una nuova licenza da 4 caratteri e con l'ID del terapeuta
     *
     * @param therapistId è l'identificativo del terapeuta
     */
    String generatePin(int therapistId);

    /**
     * genera una nuova licenza da 8 caratteri e con ID terapeuta 0
     */
    String generateLicense();

}