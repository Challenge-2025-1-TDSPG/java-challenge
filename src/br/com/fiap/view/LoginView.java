package br.com.fiap.view;

import br.com.fiap.controller.LoginController;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoginView {
    public static void main(String[] args) {
        String cpf, dataNascimentoStr;
        LocalDate dataNascimento;
        LoginController loginController = new LoginController();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            cpf = JOptionPane.showInputDialog("Digite o CPF:");
            dataNascimentoStr = JOptionPane.showInputDialog("Digite a Data de Nascimento (dd/MM/yyyy):");
            dataNascimento = LocalDate.parse(dataNascimentoStr, dtf);

            boolean autenticado = loginController.autenticarLogin(cpf, dataNascimento);

            if (autenticado) {
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "CPF ou Data de Nascimento incorretos.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());;
        }
    }
}
