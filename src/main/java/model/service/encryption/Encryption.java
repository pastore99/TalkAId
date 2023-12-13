package model.service.encryption;

import org.mindrot.jbcrypt.BCrypt;
public class Encryption implements EncryptionInterface {
    public String encryptPassword(String plainTextPassword) {
        // Generate a salt for BCrypt
        String salt = BCrypt.gensalt();

        // Hash the password with the salt
        return BCrypt.hashpw(plainTextPassword, salt);
    }
    public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
