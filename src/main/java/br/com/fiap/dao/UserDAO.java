package br.com.fiap.dao;


import br.com.fiap.to.UserTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    public ArrayList<UserTO> findAll() {
        ArrayList<UserTO>  users = new ArrayList<>();
        String sql = "select * from user_account order by id_user";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            if (ps != null) {
                while (rs.next()) {
                   UserTO user = new UserTO();
                    user.setId(rs.getLong("id_user"));
                    user.setCpf(rs.getString("cpf_user"));
                    user.setName(rs.getString("name_user"));
                    user.setEmail(rs.getString("email_user"));
                    user.setBirthDate(rs.getDate("birth_date").toLocalDate());
                    user.setPhone(rs.getString("phone_user"));
                    users.add(user);
                }
            }else {
                return null;
            }
    } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return users;
    }

    public UserTO findByCodigo(Long id){
        UserTO user = new UserTO();
        String sql  = "select * from user_account where id_user = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                user.setId(rs.getLong("id_user"));
                user.setCpf(rs.getString("cpf_user"));
                user.setName(rs.getString("name_user"));
                user.setEmail(rs.getString("email_user"));
                user.setBirthDate(rs.getDate("birth_date").toLocalDate());
                user.setPhone(rs.getString("phone_user"));
            }else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return user;
    }

    public UserTO save(UserTO user){
        String sql = "INSERT INTO USER_ACCOUNT (cpf_user, name_user, email_user, birth_date, phone_user) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, user.getCpf());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setDate(4, Date.valueOf(user.getBirthDate()));
            ps.setString(5, user.getPhone());

            if (ps.executeUpdate() > 0) {
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Salvar: " + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public Boolean delete(Long id) {
        ReminderDAO reminderDAO = new ReminderDAO();

        reminderDAO.delete(id);

        String sql = "DELETE FROM user_account WHERE id_user=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public UserTO update (UserTO user) {
        String sql = "update USER_ACCOUNT set cpf_user=?, name_user=?, email_user=?, birth_date=?, phone_user=? where id_user=?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, user.getCpf());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setDate(4, Date.valueOf(user.getBirthDate()));
            ps.setString(5, user.getPhone());
            ps.setLong(6, (user.getId()));

            if (ps.executeUpdate() > 0) {
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: + e.getMessage()");
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
