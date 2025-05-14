package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Paciente {
    //atributos
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private String telefone;


    //construtores
    public Paciente() {
    }


    //setter/getter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {

        return String.format("%s.%s.%s-%s",
            cpf.substring(0, 3),
            cpf.substring(3, 6),
            cpf.substring(6, 9),
            cpf.substring(9, 11));

    }

    public void setCpf(String cpf) {
        if (ValidacaoCpf.validarCPF(cpf)){
            this.cpf = cpf;
        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido.");
        }
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getTelefone() {
        if(telefone.length() == 11){
            return String.format("(%s)%s",
                telefone.substring(0,2),
                telefone.substring(2,11));
        }else{
        return telefone;
        }
    }

    public void setTelefone(String telefone) {
        if (telefone.length()==11 || telefone.length()==9){
            this.telefone = telefone;
        }else{
            JOptionPane.showMessageDialog(null, "erro no telefone.");
        }

    }


    //metodo
    public int calcularIdade() {
        LocalDate dataAtual = LocalDate.now();
        Period idade = Period.between(dataDeNascimento, dataAtual);
        return idade.getYears();
    }
}
