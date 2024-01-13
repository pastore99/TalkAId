package controller;

import model.service.login.Authenticator;
import model.service.user.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScheduleManagerTest
{

    @InjectMocks
    private ScheduleManager servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session = mock(HttpSession.class);

    @Mock
    private UserData userData;

    @Mock
    private Authenticator authenticator;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateScheduleCorrectDate() throws ServletException, IOException
    {

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(9);
        when(request.getParameter("action")).thenReturn("createNewSchedule");
        when(request.getParameter("date")).thenReturn("2024-02-08");
        when(request.getParameter("timeslot")).thenReturn("10:00-11:00");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request,response);

        verify(response).sendRedirect("JSP/schedule.jsp");
    }

    @Test
    void testPrenoteScheduleDate() throws ServletException, IOException
    {

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(9);
        when(request.getParameter("action")).thenReturn("prenoteSchedule");
        when(request.getParameter("idTherapist")).thenReturn("9");
        when(request.getParameter("date")).thenReturn("2024-02-08");
        when(request.getParameter("timeslot")).thenReturn("10:00-11:00");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request,response);

        verify(response).sendRedirect("JSP/schedule.jsp");
    }

    @Test
    void testUnPrenoteScheduleDate() throws ServletException, IOException
    {

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(9);
        when(request.getParameter("password")).thenReturn("pwd");
        when(request.getParameter("action")).thenReturn("unprenoteSchedule");
        when(request.getParameter("idTherapist")).thenReturn("9");
        when(request.getParameter("date")).thenReturn("2024-02-08");
        when(request.getParameter("timeslot")).thenReturn("10:00-11:00");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request,response);

        verify(response).sendRedirect("JSP/schedule.jsp");
    }

    @Test
    void testDeleteScheduleDate() throws ServletException, IOException
    {

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(9);
        when(request.getParameter("password")).thenReturn("pwd");
        when(request.getParameter("action")).thenReturn("deleteSchedule");
        when(request.getParameter("date")).thenReturn("2024-02-08");
        when(request.getParameter("timeslot")).thenReturn("10:00-11:00");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request,response);

        verify(response).sendRedirect("JSP/schedule.jsp");
    }

    /*
    @Test
    void testCreateScheduleIncorrectDate() throws ServletException, IOException
    {

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(9);
        when(request.getParameter("action")).thenReturn("createNewSchedule");
        when(request.getParameter("date")).thenReturn("1999-10-08");
        when(request.getParameter("timeslot")).thenReturn("10:00-11:00");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request,response);

        verify(request, times(1)).setAttribute("errorMessage", "La data selezionata non è valida. Seleziona una data non esistente perfavore.");
        verify(response).sendRedirect("JSP/schedule.jsp");
    }
    */

}