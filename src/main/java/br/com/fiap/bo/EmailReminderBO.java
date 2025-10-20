package br.com.fiap.bo;

import br.com.fiap.service.EmailService;
import br.com.fiap.to.EmailReminderTO;

public class EmailReminderBO {
    private final EmailService service = new EmailService();

    public String enviar(String destinatario) {
        try {
            EmailReminderTO email= new EmailReminderTO();

            email.setAssunto("LumaHC | Lembrete da sua consulta médica");
            email.setCorpo("""
                Olá, este é um lembrete da sua consulta agendada.

                📅 Data: 25/09/2025
                ⏰ Horário: 14h30
                🏥 Local: Hospital das Clínicas - Unidade IMREA VILA MARIANA

                Caso precise reagendar, entre em contato com nossa equipe.
                Atenciosamente,
                Equipe LumaHC
                """);
            email.setDestinatario(destinatario);

            service.enviarEmail(email);
            return "E-mail enviado com sucesso!";
        } catch (Exception e) {
            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }
}
