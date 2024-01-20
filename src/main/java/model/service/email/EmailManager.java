package model.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Questa classe provvede alle funzionalità per inviare email.
 * Legge la configurazione dal file properties chiamato 'email.properties'.
 */
public class EmailManager implements EmailManagerInterface{
    private static final Logger logger = LoggerFactory.getLogger(EmailManager.class);

    private static final String EMAIL_PROPERTIES = "/email.properties";
    private static final String ERROR_MESSAGE = "Error loading configuration file: ";
    private static final String HOST = "smtp.gmail.com";

    private Properties emailProps;

    /**
     * Costruttore per la classe EmailManager.
     * Inizializza le proprietà delle email caricandole dal file delle proprietà.
     */
    public EmailManager() {
        this.emailProps = loadEmailProperties();
    }

    /**
     * Questo metodo invia un'email a un address destinatario, con uno specifico oggetto e corpo testo.
     * @param toAddress il destinatario della mail.
     * @param subject l'oggetto della mail.
     * @param body il corpo della mail.
     */
    public void sendEmail(String toAddress, String subject, String body) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);

        try {
            generateMessage(message, toAddress, subject, body);
            sendEmail(session, message);
        } catch (MessagingException me) {
            logger.error("Error sending mail", me);
        }
    }

    /**
     * Il metodo restituisce una sessione per le interazioni delle email, usando le proprietà caricate e autenticando l'utente.
     * @return un oggetto Session.
     */
    private Session getSession() {
        return Session.getDefaultInstance(emailProps, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProps.getProperty("email.string"), emailProps.getProperty("email.pw"));
            }
        });
    }

    /**
     * Questo metodo invia un messaggio email preconfigurato, usando una specifica sessione.
     * @param session l'oggetto Session.
     * @param message l'oggetto MimeMessage, contiene le informazioni inerenti al messaggio da inviare.
     * @throws MessagingException se l'operazione d'invio fallisce.
     */
    private void sendEmail(Session session, MimeMessage message) throws MessagingException {
        Transport transport = session.getTransport("smtp");
        String host = HOST;
        String email = emailProps.getProperty("email.string");
        String password = emailProps.getProperty("email.pw");
        try {
            transport.connect(host, email, password);
        } catch(MessagingException e) {
            logger.error("Error sending mail", e);
        }
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    /**
     * This method generates a MimeMessage, filling it with the necessary information.
     * @param message the MimeMessage object to fill with information
     * @param toAddress the destination address of the email
     * @param subject the subject of the email
     * @param body the body of the email
     * @throws MessagingException if the setting operation fails
     */
    private void generateMessage(MimeMessage message, String toAddress, String subject, String body) throws MessagingException {
        message.setFrom(new InternetAddress(emailProps.getProperty("email.string")));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        message.setSubject(subject);
        message.setText(body);
    }

    /**
     * This method loads the email properties from a resource file named 'email.properties'.
     * @return a Properties object, holding the email properties
     */
    private Properties loadEmailProperties() {
        Properties props = new Properties();
        try (InputStream input = EmailManager.class.getResourceAsStream(EMAIL_PROPERTIES)) {
            props.load(input);
        } catch (IOException e) {
            logger.error("Error loading Properties", e);
        }

        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.checkserveridentity", "true");
        return props;
    }
}