package br.com.fiap.service;

import br.com.fiap.to.ReminderTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

@ApplicationScoped
public class EmailService {
    private final String emailUser = System.getenv("EMAIL_USER");
    private final String passwordEmail = System.getenv("PASSWORD_EMAIL");

    public void enviarEmail(ReminderTO email) throws MessagingException {
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        if (emailUser == null || passwordEmail == null) {
            throw new IllegalStateException("As variáveis de ambiente EMAIL_USER e PASSWORD_EMAIL não estão definidas no arquivo .env.");
        }

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailUser, passwordEmail);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailUser));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getDestinatario()));
        message.setSubject(email.getAssunto());
        message.setText(email.getCorpo());

        Transport.send(message);
    }
}