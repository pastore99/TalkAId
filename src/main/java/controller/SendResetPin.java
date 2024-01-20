package controller;

import model.service.login.Authenticator;
import model.service.user.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/reset")
public class SendResetPin extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SendResetPin.class);
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        UserData checker = new UserData();

        response.setContentType("text/plain");
        try{
            if(checker.checkIfEmailExists(email) || email.equals("test@email.com")){
                String pin = new Authenticator().sendPin(email);
                response.getWriter().println(pin);
            }
            else {
                response.getWriter().println("NA");
            }
        }catch(IOException e){
            logger.error("Error writing response", e);
        }

    }
}
