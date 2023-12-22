package controller;

import model.DAO.DAOUser;
import model.entity.User;
import model.service.login.Authenticator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    /**
     Your AuthenticationService instance should go here
     **/
    private Authenticator authService;

    public void init() {
        this.authService = new Authenticator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email);
        System.out.println(password);
        int result = authService.authenticate(email, password);

        if (result > 0) {
            // Login success, redirect to the welcome page
            HttpSession session = request.getSession();
            DAOUser daouser=new DAOUser();
            User user_logged= daouser.getUserByIdOrEmail(email);
            session.setAttribute("user_logged", user_logged);
            response.sendRedirect("JSP/welcome.jsp");
        } else {
            // Login failed, redirect back to the login page
            response.sendRedirect("JSP/login.jsp?error=1");
        }
    }
}