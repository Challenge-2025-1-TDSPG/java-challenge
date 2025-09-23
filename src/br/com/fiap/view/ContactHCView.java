package br.com.fiap.view;

import br.com.fiap.controller.ContactHCController;
import br.com.fiap.model.dto.ContactHC;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class ContactHCView {
    public static void main(String[] args) {
        ContactHCController controller = new ContactHCController();
        String title, inPerson, email, tel, schedule, resultado;
        String[] escolha= {"create", "update", "delete", "readOne", "readAll"};
        int opcao;
        do {
            opcao = JOptionPane.showOptionDialog(null, "Escolha uma das opcões abaixo para manipular os contatos","Escolha", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null, escolha, escolha[0]);
            try {
                switch (opcao) {
                    case 0:// CREATE
                        title = JOptionPane.showInputDialog("Digite o nome da unidade:");
                        inPerson = JOptionPane.showInputDialog("Informe o LOCAL de atendimento presencial:");
                        email = JOptionPane.showInputDialog("Digite o Email:");
                        tel = JOptionPane.showInputDialog("Digite o Telefone:");
                        schedule = JOptionPane.showInputDialog("Informe o HORÁRIO de atendimento:");
                        JOptionPane.showMessageDialog(null, controller.create(title, inPerson, email, tel, schedule));
                        break;
                    case 1:// UPDATE
                        title = JOptionPane.showInputDialog("Digite o nome da unidade (existente) para alterar:");
                        inPerson = JOptionPane.showInputDialog("Digite o novo LOCAL de atendimento presencial:");
                        email = JOptionPane.showInputDialog("Digite o novo Email:");
                        tel = JOptionPane.showInputDialog("Digite o novo Telefone:");
                        schedule = JOptionPane.showInputDialog("Digite o HORÁRIO de atendimento:");
                        JOptionPane.showMessageDialog(null, controller.update(title, inPerson, email, tel, schedule));
                        break;
                    case 2 :     // DELETE
                        title = JOptionPane.showInputDialog("Digite o Título da unidade excluir:");
                        JOptionPane.showMessageDialog(null, controller.delete(title));
                        break;
                    case 3 :// READ ONE
                        title = JOptionPane.showInputDialog("Digite o Título da unidade a buscar:");
                        resultado = controller.readOne(title);
                        JOptionPane.showMessageDialog(null, Objects.requireNonNullElse(resultado, "Contato não encontrado."));
                        break;

                    case 4 : // READ ALL
                        ArrayList<ContactHC> lista = controller.readAll();
                        if (lista != null && !lista.isEmpty()) {
                            resultado = lista.stream()
                               .map(ContactHC::toString)
                               .collect(Collectors.joining("\n"));
                            JOptionPane.showMessageDialog(null, "Contatos cadastrados:\n\n" + resultado);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum contato cadastrado.");
                        }
                        break;
                    default :
                        JOptionPane.showMessageDialog(null, "Opção inválida.");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }

        } while (opcao != JOptionPane.CLOSED_OPTION && opcao != 0);
    }
}
