package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.UserDAO;
import br.com.fiap.model.dto.User;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserController {

    // CREATE
    public String createUser(String cpf, String nome, String email, LocalDate dataNascimento, String telefone) {
        Connection con = ConnectionFactory.abrirConexao();
        UserDAO userDao = new UserDAO(con);
        String resultado;

        User user = new User();
        user.setCpf(cpf);
        user.setName(nome);
        user.setEmail(email);
        user.setDataDeNascimento(dataNascimento);
        user.setTelefone(telefone);

        resultado = userDao.create(user);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    // UPDATE
    public String updateUser(String cpf, String nome, String email, LocalDate dataNascimento, String telefone) {
        Connection con = ConnectionFactory.abrirConexao();
        UserDAO userDao = new UserDAO(con);
        String resultado;

        User user = new User();
        user.setCpf(cpf);
        user.setName(nome);
        user.setEmail(email);
        user.setDataDeNascimento(dataNascimento);
        user.setTelefone(telefone);

        resultado = userDao.update(user);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    // DELETE
    public String deleteUser(String cpf) {
        Connection con = ConnectionFactory.abrirConexao();
        UserDAO userDao = new UserDAO(con);
        String resultado;

        User user = new User();
        user.setCpf(cpf);

        resultado = userDao.delete(user);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    // READ ONE
    public String readOne(String cpf) {
        Connection con = ConnectionFactory.abrirConexao();
        UserDAO userDao = new UserDAO(con);
        String resultado;

        User user = new User();
        user.setCpf(cpf);

        resultado = userDao.readOne(user);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    // READ ALL
    public ArrayList<User> readAll() {
        Connection con = ConnectionFactory.abrirConexao();
        UserDAO userDao = new UserDAO(con);
        ArrayList<User> lista = userDao.readAll();
        ConnectionFactory.fecharConexao(con);
        return lista;
    }
}
