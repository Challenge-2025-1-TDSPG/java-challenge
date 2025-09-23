package br.com.fiap.controller;

import br.com.fiap.model.dto.Email;
import br.com.fiap.service.EmailService;

public class EmailController {

    private final EmailService service = new EmailService();
    Email email;
    public String enviar(String destinatario,String assunto,String corpo) {
        try {
            email = new Email(destinatario, assunto, corpo);
            service.enviarEmail(email);
            return "E-mail enviado com sucesso!";
        } catch (Exception e) {
            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }
}
