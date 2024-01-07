package model.service.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class provides functionality for sending email messages.
 * It reads the configuration from a properties file named 'email.properties'.
 */
public class EmailManager implements EmailManagerInterface{

    private static final String EMAIL_PROPERTIES = "/email.properties";
    private static final String ERROR_MESSAGE = "Error loading configuration file: ";
    private static final String HOST = "smtp.gmail.com";

    private Properties emailProps;

    /**
     * Constructor for the EmailManager class.
     * It initializes the email properties by loading them from a properties file.
     */
    public EmailManager() {
        this.emailProps = loadEmailProperties();
    }

    /**
     * This method sends an email to a given address, with a specified subject and body.
     * @param toAddress the destination address of the email
     * @param subject the subject of the email
     * @param body the body of the email
     */
    public void sendEmail(String toAddress, String subject, String body) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);

        try {
            generateMessage(message, toAddress, subject, body);
            sendEmail(session, message);
            System.out.println("Email sent successfully to " + toAddress);
        } catch (MessagingException me) {
            System.out.println("Cannot send email to " + toAddress);
        }
    }

    /**
     * This method returns a session for email interactions, using the loaded properties and authenticating the user.
     * @return a Session object
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
     * This method sends a preconfigured email message, using a specified session.
     * @param session the Session object
     * @param message the MimeMessage object, holding the information about the message to send
     * @throws MessagingException if the send operation fails
     */
    private void sendEmail(Session session, MimeMessage message) throws MessagingException {
        Transport transport = session.getTransport("smtp");
        String host = HOST;
        String email = emailProps.getProperty("email.string");
        String password = emailProps.getProperty("email.pw");
        try {
            transport.connect(host, email, password);
        } catch(MessagingException e) {
            System.out.println(e);
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
            System.out.println(ERROR_MESSAGE + e.getMessage());
        }

        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return props;
    }
}