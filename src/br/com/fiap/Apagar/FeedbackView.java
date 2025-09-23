package br.com.fiap.Apagar;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class FeedbackView {
    public static void main(String[] args) {
        FeedbackController controller = new FeedbackController();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Variáveis declaradas antes
        int id;
        String description, cpfUser, sentAtStr;
        LocalDate sentAt;
        String result;

        String menu = """
                1 - Inserir Feedback
                2 - Atualizar Feedback
                3 - Excluir Feedback
                4 - Buscar Feedback por ID
                0 - Sair
                """;

        int option;
        do {
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));

            try {
                switch (option) {
                    case 1 : { // CREATE
                        description = JOptionPane.showInputDialog("Digite a descrição do feedback:");
                        cpfUser = JOptionPane.showInputDialog("Digite o CPF do usuário (opcional):");
                        JOptionPane.showMessageDialog(null,
                           controller.createFeedback(description, cpfUser));
                        break;
                    }
                    case 2 :{ // UPDATE
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Feedback para atualizar:"));
                        description = JOptionPane.showInputDialog("Digite a nova descrição:");
                        sentAtStr = JOptionPane.showInputDialog("Digite a nova data de envio (dd/MM/yyyy):");
                        sentAt = LocalDate.parse(sentAtStr, dtf);
                        cpfUser = JOptionPane.showInputDialog("Digite o novo CPF do usuário:");
                        JOptionPane.showMessageDialog(null,
                           controller.updateFeedback(id, description, sentAt, cpfUser));
                        break;
                    }
                    case 3 : { // DELETE
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Feedback para excluir:"));
                        JOptionPane.showMessageDialog(null,
                           controller.deleteFeedback(id));
                        break;
                    }
                    case 4 : { // READ ONE
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Feedback para buscar:"));
                        result = controller.readOneFeedback(id);
                        JOptionPane.showMessageDialog(null,
                           Objects.requireNonNullElse(result, "Feedback não encontrado."));
                        break;
                    }
                    case 0 : JOptionPane.showMessageDialog(null, "Saindo..."); break;
                    default : JOptionPane.showMessageDialog(null, "Opção inválida.");break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }

        } while (option != JOptionPane.CLOSED_OPTION && option != 0);
    }
}
