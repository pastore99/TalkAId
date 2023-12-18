package model.service.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailManager {

    private static final String EMAIL_PROPERTIES = "/email.properties";
    private static final String ERROR_MESSAGE = "Error loading configuration file: ";
    private static final String HOST = "smtp.gmail.com";

    private Properties emailProps;

    public EmailManager() {
        this.emailProps = loadEmailProperties();
    }

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

    private Session getSession() {
        return Session.getDefaultInstance(emailProps, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProps.getProperty("email.string"), emailProps.getProperty("email.pw"));
            }
        });
    }

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

    private void generateMessage(MimeMessage message, String toAddress, String subject, String body) throws MessagingException {
        message.setFrom(new InternetAddress(emailProps.getProperty("email.string")));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        message.setSubject(subject);
        message.setText(body);
    }

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
