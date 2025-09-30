package br.com.fiap.service;

import br.com.fiap.model.dto.Email;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService {

    private final String usuario = "lumahc.suporte@gmail.com";
    private final String senha = "bwli zgdw otfq chaf";

    public void enviarEmail(Email email) throws MessagingException {
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(usuario));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getDestinatario()));
        message.setSubject(email.getAssunto());
        message.setText(email.getCorpo());

        Transport.send(message);
    }
}
