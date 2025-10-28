package br.com.fiap.dao;

import br.com.fiap.to.ContactHcTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContactHcDAO {

    public ArrayList<ContactHcTO> findAll() {
        ArrayList<ContactHcTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM contact_hc ORDER BY ID_CONTACT_HC";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            if (ps != null) {
                while (rs.next()) {
                    ContactHcTO contact = new ContactHcTO();
                    contact.setIdHC(rs.getLong("ID_CONTACT_HC"));
                    contact.setTitle(rs.getString("TITLE_CONTACT"));
                    contact.setInPerson(rs.getString("IN_PERSON_CONTACT"));
                    contact.setEmailhc( rs.getString("EMAIL_HC"));
                    contact.setPhoneHC(rs.getString("PHONE_HC"));
                    contact.setSchedule(rs.getString("SCHEDULE"));
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
        String sql = "SELECT * FROM contact_hc WHERE ID_CONTACT_HC = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                contact = new ContactHcTO();
                contact.setIdHC(rs.getLong("ID_CONTACT_HC"));
                contact.setTitle(rs.getString("TITLE_CONTACT"));
                contact.setInPerson(rs.getString("IN_PERSON_CONTACT"));
                contact.setEmailhc(rs.getString("EMAIL_HC"));
                contact.setPhoneHC(rs.getString("PHONE_HC"));
                contact.setSchedule(rs.getString("SCHEDULE"));
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return contact;
    }

    public ContactHcTO save(ContactHcTO contact) {
        String sql = "INSERT INTO contact_hc ( TITLE_CONTACT, IN_PERSON_CONTACT, EMAIL_HC, PHONE_HC, SCHEDULE) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, contact.getTitle());
            ps.setString(2, contact.getInPerson());
            ps.setString(3, contact.getEmailhc());
            ps.setString(4, contact.getPhoneHC());
            ps.setString(5, contact.getSchedule());

            if (ps.executeUpdate() > 0) {
                return contact;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public Boolean delete(Long id) {
        String sql = "DELETE FROM contact_hc WHERE ID_CONTACT_HC = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }  catch (SQLException e) {
            System.out.println("Erro ao Excluir: " + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ContactHcTO update (ContactHcTO contact ) {
        String sql = "update contact_hc set TITLE_CONTACT=?, IN_PERSON_CONTACT=?, EMAIL_HC=?, PHONE_HC=?, SCHEDULE=? where ID_CONTACT_HC=?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, contact.getTitle());
            ps.setString(2, contact.getInPerson());
            ps.setString(3, contact.getEmailhc());
            ps.setString(4, contact.getPhoneHC());
            ps.setString(5, contact.getSchedule());
            ps.setLong(6, (contact.getIdHC()));

            if (ps.executeUpdate() > 0) {
                return contact;
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
