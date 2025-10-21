package br.com.fiap.dao;

import br.com.fiap.to.EmailReminderTO;

import java.sql.*;
import java.util.ArrayList;

public class EmailReminderDAO {

    public ArrayList<EmailReminderTO> findAll() {
        ArrayList<EmailReminderTO> reminders = new ArrayList<>();
        String sql = "SELECT * FROM reminder ORDER BY id_reminder";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (ps != null) {
                while (rs.next()) {
                    EmailReminderTO reminder = new EmailReminderTO();
                    reminder.setIdReminder(rs.getLong("id_reminder"));
                    reminder.setUserId(rs.getLong("user_account_id_user"));
                    reminder.setDateReminder(rs.getDate("reminder_date").toLocalDate());
                    reminder.setTimeReminder(rs.getTime("reminder_time").toLocalTime());
                    reminder.setDescriptionReminder(rs.getString("description"));

                    reminders.add(reminder);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return reminders;
    }

    public EmailReminderTO findByCodigo(Long id) {
        EmailReminderTO reminder = new EmailReminderTO();
        String sql = "SELECT * FROM reminder WHERE id_reminder = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                reminder.setIdReminder(rs.getLong("id_reminder"));
                reminder.setUserId(rs.getLong("user_account_id_user"));
                reminder.setDateReminder(rs.getDate("reminder_date").toLocalDate());
                reminder.setTimeReminder(rs.getTime("reminder_time").toLocalTime());
                reminder.setDescriptionReminder(rs.getString("description"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return reminder;
    }

    public EmailReminderTO save(EmailReminderTO reminder) {
        String sql = "INSERT INTO REMINDER (reminder_date, reminder_time, description) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(reminder.getDateReminder()));
            ps.setTime(2, Time.valueOf(reminder.getTimeReminder()));
            ps.setString(3, reminder.getDescriptionReminder());

            if (ps.executeUpdate() > 0) {
                return reminder;
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
        String sql = "DELETE FROM reminder WHERE id_reminder = ?";
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
}
