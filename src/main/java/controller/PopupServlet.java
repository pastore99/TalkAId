package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PopupServlet")
public class PopupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Esegui qui la logica per aggiornare il messaggio del popup
        String updatedMessage = "Nuovo messaggio dalla servlet";

        response.setContentType("JSP/test_popup.jsp");
        response.getWriter().write(updatedMessage);
    }
}

