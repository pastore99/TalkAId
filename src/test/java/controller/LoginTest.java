package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

class LoginTest {

    private Login loginServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        loginServlet = new Login();
        loginServlet.init();

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    //ACCESSO PAZIENTE CORRETTO
    @Test
    void testDoPostWithValidCredentials() throws Exception {
        // Setup test data
        String email = "patient2@example.com";
        String password = "pwd";
        int userId = 13;

        // Set request parameters
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Mock HttpSession
        HttpSession mockSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(mockSession);

        // Execute doPost method
        loginServlet.doPost(request, response);

        // Verify session attributes
        verify(mockSession).setAttribute("id", userId);
        verify(mockSession).setAttribute("type", "patient");

        // Verify redirection
        verify(response).sendRedirect("JSP/homepagepatient.jsp");
    }

    //ACCESSO LOGOPEDISTA CORRETTO
    @Test
    void testDoPostWithValidCredentials2() throws Exception {
        // Setup test data
        String email = "doc1@example.com";
        String password = "pwd";
        int userId = 9;

        // Set request parameters
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);


        // Mock HttpSession
        HttpSession mockSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(mockSession);

        // Execute doPost method
        loginServlet.doPost(request, response);

        // Verify session attributes
        verify(mockSession).setAttribute("id", userId);
        verify(mockSession).setAttribute("type", "therapist");

        // Verify redirection
        verify(response).sendRedirect("JSP/homeTherapist.jsp");
    }

    @Test
    void testDoPostWithInvalidCredentials() throws Exception {
        // Setup test data
        String email = "patient55@example.com";
        String password = "pwd";
        int userId = 13;

        // Set request parameters
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);

        // Mock HttpSession
        HttpSession mockSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(mockSession);

        // Execute doPost method
        loginServlet.doPost(request, response);

        // Verify redirection
        verify(response).sendRedirect("JSP/login.jsp?error=1");
    }

}
