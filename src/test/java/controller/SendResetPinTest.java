package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

class SendResetPinTest {

    private SendResetPin sendResetPinServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        sendResetPinServlet = new SendResetPin();

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    void testDoPostWithEmailExists() throws IOException {
        // Setup test data
        String email = "patient2@example.com";

        // Set request parameters
        when(request.getParameter("email")).thenReturn(email);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute doPost method
        sendResetPinServlet.doPost(request, response);

        // Verify response
        assert(stringWriter.toString().trim().length()==8);
    }

    @Test
    void testDoPostWithEmailNotExistent() throws IOException {
        // Setup test data
        String email = "nonexistent@example.com";

        // Set request parameters
        when(request.getParameter("email")).thenReturn(email);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Execute doPost method
        sendResetPinServlet.doPost(request, response);

        // Verify response
        assert(stringWriter.toString().trim().equals("NA"));
    }
}
