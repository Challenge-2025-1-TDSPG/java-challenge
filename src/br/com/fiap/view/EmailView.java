package br.com.fiap.view;

import br.com.fiap.controller.EmailController;

import javax.swing.*;

public class EmailView {
    public static void main(String[] args) {
        String destinatario = JOptionPane.showInputDialog("Digite o e-mail do destinatário:");

        EmailController controller = new EmailController();
        String resultado = controller.enviar(destinatario);

        JOptionPane.showMessageDialog(null, resultado);
    }
}
