package br.com.fiap.service;

import br.com.fiap.to.ReminderTO;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService {
    private static final Dotenv dotenv = Dotenv.load();

    private final String EMAIL_USER = dotenv.get("EMAIL_USER");
    private final String PASSWORD_EMAIL = dotenv.get("PASSWORD_EMAIL");

    public void enviarEmail(ReminderTO email) throws MessagingException {
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USER, PASSWORD_EMAIL);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL_USER));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getDestinatario()));
        message.setSubject(email.getAssunto());
        message.setText(email.getCorpo());

        Transport.send(message);
    }
}