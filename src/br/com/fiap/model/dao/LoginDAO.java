package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private Connection con;

    public LoginDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

//     metodo de autenticação
    public boolean autenticar(Login login) {
        String sql = "SELECT * FROM USER_ACCOUNT WHERE CPF_USER = ? AND BIRTH_DATE = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, login.getCpf());
            ps.setDate(2, java.sql.Date.valueOf(login.getPasswordDate()));
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
}
