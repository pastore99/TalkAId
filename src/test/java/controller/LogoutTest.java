package controller;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutTest {
    @Test
    public void testDoPost() throws Exception {
        // Create mocks
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        // Define return value for method getA()
        when(request.getSession(false)).thenReturn(session);

        // Use mock in test....
        new Logout().doPost(request, response);

        // Verify the session invalidate method was called once
        verify(session, times(1)).invalidate();
    }
}
