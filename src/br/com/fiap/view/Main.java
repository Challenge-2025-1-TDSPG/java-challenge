package br.com.fiap.view;

import br.com.fiap.Apagar.FeedbackView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String menu = """
                === MENU PRINCIPAL ===
                
                1 - Gerenciar Usuários
                2 - Gerenciar Contatos (HC)
                3 - Gerenciar Feedbacks
                4 - Fazer Login
                0 - Sair
                """;

        int opcao;
        do {
            try {
                opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

                switch (opcao) {
                    case 1 -> UsuarioView.main(null);
                    case 2 -> ContactHCView.main(null);
                    case 3 -> FeedbackView.main(null);
                    case 4 -> LoginView.main(null);
                    case 0 -> JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                    default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                opcao = -1;
            }

        } while (opcao != 0 && opcao != JOptionPane.CLOSED_OPTION);
    }
}