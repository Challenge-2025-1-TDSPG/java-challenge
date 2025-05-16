package br.com.fiap.main;
import br.com.fiap.bean.*;

import javax.swing.*;
import java.awt.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Telefone telefone = new Telefone();
        Paciente paciente= new Paciente();
        Medico medico= new Medico(" Lucas Andrade Pereira","0","Cardiovascula");
        Consulta consulta = new Consulta(LocalDate.of(2025,4,5), LocalTime.of(18,0));

        //formato data
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String repetir="sim";

        while (repetir.equalsIgnoreCase("sim"))
            try {
                GridBagConstraints gbc =new GridBagConstraints();
                gbc.insets = new Insets(2,2,2,2);
                gbc.anchor= GridBagConstraints.WEST;

                //ABA Paciete-entrada
                JPanel painel = new JPanel(new GridBagLayout());

                //Nome
                JTextField entradaNome = new JTextField(18);
                gbc.gridx = 0; gbc.gridy = 0;// coluna 0, linha 0
                painel.add(new JLabel("Nome completo:"), gbc);
                gbc.gridx = 1;
                painel.add(entradaNome, gbc);

                //CPF
                JTextField entraCpf = new JTextField(11);
                gbc.gridx = 0; gbc.gridy = 1;
                painel.add(new JLabel("CPF(somente numero):"), gbc);
                gbc.gridx =1;
                painel.add(entraCpf, gbc);

                //data De Nascimento
                JTextField entradataDeNascimento = new JTextField(8);
                gbc.gridx = 0; gbc.gridy = 2;
                painel.add(new JLabel("Data de Nascimento:"), gbc);
                gbc.gridx =1;
                painel.add(entradataDeNascimento, gbc);

                //telefone
                JTextField entradaTelefone = new JTextField(11);
                gbc.gridx = 0; gbc.gridy = 3;
                painel.add(new JLabel("Telefone com DDD:"), gbc);
                gbc.gridx =1;
                painel.add(entradaTelefone, gbc);


                // aba Endereco-Entrada
                //CEP
                JPanel painelEnderco = new JPanel(new GridBagLayout());
                JTextField entradaCep = new JTextField(11);
                gbc.gridx = 0; gbc.gridy = 0;
                painelEnderco.add(new JLabel("CEP: "), gbc);
                gbc.gridx =1;
                painelEnderco.add(entradaCep, gbc);

                //Logradouro
                JTextField entradaLogradouro = new JTextField(11);
                gbc.gridx = 0; gbc.gridy = 1;
                painelEnderco.add(new JLabel("Logradouro: "), gbc);
                gbc.gridx =1;
                painelEnderco.add(entradaLogradouro, gbc);

                //Complemento
                JTextField entradaComplemento = new JTextField(11);
                gbc.gridx = 0; gbc.gridy = 2;
                painelEnderco.add(new JLabel("Número: "), gbc);
                gbc.gridx =1;
                painelEnderco.add(entradaComplemento, gbc);

                //Painel com abas
                JTabbedPane abas = new JTabbedPane();
                abas.addTab("Dados Pessoais", painel);
                abas.addTab("Endereço", painelEnderco);

                int result = JOptionPane.showConfirmDialog(null, abas,
                        "Informe seus dados", JOptionPane.OK_CANCEL_OPTION);


                if (result == JOptionPane.OK_OPTION) {
                    //Aba Paciente
                    paciente.setNome(entradaNome.getText());
                    paciente.setCpf(entraCpf.getText());
                    LocalDate data = LocalDate.parse(entradataDeNascimento.getText(),dtm);
                    paciente.setDataDeNascimento(data);
                    telefone.setTelefone(entradaTelefone.getText());

                    //Aba Endereco
                    String cep = entradaCep.getText();
                    Endereco endereco = ValidacaoViaCep.buscarEnderecoPorCEP(cep);
                    endereco.setLagradouro(entradaLogradouro.getText());
                    endereco.setNumero(entradaComplemento.getText());

                    //painel de confirmação
                    String msgDeConfirmacao = String.format(" \nNome: %s\nCPF: %s\nIdade: %d \nTelefone: %s \n\nEndereço: \nCEP: %s \nLogradouro: %s \nNúmero: %s \nBairro: %s \nCidade: %s \nEstado: %s \n\nAs informações estão certas?",
                            paciente.getNome(),
                            paciente.getCpf(),
                            paciente.calcularIdade(),
                            telefone.getTelefone(),
                            endereco.getCep(),
                            endereco.getLagradouro(),
                            endereco.getNumero(),
                            endereco.getBairro(),
                            endereco.getLocalidade(),
                            endereco.getUf());

                    Object [] options ={"Fazer novamente","Prosseguir"};
                    int escolha = JOptionPane.showOptionDialog(null,msgDeConfirmacao,"Comfirme as informações",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                    if(escolha == 0){
                        repetir = "sim";
                    }else if(escolha==1) {
                        //painel de dados da consulta
                        JPanel painelConsulta = new JPanel(new GridBagLayout());
                        gbc.gridx=0; gbc.gridy=0;
                        painelConsulta.add(new JLabel(String.format("Prontuário/Paciente: %s",paciente.getNome().toUpperCase())),gbc);
                        gbc.gridy=1;
                        painelConsulta.add(new JLabel(String.format("Data Nasc./Idade: %s - %d anos",entradataDeNascimento.getText(),paciente.calcularIdade())),gbc);
                        gbc.gridy=3;
                        String dataFormatada = consulta.getData().format(dtm);
                        painelConsulta.add(new JLabel(String.format("Registro de Atendimento: %s - %s",consulta.geraProtocolo(),dataFormatada)),gbc);
                        gbc.gridy=4;
                        painelConsulta.add(new JLabel(String.format("Profissional do Atendimento: CRM-%s - Dr.%s",medico.gerarCrm(),medico.getNomeMedico())),gbc);

                        int painelConsultaInfo = JOptionPane.showConfirmDialog(null, painelConsulta, "Consulta", JOptionPane.OK_CANCEL_OPTION);
                        //parte do Feedback
                        if(painelConsultaInfo ==0){
                            JPanel painelFeed = new JPanel(new GridBagLayout());
                            JTextArea entraDaFeed = new JTextArea(12,30);
                            JLabel textofeed = new JLabel(String.format("<html><body style='width:300px;'>%s, queremos saber a sua opinião!<br>Como foi sua consulta?<br>Deixe seu feedback para que possamos continuar melhorando.</body></html>",paciente.getNome()));

                            gbc.gridx=1; gbc.gridy=1;
                            painelFeed.add(textofeed,gbc);
                            gbc.gridy=2;
                            painelFeed.add(entraDaFeed,gbc);

                            Object [] optionsFeed = {"enviar", "sair"};
                            int painelFeddback = JOptionPane.showOptionDialog(null,painelFeed,"Comfirme as informações",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsFeed, optionsFeed[0]);

                            if (painelFeddback == 0){
                                JOptionPane.showMessageDialog(null,"Obrigado pelo seu feedback! Sua opinião é muito importante para nós e nos ajuda a melhorar cada vez mais");
                            }
                            break;
                        }
                        break;

                    }else{
                        break;
                    }

                }else {
                    break;
                }


            } catch (Exception e) {
                Object [] options ={"sim","não"};
                int erroDeCadastro = JOptionPane.showOptionDialog(null,"Erro ao validar dados de cadastro, \ngostaria de fazer novamente?","Erro ao validar",JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                if (erroDeCadastro != 0) {
                    break;
                }
            }

    }
}
