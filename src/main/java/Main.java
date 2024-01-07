import model.service.schedule.ScheduleManager;
import model.service.email.EmailManager;
import model.service.encryption.Encryption;
import model.DAO.DAOUser;
import model.entity.User;
import model.service.login.Authenticator;
//import model.service.message.Conversation;
import model.service.registration.Registration;

public class Main {
    public static void main(String[] args) {
        //Authenticator auth = new Authenticator();
        //Registration r = new Registration();

        //System.out.println(auth.authenticate("patient1@example.com", "pwd"));
        //System.out.println(auth.authenticate("patient1@example.com", "pwd"));
        //r.invitePatient(9, "thenicrodeath@gmail.com", "Raffaele", "Monti");

        ScheduleManager manager = new ScheduleManager();
        System.out.println(manager.checkData(9, "1999-10-08", "10:00-11:00"));
    }
}
