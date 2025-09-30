package br.com.fiap.model.dto;

import javax.swing.*;
import java.time.LocalDate;

public class User {
    //atributos
    private String cpf;
    private String name;
    private String email;
    private LocalDate birthDate;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
        this.telefone = formatarTelefone(telefone);
    }


    public String getTelefone() {
        return telefone;
    }

    //metodo
    public static String formatarTelefone(String telefone) {
        if (telefone == null) {
            throw new IllegalArgumentException("Telefone não pode ser nulo.");
        }


        String telLimpo = telefone.replaceAll("\\D", "");

        if (telLimpo.length() == 11) {
            return String.format("(%s)%s-%s",
                    telLimpo.substring(0, 2),
                    telLimpo.substring(2, 7),
                    telLimpo.substring(7));
        } else if (telLimpo.length() == 10) {
            return String.format("(%s)%s-%s",
                    telLimpo.substring(0, 2),
                    telLimpo.substring(2, 6),
                    telLimpo.substring(6));
        } else {
            throw new IllegalArgumentException("Telefone inválido. Use DDD + número (10 ou 11 dígitos).");
        }
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
        String emailFerificado = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

        return email.matches(emailFerificado);
    }

    public String toString() {
        return String.format(
           "CPF: %s%nNome: %s%nEmail: %s%nData Nasc.: %s%nTelefone: %s%n",
           cpf, name, email, birthDate, telefone
        );
    }
}
