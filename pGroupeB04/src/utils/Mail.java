package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    /**
     * Will send an email via an email address given in parameter by thanking
     * the person for subscribing to the newsletter
     * @param email email of the person you want sent the email
     */
    public static void sendMail(String email) {

        final String username = "battleofknowledges@gmail.com";
        final String password = "Battle123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("subscribe newsletters");
            message.setText("Welcome"
                    + "\n\nThank you for subscribing");

            Transport.send(message);

            System.out.println("Email send");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
