package br.com.fiap.model.dto;

import javax.swing.*;
import java.time.LocalDate;

public class User {
    //atributos
    private String cpf;
    private String name;
    private String email;
    private LocalDate dataDeNascimento;
    private String telefone;


    //construtores
    public User() {
    }

    //setter/getter
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        String cpfLimpo = cpf.replace(".", "").replace("-", "");
        if (validarCpf(cpfLimpo)) {
            this.cpf = cpfLimpo;
        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validarEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
    }

    public void setTelefone(String telefone) {
        String telLimpo = telefone.replaceAll("\\D", "");
        if (validarTelefone(telLimpo)) {
            this.telefone = telLimpo;
        } else {
            throw new IllegalArgumentException("Telefone inválido. Use DDD + número (10 ou 11 dígitos).");
        }
    }

    public String getTelefone() {
        return telefone;
    }

    //metodo
    public static boolean validarTelefone(String telefone) {
        return telefone != null && (telefone.length() == 10 || telefone.length() == 11);
    }

    public static boolean validarCpf(String cpf){
        int soma = 0,resto, numero;

        String dec1, dec2;
        try {
            if (cpf == null || (cpf.length() != 11 && cpf.length() != 14)) return false;

            for (int i =0 ; i<9; i++){
                numero = Integer.parseInt(cpf.substring(i,i+1));
                soma += numero * (10-i);
            }
            resto = soma % 11;
            if (resto < 2){
                dec1 ="0";
            }else {
                dec1 = String.valueOf(11- resto);
            }

            soma = 0;
            for (int i =0 ; i<9; i++){
                numero = Integer.parseInt(cpf.substring(i,i+1));
                soma += numero * (11-i);
            }
            soma += Integer.parseInt(dec1) * 2;
            resto = soma % 11;
            if (resto < 2){
                dec2="0";
            }else {
                dec2= String.valueOf(11-resto);
            }

            //formato cpf
            String cpfVericado = cpf.substring(0,9)+dec1+dec2;
            return cpf.equals(cpfVericado);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarEmail(String email){
        if (email.isEmpty()) {
            return true;
        }
        String emailFerificado = "^[\\w.-]+@[\\w-]+\\.[a-zA-Z]{2,}$";

        return email.matches(emailFerificado);
    }

    public String toString() {
        return String.format(
           "CPF: %s%nNome: %s%nEmail: %s%nData Nasc.: %s%nTelefone: %s%n",
           cpf, name, email, dataDeNascimento, telefone
        );
    }
}
