package controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CheckCurrentPasswordTest {

    private CheckCurrentPassword checkCurrentPasswordServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        checkCurrentPasswordServlet = new CheckCurrentPassword();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    void testDoPost() throws Exception {
        // Setup test data
        int userId = 11; // use valid id from your Test DB
        String password = "pwd"; // password for the userId in your Test DB

        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(userId);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        // Execute doPost method
        checkCurrentPasswordServlet.doPost(request, response);

        // Verify output
        printWriter.flush();
        assertTrue(stringWriter.toString().contains("true")); // Assuming password entered is same as in DB.
    }

    @Test
    void testDoPost2() throws Exception {
        // Setup test data
        int userId = 11; // use valid id from your Test DB
        String password = "pwd2"; // password for the userId in your Test DB

        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(userId);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        // Execute doPost method
        checkCurrentPasswordServlet.doPost(request, response);

        // Verify output
        printWriter.flush();
        assertTrue(stringWriter.toString().contains("false")); // Assuming password entered is not the same as in DB.
    }
}
