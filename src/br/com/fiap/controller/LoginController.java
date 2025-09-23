package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.LoginDAO;
import br.com.fiap.model.dto.Login;

import java.sql.Connection;
import java.time.LocalDate;

public class LoginController {

    public boolean autenticarLogin(String cpf, LocalDate dataNascimento) {
        Connection con = ConnectionFactory.abrirConexao();
        LoginDAO loginDao = new LoginDAO(con);
        boolean autenticado;

        Login login = new Login();
        login.setCpf(cpf);
        login.setPasswordDate(dataNascimento);

        autenticado = loginDao.autenticar(login);

        ConnectionFactory.fecharConexao(con);
        return autenticado;
    }
}
