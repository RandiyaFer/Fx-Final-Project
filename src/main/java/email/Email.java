package email;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class Email {
    private static Email email;
    private Session session;
    private Email(){

    }
    public static Email getInstance(){
        return email!=null ? email:(email=new Email());
    }
    public Session getSession(){
        return session!=null ? session : (session=createSession());
    }
    private Session createSession(){
        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.elasticemail.com");
        props.put("mail.smtp.port", "2525");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Sender and recipient configuration
        String senderEmail = "randiya4fernando@gmail.com";
        //String username = "Randiya Fernando";
        String senderPassword = "BA9A2C351CCD4A42D6E35B9268EB83E7FD16";

        // Create a session with the SMTP server
        return session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });
    }
}
