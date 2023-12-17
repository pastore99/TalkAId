import model.service.encryption.Encryption;
import model.DAO.DAOUser;
import model.entity.User;

public class Main {
    public static void main(String[] args){
        Encryption encryption = new Encryption();
        DAOUser db = new DAOUser();
        String plainTextPassword = "123456";

        /**
        String hashedPassword = encryption.encryptPassword(plainTextPassword);

        // Use hashed password to create new user
        db.createUser("test@gmail.com", hashedPassword, 0);
        */
        User user = db.getUserByIdOrEmail("test@gmail.com");
        System.out.println(encryption.verifyPassword(plainTextPassword, user.getPassword()));
    }
}
