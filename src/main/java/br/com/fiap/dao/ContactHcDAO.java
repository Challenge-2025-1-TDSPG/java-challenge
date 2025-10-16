package br.com.fiap.dao;

import br.com.fiap.to.ContactHcTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContactHcDAO {

    public ArrayList<ContactHcTO> findAll() {
        ArrayList<ContactHcTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM contact_hc ORDER BY id_hc";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            if (ps != null) {
                while (rs.next()) {
                    ContactHcTO contact = new ContactHcTO();
                    contact.setIdHC(rs.getLong("id_hc"));
                    contact.setTitle(rs.getString("title"));
                    contact.setInPerson(rs.getString("in_person"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhoneHC(rs.getString("phone_hc"));
                    contact.setSchedule(rs.getString("schedule"));
                    lista.add(contact);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return lista;
    }

    public ContactHcTO findById(Long id) {
        ContactHcTO contact = null;
        String sql = "SELECT * FROM contact_hc WHERE id_hc = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                contact = new ContactHcTO();
                contact.setIdHC(rs.getLong("id_hc"));
                contact.setTitle(rs.getString("title"));
                contact.setInPerson(rs.getString("in_person"));
                contact.setEmail(rs.getString("email"));
                contact.setPhoneHC(rs.getString("phone_hc"));
                contact.setSchedule(rs.getString("schedule"));
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return contact;
    }

    public ContactHcTO save(ContactHcTO contact) {
        String sql = "INSERT INTO contact_hc (title, in_person, email, phone_hc, schedule) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"id_hc"})) {
            ps.setString(1, contact.getTitle());
            ps.setString(2, contact.getInPerson());
            ps.setString(3, contact.getEmail());
            ps.setString(4, contact.getPhoneHC());
            ps.setString(5, contact.getSchedule());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    contact.setIdHC(rs.getLong(1));
                }
                return contact;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public Boolean delete(Long id) {
        String sql = "DELETE FROM contact_hc WHERE id_hc = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }
}
