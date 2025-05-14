package br.com.fiap.bean;


import java.util.Random;

public class Medico {
    //atributos
    private String nomeMedico;
    private String crm;
    private String especialidade;


    //construtores
    public Medico(){
    }
    public Medico(String nomeMedico, String crm, String especialidade) {
        this.nomeMedico = nomeMedico;
        this.crm = crm;
        this.especialidade = especialidade;
    }


    //getter/setter
    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        if (crm.length() > 5 && crm.length() < 10) {
            this.crm = crm;
        }
    }
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }


    //metodo
    public String gerarCrm() {
        Random rand = new Random();
        int crmGerado = rand.nextInt(900000) + 100000; // CRM de 6 dígitos garantidos
        String crmStr = Integer.toString(crmGerado);
        setCrm(crmStr);
        return crmStr;
    }
}
