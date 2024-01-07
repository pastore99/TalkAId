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

class CountMessagesTest {

    private CountMessages countMessagesServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        countMessagesServlet = new CountMessages();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    void testDoGet() throws Exception {
        int userId = 11;
        //Assume that userId 11 has 5 messages in your test Database

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(userId);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        countMessagesServlet.doGet(request, response);

        printWriter.flush();
        assertTrue(stringWriter.toString().contains("5")); //Assuming 5 is the count of messages for userId 11.
    }
}