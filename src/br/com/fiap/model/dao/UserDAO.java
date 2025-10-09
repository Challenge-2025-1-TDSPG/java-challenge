package br.com.fiap.model.dao;

import br.com.fiap.model.dto.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private Connection con;
    private User user;

    public UserDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    // Create
    public String create(Object object){
        user =(User) object;
        String sql = "INSERT INTO user_account (cpf_user, name_user, email_user, birth_date, phone_user) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps =getCon().prepareStatement(sql)){
            ps.setString(1, user.getCpf());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setDate(4, Date.valueOf(user.getBirthDate()));
            ps.setString(5, user.getPhone());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // Update
    public String update(Object object){
        user = (User) object;
        String sql = "UPDATE user_account SET NAME_USER=?, BIRTH_DATE=?, EMAIL_USER=?, PHONE_USER=? WHERE CPF_USER=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setDate(2, Date.valueOf(user.getBirthDate()));
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getCpf());

            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e){
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // DELETE
    public String delete(Object object) {
        user = (User) object;
        String sql = "DELETE FROM user_account WHERE CPF_USER=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, user.getCpf());

            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso.";
            } else {
                return "Erro ao excluir.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // READ ONE
    public String readOne(Object object) {
        User user = (User) object;

        String sql = "SELECT * FROM user_account WHERE CPF_USER = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, user.getCpf());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setCpf(rs.getString("CPF_USER"));
                user.setName(rs.getString("NAME_USER"));
                user.setBirthDate(rs.getDate("BIRTH_DATE").toLocalDate());
                user.setEmail(rs.getString("EMAIL_USER"));
                user.setPhone(rs.getString("Phone_USER"));

                return String.format(
                   "CPF: %s%nNome: %s%nData de nascimento: %s%nEmail: %s%nTelefone: %s",
                   user.getCpf(),
                   user.getName(),
                   user.getBirthDate(),
                   user.getEmail(),
                   user.getPhone()
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;
    }

    // READ ALL
    public ArrayList<User> readAll() {
        String sql = "SELECT * FROM user_account ORDER BY NAME_USER";
        ArrayList<User> lista = new ArrayList<>();

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setCpf(rs.getString("CPF_USER"));
                user.setName(rs.getString("NAME_USER"));
                user.setBirthDate(rs.getDate("BIRTH_DATE").toLocalDate());
                user.setEmail(rs.getString("EMAIL_USER"));
                user.setPhone(rs.getString("Phone_USER"));
                lista.add(user);
            }
            return lista;

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
