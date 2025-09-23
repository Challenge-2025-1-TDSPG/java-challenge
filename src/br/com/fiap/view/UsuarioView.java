package br.com.fiap.view;

import br.com.fiap.controller.UserController;
import br.com.fiap.model.dto.User;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class UsuarioView {
    public static void main(String[] args) {
        UserController userController = new UserController();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String cpf, nome, email, dataDeNascimento, telefone;
        LocalDate dataNascimento;
        String resultado;
        String menu = """
                1 - Inserir Usuário
                2 - Alterar Usuário
                3 - Excluir Usuário
                4 - Buscar Usuário por CPF
                5 - Listar Todos Usuários
                0 - Sair
                """;

        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            try {
                switch (opcao) {
                    case 1 : { // CREATE
                        cpf = JOptionPane.showInputDialog("Digite o CPF:");
                        nome = JOptionPane.showInputDialog("Digite o Nome:");
                        email = JOptionPane.showInputDialog("Digite o Email:");
                        dataDeNascimento = JOptionPane.showInputDialog("Digite a Data de Nascimento (dd/MM/yyyy):");
                        dataNascimento = LocalDate.parse(dataDeNascimento, dtf);
                        telefone = JOptionPane.showInputDialog("Digite o Telefone (com DDD):");
                        JOptionPane.showMessageDialog(null,
                           userController.createUser(cpf, nome, email, dataNascimento, telefone));
                        break;
                    }
                    case 2 : { // UPDATE
                        cpf = JOptionPane.showInputDialog("Digite o CPF do usuário a alterar:");
                        nome = JOptionPane.showInputDialog("Digite o novo Nome:");
                        email = JOptionPane.showInputDialog("Digite o novo Email:");
                        dataDeNascimento = JOptionPane.showInputDialog("Digite a nova Data de Nascimento (dd/MM/yyyy):");
                        dataNascimento = LocalDate.parse(dataDeNascimento, dtf);
                        telefone = JOptionPane.showInputDialog("Digite o novo Telefone (com DDD):");
                        JOptionPane.showMessageDialog(null, userController.updateUser(cpf, nome, email, dataNascimento, telefone));
                        break;
                    }
                    case 3 : { // DELETE
                        cpf = JOptionPane.showInputDialog("Digite o CPF do usuário a excluir:");
                        JOptionPane.showMessageDialog(null,userController.deleteUser(cpf));
                        break;
                    }
                    case 4 : { // READ ONE
                        cpf = JOptionPane.showInputDialog("Digite o CPF do usuário a buscar:");
                        resultado = userController.readOne(cpf);

                        JOptionPane.showMessageDialog(null, Objects.requireNonNullElse(resultado, "Usuário não encontrado."));
                        break;
                    }
                    case 5 : { // READ ALL
                        ArrayList<User> lista = userController.readAll();
                        if (lista != null && !lista.isEmpty()) {
                            resultado = lista.stream()
                               .map(User::toString)
                               .collect(Collectors.joining("\n"));

                            JOptionPane.showMessageDialog(null, "Usuários cadastrados:\n\n" + resultado);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado.");
                        }
                        break;
                    }
                    case 0 : System.out.println("Saindo..."); break;
                    default : System.out.println("Opção inválida."); break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }

        } while (opcao != JOptionPane.CLOSED_OPTION && opcao != 0);
    }
}
