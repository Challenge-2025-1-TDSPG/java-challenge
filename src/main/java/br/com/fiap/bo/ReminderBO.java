package br.com.fiap.bo;


import br.com.fiap.dao.ReminderDAO;
import br.com.fiap.service.EmailService;
import br.com.fiap.service.SmsService;
import br.com.fiap.to.ReminderTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.scheduler.Scheduled;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ReminderBO {
    @Inject
    EmailService emailService;
    @Inject
    SmsService smsService;
    @Inject
    ReminderDAO reminderDAO;

    public ArrayList<ReminderTO> findAll() {
        reminderDAO = new ReminderDAO();
        return reminderDAO.findAll();
    }

    public ReminderTO findByCodigo (Long id) {
        reminderDAO = new ReminderDAO();

        return reminderDAO.findByCodigo(id);
    }

    public ReminderTO save(ReminderTO reminder){
        reminderDAO = new ReminderDAO();
        return reminderDAO.save(reminder);
    }

    public boolean delete(Long id){
        reminderDAO = new ReminderDAO();
        return  reminderDAO.delete(id);
    }

    public ReminderTO update(ReminderTO reminder){
        reminderDAO = new ReminderDAO();
        return reminderDAO.update(reminder);
    }

    @Scheduled(every = "1h")
    public void sendReminders() {
        emailService = new EmailService();
        smsService = new SmsService();
        List<ReminderTO> reminders = reminderDAO.findReminders();

        if (reminders.isEmpty()) {
            System.out.println("Nenhum lembrete dentro dos próximos 7 dias.");
            return;
        }


        for (ReminderTO reminder : reminders) {
            // EMAIL
            ReminderTO email = new ReminderTO();
            email.setDestinatario(reminder.getDestinatario());
            email.setAssunto("LumaHC | Lembrete da sua consulta médica");

            String corpoEmail = String.format("""
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

            email.setCorpo(corpoEmail);

            //SMS
            String mensagemSMS = String.format("i\n" +
                            "Olá, este é um lembrete da sua consulta agendada.\n\n" +
                            "📅 Data: %s\n" +
                            "⏰ Horário: %s\n" +
                            "🏥 Local: Hospital das Clínicas - Unidade IMREA VILA MARIANA",
                    reminder.getDateReminder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    reminder.getTimeReminder().format(DateTimeFormatter.ofPattern("HH:mm"))
            );


            try {
                emailService.enviarEmail(email);
                smsService.send(reminder.getNumberReminder(), mensagemSMS);

                System.out.println("E-mail enviado para " + reminder.getDestinatario());
                System.out.println("SMS enviado para " + reminder.getNumberReminder());

            } catch (Exception e) {
                System.out.println("Erro ao enviar e-mail e sms para " + reminder.getDestinatario() + reminder.getNumberReminder() + ": " + e.getMessage());
            }

        }
    }

}
