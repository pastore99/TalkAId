package model.service.login;

import model.DAO.DAOUser;
import model.entity.User; 
import model.service.email.EmailManager; 
import model.service.encryption.Encryption; 
import org.junit.jupiter.api.*;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthenticatorTest { 
    private final DAOUser daoUser = mock(DAOUser.class);
    private final EmailManager emailManagerMock = mock(EmailManager.class);
    private final Encryption encryption = mock(Encryption.class);
    private final User user = mock(User.class);
    Authenticator authenticator; 
      
    @BeforeEach
    void setUp() { 
        authenticator = new Authenticator(daoUser);
        authenticator.setEncryption(encryption);
    } 

    @Test 
    void itShouldAuthenticateWhenEmailAndPasswordAreCorrect() {
        String hashedPassword = BCrypt.hashpw("plainTextPassword", BCrypt.gensalt());
        when(daoUser.getUserByIdOrEmail("test@email.com")).thenReturn(user);
        when(user.getPassword()).thenReturn(hashedPassword);
        when(encryption.verifyPassword("plainTextPassword", user.getPassword())).thenReturn(true); 
        when(user.getId()).thenReturn(1);

        int response = authenticator.authenticate("test@email.com", "plainTextPassword"); 
        assertEquals(1, response);
    } 

    @Test
    void itShouldNotAuthenticateWhenPasswordIsIncorrect() {
        String hashedPassword = BCrypt.hashpw("wrongPassword", BCrypt.gensalt());
        when(daoUser.getUserByIdOrEmail("test@email.com")).thenReturn(user);
        when(user.getPassword()).thenReturn(hashedPassword);
        when(encryption.verifyPassword("plainTextPassword", user.getPassword())).thenReturn(false);

        int response = authenticator.authenticate("test@email.com", "plainTextPassword"); 
        assertEquals(-2, response);
    } 

     @Test
    void itShouldNotAuthenticateWhenUserDoesNotExist() { 
        when(daoUser.getUserByIdOrEmail("test@email.com")).thenReturn(null);

        int response = authenticator.authenticate("test@email.com", "plainTextPassword"); 
        assertEquals(-1, response);
    }

    @Test
    void testSendPin() {
        // Using spy to be able to mock the private method `generatePin`.
        Authenticator spyAuthenticator = Mockito.spy(authenticator);

        // Mock the generatePin() method
        doReturn("12345678").when(spyAuthenticator).generatePin();

        // Use anyString() so that we match any value of email,
        // except the "test@email.com" since it has a different behavior.
        String pin = spyAuthenticator.sendPin(anyString());

        // The value of pin should equal the value we stubbed above.
        assertEquals("12345678", pin);

        // Ensure that our spy's generatePin() method was called once.
        verify(spyAuthenticator, times(1)).generatePin();
    }

    @Test
    void testSendPinSpecialEmail() {
        // Explicitly test the email value that generates the pre-defined PIN.
        String pin = authenticator.sendPin("test@email.com");

        // The value of pin should be the pre-defined value "12345678".
        assertEquals("12345678", pin);
    }

    @Test
    void testResetPassword() {
        // Mock encryptPassword() return value.
        when(encryption.encryptPassword(anyString())).thenReturn("hashed_password");

        // Mock resetPassword() return value.
        when(daoUser.resetPassword(anyString(), anyString())).thenReturn(true);

        // Run our authenticator's resetPassword() method.
        boolean result = authenticator.resetPassword("test@email.com", "password");

        // Ensure that the result is true, as we mocked in daoUserMock.
        assertTrue(result);

        // Ensure that our mocked methods were called once.
        verify(encryption, times(1)).encryptPassword("password");
        verify(daoUser, times(1)).resetPassword("test@email.com", "hashed_password");
    }

    @Test
    void testGeneratePin() throws Exception {
        Authenticator authenticator = new Authenticator();

        // Use reflection to access the method
        Method method = Authenticator.class.getDeclaredMethod("generatePin");
        method.setAccessible(true);

        String result = (String) method.invoke(authenticator);

        // Assert that result is not null or empty and its length equals 8
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(8, result.length());

        // Assert that result only contains numbers
        assertTrue(result.matches("[0-9]+"));
    }
}