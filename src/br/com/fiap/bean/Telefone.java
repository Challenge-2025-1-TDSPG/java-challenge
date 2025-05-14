package br.com.fiap.bean;

import javax.swing.*;

public class Telefone {
    //atributos
    private String telefone;


    //construtores
    public Telefone() {
    }

    public Telefone(String telefone) {
        this.telefone = telefone;
    }


    //setter/getter
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
}
