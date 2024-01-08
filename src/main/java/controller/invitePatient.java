package controller;

        import model.service.condition.ConditionManager;
        import model.service.registration.Registration;

        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;


@WebServlet("/invitePatient")
public class invitePatient extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        model.service.registration.Registration registration=new Registration();
        registration.invitePatient((Integer) session.getAttribute("id"),(String) request.getParameter("email"),(String) request.getParameter("nome"),(String) request.getParameter("cognome"));
        response.sendRedirect("JSP/homeTherapist.jsp");

    }

}
