package br.com.fiap.model.dao;


import br.com.fiap.model.dto.ContactHC;

import java.sql.*;
import java.util.ArrayList;

public class ContactHCDAO {
    private Connection con;
    private ContactHC contact;

    public ContactHCDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    // CREATE
    public String create(Object object) {
        contact = (ContactHC) object;
        String sql = "INSERT INTO CONTACT_HC (TITLE_CONTACT, IN_PERSON_CONTACT, EMAIL_HC, PHONE_HC, SCHEDULE) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, contact.getTitle());
            ps.setString(2, contact.getInPerson());
            ps.setString(3, contact.getEmail());
            ps.setString(4, contact.getPhoneHC());
            ps.setString(5, contact.getSchedule());

            if (ps.executeUpdate() > 0) {
                return "Contato inserido com sucesso.";
            } else {
                return "Erro ao inserir contato.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // UPDATE
    public String update(Object object) {
        contact = (ContactHC) object;
        String sql = "UPDATE CONTACT_HC SET IN_PERSON_CONTACT=?, EMAIL_HC=?, PHONE_HC=?, SCHEDULE=? WHERE TITLE_CONTACT=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, contact.getInPerson());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getPhoneHC());
            ps.setString(4, contact.getSchedule());
            ps.setString(5, contact.getTitle());

            if (ps.executeUpdate() > 0) {
                return "Contato atualizado com sucesso.";
            } else {
                return "Erro ao atualizar contato.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // DELETE
    public String delete(Object object) {
        contact = (ContactHC) object;
        String sql = "DELETE FROM CONTACT_HC WHERE TITLE_CONTACT=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, contact.getTitle());

            if (ps.executeUpdate() > 0) {
                return "Contato excluído com sucesso.";
            } else {
                return "Erro ao excluir contato.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // READ ONE
    public String readOne(Object object) {
        contact = (ContactHC) object;
        String sql = "SELECT * FROM CONTACT_HC WHERE TITLE_CONTACT=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, contact.getTitle());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                contact.setTitle(rs.getString("TITLE"));
                contact.setInPerson(rs.getString("IN_PERSON"));
                contact.setEmail(rs.getString("EMAIL"));
                contact.setPhoneHC(rs.getString("TEL"));
                contact.setSchedule(rs.getString("SCHEDULE"));

                return String.format(
                   "Título: %s%nAtendimento Presencial: %s%nEmail: %s%nTelefone: %s%nHorário: %s",
                   contact.getTitle(),
                   contact.getInPerson(),
                   contact.getEmail(),
                   contact.getPhoneHC(),
                   contact.getSchedule()
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;
    }

    // READ ALL
    public ArrayList<ContactHC> readAll() {
        String sql = "SELECT * FROM CONTACT_HC ORDER BY TITLE_CONTACT";
        ArrayList<ContactHC> lista = new ArrayList<>();

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ContactHC contact = new ContactHC();
                contact.setTitle(rs.getString("TITLE"));
                contact.setInPerson(rs.getString("IN_PERSON"));
                contact.setEmail(rs.getString("EMAIL"));
                contact.setPhoneHC(rs.getString("TEL"));
                contact.setSchedule(rs.getString("SCHEDULE"));
                lista.add(contact);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
