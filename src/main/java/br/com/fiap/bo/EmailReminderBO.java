package br.com.fiap.bo;


import br.com.fiap.dao.EmailReminderDAO;
import br.com.fiap.service.EmailService;
import br.com.fiap.to.EmailReminderTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.scheduler.Scheduled;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmailReminderBO {
    @Inject
    private final EmailService service = new EmailService();
    @Inject
    private EmailReminderDAO reminderEmail;

    public ArrayList<EmailReminderTO> findAll() {
        reminderEmail = new EmailReminderDAO();
        // Lógica de negócio pode ser adicionada aqui
        return reminderEmail.findAll();
    }

    public EmailReminderTO findByCodigo (Long id) {
        reminderEmail = new EmailReminderDAO();

        return reminderEmail.findByCodigo(id);
    }

    public EmailReminderTO save(EmailReminderTO reminder){
        reminderEmail = new EmailReminderDAO();
        return reminderEmail.save(reminder);
    }

    public boolean delete(Long id){
        reminderEmail = new EmailReminderDAO();
        return  reminderEmail.delete(id);
    }

    @Scheduled(every = "24h")
    public void sendReminders() {
        List<EmailReminderTO> reminders = reminderEmail.findReminders7DaysAhead();

        if (reminders.isEmpty()) {
            System.out.println("Nenhum lembrete dentro dos próximos 7 dias.");
            return;
        }

        for (EmailReminderTO reminder : reminders) {
            EmailReminderTO email = new EmailReminderTO();
            email.setDestinatario(reminder.getDestinatario());
            email.setAssunto("LumaHC | Lembrete da sua consulta médica");

            String corpo = String.format("""
                Olá, este é um lembrete da sua consulta agendada.

                📅 Data: %s
                ⏰ Horário: %s
                🏥 Local: Hospital das Clínicas - Unidade IMREA VILA MARIANA

                Caso precise reagendar, entre em contato com nossa equipe.
                Atenciosamente,
                Equipe LumaHC
                """,
               reminder.getDateReminder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
               reminder.getTimeReminder().format(DateTimeFormatter.ofPattern("HH:mm"))
            );

            email.setCorpo(corpo);

            try {
                service.enviarEmail(email);
            } catch (Exception e) {
                System.out.println("Erro ao enviar e-mail para " + reminder.getDestinatario() + ": " + e.getMessage());
            }
        }
    }

}
