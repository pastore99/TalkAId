package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.service.login.Authenticator;

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
            response.sendRedirect("welcome.jsp");
        } else {
            // Login failed, redirect back to the login page
            response.sendRedirect("JSP/login.jsp");
        }
    }
}