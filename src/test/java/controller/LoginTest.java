package controller;

import com.mockrunner.mock.web.MockHttpServletRequest;
import com.mockrunner.mock.web.MockHttpServletResponse;
import com.mockrunner.mock.web.MockHttpSession;
import com.mockrunner.mock.web.WebMockObjectFactory;
import com.mockrunner.servlet.ServletTestModule;
import com.mockrunner.struts.BasicActionTestCaseAdapter;
import model.service.login.Authenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class LoginControllerTest extends BasicActionTestCaseAdapter {


    private ServletTestModule tester;
    private Login loginController;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        tester = createServletTestModule();
        loginController = new Login();
        loginController.init();
        tester.setServlet(loginController, "/login");
    }

    private ServletTestModule createServletTestModule() {
        // Assuming you are initializing your ServletTestModule here.
        ServletTestModule tester = new ServletTestModule(getActionServlet());
        return tester;
    }


    @Test
    public void testLoginSuccess() throws Exception {
        MockHttpServletRequest request = tester.createMockRequest();
        MockHttpServletResponse response = tester.createMockResponse();
        MockHttpSession session = tester.createMockSession();

        request.setRequestURI("/login");
        request.setMethod("POST");
        request.setupAddParameter("email", "test@example.com");
        request.setupAddParameter("password", "testing123");
        request.setSession(session);

        Authenticator mockAuthService = mock(Authenticator.class);
        when(mockAuthService.authenticate(anyString(), anyString())).thenReturn(1);
        loginController.authService = mockAuthService;

        // Add additional mocks if needed.
        // For example, you might want to mock the userData.getUser() and userReg.getPersonalInfo() calls within setSessionAttributes() method.

        tester.doPost(request, response);
        assertEquals("JSP/welcome.jsp", response.getRedirectURL());
    }

    @Test
    public void testLoginFailure() throws Exception {
        MockHttpServletRequest request = tester.createMockRequest();
        MockHttpServletResponse response = tester.createMockResponse();
        MockHttpSession session = tester.createMockSession();

        request.setRequestURI("/login");
        request.setMethod("POST");
        request.setupAddParameter("email", "fail@example.com");
        request.setupAddParameter("password", "testing123");
        request.setSession(session);

        Authenticator mockAuthService = mock(Authenticator.class);
        when(mockAuthService.authenticate(anyString(), anyString())).thenReturn(0);
        loginController.authService = mockAuthService;

        tester.doPost(request, response);
        assertEquals("JSP/login.jsp?error=1", response.getRedirectURL());
    }
}