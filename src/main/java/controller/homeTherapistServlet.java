package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import model.entity.PersonalInfo;
import model.entity.*;

@WebServlet("/homeTherapistServlet")
public class homeTherapistServlet extends HttpServlet {

    public homeTherapistServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        model.service.user.UserData userService = new model.service.user.UserData();

        ArrayList<UserInfo> list_user=new ArrayList<>();
        //String tipo=request.getParameter("type");
        User u= userService.getUserByIdOrEmail(session.getAttribute("user_logged")); //save in session attribute the id of the therapist by email
        list_user=userService.getUsersAndPersonalInfoByIdTherapist((Integer) u.getId()); //save all patient of X therapist


        session.setAttribute("list_user",list_user);


        response.sendRedirect("testTable.jsp");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
