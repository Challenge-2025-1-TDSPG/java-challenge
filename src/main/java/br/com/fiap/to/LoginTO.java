package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

import javax.swing.*;
import java.time.LocalDate;

public class LoginTO {
    // Atributos
    @NotBlank
    private String cpf;
    @NotBlank
    private LocalDate passwordDate;

    //getter e setter
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        String cpfLimpo = cpf.replace(".", "").replace("-", "");
        if (UserTO.validarCpf(cpfLimpo)) {
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
