package br.com.fiap.main;
import br.com.fiap.bean.*;

import javax.swing.*;
import java.awt.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Paciente paciente= new Paciente();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Medico medico= new Medico(" Lucas Andrade Pereira","0","Cardiovascula");
        Consulta consulta = new Consulta(LocalDate.of(2025,4,13), LocalTime.of(18,0));
        String repetir="s";
        while (repetir.equalsIgnoreCase("s"))
            try {

                //ABA Paciete-entrada
                JPanel painel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc =new GridBagConstraints();
                gbc.insets = new Insets(2,2,2,2);
                gbc.anchor= GridBagConstraints.WEST;


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
                    paciente.setTelefone(entradaTelefone.getText());

                    //Aba Endereco
                    String cep = entradaCep.getText();
                    Endereco endereco = ValidacaoViaCep.buscarEnderecoPorCEP(cep);
                    endereco.setLagradouro(entradaLogradouro.getText());
                    endereco.setNumero(entradaComplemento.getText());

                    String msgDeConfirmacao = String.format(" \nNome: %s\nCPF: %s\nIdade: %d \nTelefone: %s \n\nEndereço: \nCEP: %s \nLogradouro: %s \nNúmero: %s \nBairro: %s \nCidade: %s \nEstado: %s \n\nAs informações estão certas?",
                            paciente.getNome(),
                            paciente.getCpf(),
                            paciente.calcularIdade(),
                            paciente.getTelefone(),
                            endereco.getCep(),
                            endereco.getLagradouro(),
                            endereco.getNumero(),
                            endereco.getBairro(),
                            endereco.getLocalidade(),
                            endereco.getUf());

                    Object [] options ={"Fazer novamente","Prosseguir"};
                    int escolha = JOptionPane.showOptionDialog(null,msgDeConfirmacao,"Comfirme as informações",JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if(escolha == 0){
                        repetir = "s";
                    }else if(escolha==1) {

                        JPanel painelConsulta = new JPanel(new GridBagLayout());
                        gbc.gridx=0; gbc.gridy=0;
                        painelConsulta.add(new JLabel(String.format("Prontuário/Paciente: %s",medico.getNomeMedico().toUpperCase())),gbc);
                        gbc.gridy=1;
                        painelConsulta.add(new JLabel(String.format("Data Nasc./Idade: %s - %d",entradataDeNascimento.getText(),paciente.calcularIdade())),gbc);
                        gbc.gridy=3;
                        painelConsulta.add(new JLabel(String.format("Registro de Atendimento: %s - %s",consulta.geraProtocolo(),consulta.getData())),gbc);
                        gbc.gridy=4;
                        painelConsulta.add(new JLabel(String.format("Profissional do Atendimento: CRM-%s - Dr.%s",medico.gerarCrm(),medico.getNomeMedico())),gbc);

                        int painelConsultaInfo = JOptionPane.showConfirmDialog(null, painelConsulta, "Consulta", JOptionPane.OK_CANCEL_OPTION);

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
