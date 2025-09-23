package br.com.fiap.model.dto;

import java.time.LocalDate;

public class Feedback {
    // Atributos
    private int idFeedback;
    private String descricao;
    private LocalDate enviadoEm;
    private String cpfUser;

    public Feedback() {}

    //getter e setter
    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getEnviadoEm() {
        return enviadoEm;
    }

    public void setEnviadoEm(LocalDate enviadoEm) {
        this.enviadoEm = enviadoEm;
    }

    public String getCpfUser() {
        return cpfUser;
    }

    public void setCpfUser(String cpfUser) {
        this.cpfUser = cpfUser;
    }
}
