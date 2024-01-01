package model.service.encryption;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EncryptionTest {

        @Test
        public void testEncryptPassword() {
            Encryption encryption = new Encryption();
            String plainTextPassword = "password";

            // Ensure that the encrypted password is not null or empty
            String encryptedPassword = encryption.encryptPassword(plainTextPassword);
            assertNotNull(encryptedPassword);
            assertFalse(encryptedPassword.isEmpty());

            // Ensure that each encryption of the same password produces different results
            String secondEncryptedPassword = encryption.encryptPassword(plainTextPassword);
            assertNotEquals(encryptedPassword, secondEncryptedPassword);
        }

        @Test
        public void testVerifyPassword() {
            Encryption encryption = new Encryption();
            String plainTextPassword = "password";
            String hashedPassword = encryption.encryptPassword(plainTextPassword);

            // Ensure that the password is verified correctly
            assertTrue(encryption.verifyPassword(plainTextPassword, hashedPassword));

            // Ensure that an incorrect password is not verified
            String incorrectPassword = "wrongpassword";
            assertFalse(encryption.verifyPassword(incorrectPassword, hashedPassword));
        }
}
