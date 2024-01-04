import model.service.email.EmailManager;
import model.service.encryption.Encryption;
import model.DAO.DAOUser;
import model.entity.User;
import model.service.login.Authenticator;
//import model.service.message.Conversation;
import model.service.registration.Registration;

public class Main {
    public static void main(String[] args) {
        Authenticator auth = new Authenticator();
        System.out.println(auth.authenticate("patient1@example.com", "pwd"));
        System.out.println(auth.authenticate("patient1@example.com", "pwd"));

    }
}
