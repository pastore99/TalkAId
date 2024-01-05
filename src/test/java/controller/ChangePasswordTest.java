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

class ChangePasswordTest {

    private ChangePassword changePasswordServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        changePasswordServlet = new ChangePassword();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    void testDoPost() throws Exception {
        String password = "pwd";
        int userId = 13; // use valid id from your Test DB

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(userId);
        when(request.getParameter("password")).thenReturn(password);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        changePasswordServlet.doPost(request, response);

        printWriter.flush();
        assertTrue(stringWriter.toString().contains("true"));
    }
}
