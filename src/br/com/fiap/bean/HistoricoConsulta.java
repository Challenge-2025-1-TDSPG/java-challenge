    package br.com.fiap.bean;


import java.time.LocalDate;
import java.time.LocalTime;


    public class HistoricoConsulta extends Consulta{
        private String descricao;

        public HistoricoConsulta() {
        }

      public HistoricoConsulta(String nome, String cpf, LocalDate dataDeNascimento, LocalDate data, LocalTime hora, String protocolo, String descricao) {
            super(nome, cpf, dataDeNascimento, data, hora, protocolo);
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }
    }
