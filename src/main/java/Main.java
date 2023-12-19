import model.service.email.EmailManager;
import model.service.encryption.Encryption;
import model.DAO.DAOUser;
import model.entity.User;
import model.service.login.Authenticator;

public class Main {
    public static void main(String[] args) {
        Encryption encryption = new Encryption();
        DAOUser db = new DAOUser();
        String plainTextPassword = "123456";
        EmailManager message = new EmailManager();

        /*
        String hashedPassword = encryption.encryptPassword(plainTextPassword);

        // Use hashed password to create new user
        db.createUser("test@gmail.com", hashedPassword, 0);
        */

        /*
        User user = db.getUserByIdOrEmail("test@gmail.com");
        System.out.println(encryption.verifyPassword(plainTextPassword, user.getPassword()));
         */


        //test email
        //message.sendEmail("c.porzio02@gmail.com", "Email Test", "questo è una email di test");

        //test email recupero password
        Authenticator authenticator = new Authenticator();
        System.out.println(authenticator.authenticate("test@gmail.com", "123456"));
    }
}
