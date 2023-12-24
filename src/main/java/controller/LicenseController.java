package controller;

import model.DAO.DAOLicense;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/license")
public class LicenseController extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        DAOLicense dao = new DAOLicense();
        String license = dao.generateLicense();


        if(license!=null){
            request.setAttribute("license", license);
            request.getRequestDispatcher("testingInvitationAndLicense.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        DAOLicense dao = new DAOLicense();
        String invitation = dao.generateInvitation((int)s.getAttribute("id"));

        if(invitation!=null){
            request.setAttribute("invitation", invitation);
            request.getRequestDispatcher("testingInvitationAndLicense.jsp").forward(request, response);
        }


    }
}