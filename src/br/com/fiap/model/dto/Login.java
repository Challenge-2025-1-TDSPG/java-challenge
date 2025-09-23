package br.com.fiap.model.dto;

import javax.swing.*;
import java.time.LocalDate;

public class Login {
    // Atributos
    private String cpf;
    private LocalDate passwordDate;

    //getter e setter
    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        String cpfLimpo = cpf.replace(".", "").replace("-", "");
        if (User.validarCpf(cpfLimpo)) {
            this.cpf = cpfLimpo;
        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido.");
        }
    }

    public LocalDate getPasswordDate() {
        return passwordDate;
    }

    public void setPasswordDate(LocalDate passwordDate) {
        this.passwordDate = passwordDate;
    }
}
