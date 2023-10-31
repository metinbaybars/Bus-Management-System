import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MailService {
    //Method that send an email to receiver who books the ticket
    public void sendMail(String recipient, String content) throws Exception {

        //This will be used for the configuring the email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.sendgrid.net");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");

        //Email address and the password for the sender account
        String fromEmail = "info.busmanagement.ieu@gmail.com";

        //Logs in to sender account
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("apikey", "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");
            }
        });

        //Creates the message that will be send
        Message message = prepareMessage(session, fromEmail, recipient, content);

        //Sends email
        Transport.send(message);
    }

    //Method that prepares mail content, sets the sender and receiver account
    private Message prepareMessage(Session session, String myAccountEmail, String recipient, String content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Booking is successful");
            message.setText(content);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
