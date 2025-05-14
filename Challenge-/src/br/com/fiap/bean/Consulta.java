package br.com.fiap.bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Consulta {
    //atributos
    private LocalDate data;
    private LocalTime hora;
    private String protocolo;


    //construtores
    public Consulta(){
    }
    public Consulta(LocalDate data, LocalTime hora) {
        this.data = data;
        this.hora = hora;
    }


    //getter/setter
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }


    //metodo
    public String geraProtocolo() {
        Random rand = new Random();
        int protogerado = rand.nextInt(9000000) + 1000000;
        String protocoloStr = Integer.toString(protogerado);
        setProtocolo(protocoloStr);
        return protocoloStr;
    }

    }