package controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ResetPasswordTest {

    private ResetPassword resetPasswordServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        resetPasswordServlet = new ResetPassword();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    void testDoPost() throws Exception {
        // Setup test data
        String testEmail = "patient2@example.com";
        String testPassword = "pwd";

        // Mock request parameters
        when(request.getParameter("email")).thenReturn(testEmail);
        when(request.getParameter("newPassword")).thenReturn(testPassword);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        // Execute doPost method
        resetPasswordServlet.doPost(request, response);

        // Verify output
        printWriter.flush();
        assertTrue(stringWriter.toString().contains("Password cambiata con successo!"));
    }
}