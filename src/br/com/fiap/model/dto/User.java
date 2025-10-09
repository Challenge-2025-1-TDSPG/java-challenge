package br.com.fiap.model.dto;

import javax.swing.*;
import java.time.LocalDate;

public class User {
    //atributos
    private String cpf;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String phone;


    //construtores
    public User() {
    }

    //setter/getter
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (validarCpf(cpf)){
            this.cpf = cpf;
        }else {
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

    public void setPhone(String phone) {
        this.phone = formatarTelefone(phone);
    }


    public String getPhone() {
        return phone;
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


    public static boolean validarCpf(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) return false;

        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0, resto;

            for (int i = 0; i < 9; i++) {
                int numero = Character.getNumericValue(cpf.charAt(i));
                soma += numero * (10 - i);
            }
            resto = soma % 11;
            String dec1 = (resto < 2) ? "0" : String.valueOf(11 - resto);


            soma = 0;
            for (int i = 0; i < 10; i++) {
                int numero = Character.getNumericValue(cpf.charAt(i));
                soma += numero * (11 - i);
            }
            resto = soma % 11;
            String dec2 = (resto < 2) ? "0" : String.valueOf(11 - resto);

            String cpfVerificado = cpf.substring(0, 9) + dec1 + dec2;
            return cpf.equals(cpfVerificado);

        } catch (Exception e) {
            return false;
        }
    }


    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    public String toString() {
        return String.format(
           "CPF: %s%nNome: %s%nEmail: %s%nData Nasc.: %s%nTelefone: %s%n",
           cpf, name, email, birthDate, phone
        );
    }
}
