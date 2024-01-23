package model.service.encryption;

/**
 * Interfaccia per la crittografia e verifica delle password utilizzando BCrypt.
 */
public interface EncryptionInterface {
    /**
     * Crittografa la password in chiaro fornita utilizzando BCrypt.
     *
     * @param plainTextPassword La password in chiaro da crittografare.
     * @return La password crittografata.
     */
    String encryptPassword(String plainTextPassword);

    /**
     * Verifica se la password in chiaro fornita corrisponde alla password crittografata.
     *
     * @param plainTextPassword La password in chiaro da verificare.
     * @param hashedPassword    La password crittografata da confrontare.
     * @return true se la password in chiaro corrisponde alla password crittografata, false altrimenti.
     */
    boolean verifyPassword(String plainTextPassword, String hashedPassword);

    // Puoi includere altri metodi o personalizzazioni in base alle tue esigenze

}
