package controller;

import model.DAO.DAOLicense;
import model.DAO.DAOPersonalInfo;
import model.DAO.DAOUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationTest {

    private Registration registrationServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws IOException {
        registrationServlet = new Registration();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testCorrectlyRegistered() throws IOException {
        // Set up parameters for the request
        String licenseCode = new DAOLicense().generateLicense();
        when(request.getParameter("licenseCode")).thenReturn(licenseCode);
        when(request.getParameter("email")).thenReturn("test@test.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("name")).thenReturn("Testo");
        when(request.getParameter("surname")).thenReturn("Tutto");
        HttpSession mockSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(mockSession);
        // Call the method under test
        registrationServlet.doPost(request, response);

        // Verify the response
        assertEquals(stringWriter.toString(), "0");

        //pulizia
        new DAOLicense().deleteLicense(licenseCode);
        new DAOPersonalInfo().deleteRegistry(new DAOUser().getUserByIdOrEmail("test@test.com").getId());
        new DAOUser().deleteUserByIdOrEmail("test@test.com");
    }

    @Test
    public void testWrongRegistration() throws IOException {
        // Set up parameters for the request
        when(request.getParameter("licenseCode")).thenReturn("123456");
        when(request.getParameter("email")).thenReturn("test@test.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("name")).thenReturn("Testo");
        when(request.getParameter("surname")).thenReturn("Tutto");
        HttpSession mockSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(mockSession);
        // Call the method under test
        registrationServlet.doPost(request, response);

        // Verify the response
        assertEquals(stringWriter.toString(), "1");

    }

    @Test
    public void testAlreadyTakenEmail() throws IOException {
        // Set up parameters for the request
        String licenseCode = new DAOLicense().generateLicense();
        when(request.getParameter("licenseCode")).thenReturn(licenseCode);
        when(request.getParameter("email")).thenReturn("doc1@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("name")).thenReturn("Testo");
        when(request.getParameter("surname")).thenReturn("Tutto");
        HttpSession mockSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(mockSession);
        // Call the method under test
        registrationServlet.doPost(request, response);

        // Verify the response
        assertEquals(stringWriter.toString(), "2");
        new DAOLicense().deleteLicense(licenseCode);
    }

}