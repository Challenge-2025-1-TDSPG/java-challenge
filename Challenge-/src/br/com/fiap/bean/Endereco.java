package br.com.fiap.bean;

public class Endereco {
    //atributos
    private String cep ;
    private String lagradouro;
    private String numero;
    private String bairro;
    private String localidade;
    private String uf;


    //construtores
    public Endereco() {
    }

    public Endereco(String cep, String lagradouro, String numero, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.lagradouro = lagradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    //getter/setter
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {

        this.cep = cep;


    }

    public String getLagradouro() {
        return lagradouro;
    }

    public void setLagradouro(String lagradouro) {
        this.lagradouro = lagradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}