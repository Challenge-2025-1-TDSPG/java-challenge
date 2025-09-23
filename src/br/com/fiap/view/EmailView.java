package br.com.fiap.view;

import br.com.fiap.controller.EmailController;

import javax.swing.*;

public class EmailView {
    public static void main(String[] args) {
        String destinatario = JOptionPane.showInputDialog("Digite o e-mail do destinatário:");
        String assunto = "LumaHC | Lembrete da sua consulta médica";
        String corpo = """
                Olá, este é um lembrete da sua consulta agendada.

                📅 Data: 25/09/2025
                ⏰ Horário: 14h30
                🏥 Local: Hospital das Clínicas - Unidade IMREA VILA MARIANA

                Caso precise reagendar, entre em contato com nossa equipe.
                Atenciosamente,
                Equipe LumaHC
                """;

        EmailController controller = new EmailController();
        String resultado = controller.enviar(destinatario, assunto, corpo);

        JOptionPane.showMessageDialog(null, resultado);
    }
}
