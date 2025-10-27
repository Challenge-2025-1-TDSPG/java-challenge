package br.com.fiap.dao;

import br.com.fiap.to.ReminderTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ReminderDAO {

    public ArrayList<ReminderTO> findAll() {
        ArrayList<ReminderTO> reminders = new ArrayList<>();
        String sql = "SELECT * FROM reminder ORDER BY id_reminder";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (ps != null) {
                while (rs.next()) {
                    ReminderTO reminder = new ReminderTO();
                    reminder.setIdReminder(rs.getLong("id_reminder"));
                    reminder.setUserId(rs.getLong("user_account_id_user"));
                    reminder.setDateReminder(rs.getDate("reminder_date").toLocalDate());
                    reminder.setTimeReminder(LocalTime.parse(rs.getString("reminder_time")));
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

    public ReminderTO findByCodigo(Long id) {
        ReminderTO reminder = new ReminderTO();
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

    public ReminderTO save(ReminderTO reminder) {
        String sql = "INSERT INTO REMINDER (user_account_id_user, reminder_date, reminder_time, description) " +
           "VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_reminder"})) {

            ps.setLong(1, reminder.getUserId());
            ps.setDate(2, Date.valueOf(reminder.getDateReminder()));
            ps.setString(3, reminder.getTimeReminder().toString());
            ps.setString(4, reminder.getDescriptionReminder());

            if (ps.executeUpdate() > 0) {
                return reminder;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao Salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public Boolean delete(Long id) {
        String sql = "DELETE FROM reminder WHERE user_account_id_user = ?";
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

    public ReminderTO update (ReminderTO reminder) {
        String sql = "update REMINDER set user_account_id_user=?, reminder_date=?, reminder_time=?, description=? where id_reminder=?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, reminder.getUserId());
            ps.setDate(2, Date.valueOf(reminder.getDateReminder()));
            ps.setString(3, reminder.getTimeReminder().toString());
            ps.setString(4, reminder.getDescriptionReminder());
            ps.setLong(5, (reminder.getIdReminder()));

            if (ps.executeUpdate() > 0) {
                return reminder;
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

    public List<ReminderTO> findReminders() {
        List<ReminderTO> reminders = new ArrayList<>();

        String sql = """
            SELECT u.email_user,
            u.phone_number,        
            r.id_reminder,
            r.user_account_id_user,
            r.reminder_date,
            r.reminder_time,
            r.description
            FROM reminder r
            JOIN user_account u ON u.id_user = r.user_account_id_user
            WHERE r.reminder_date BETWEEN ? AND ?
        """;

        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAhead = today.plusDays(7);

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(today));
            ps.setDate(2, Date.valueOf(sevenDaysAhead));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReminderTO reminder = new ReminderTO();
                reminder.setDestinatario(rs.getString("email_user"));
                reminder.setNumberReminder(rs.getString("phone_number"));
                reminder.setIdReminder(rs.getLong("id_reminder"));
                reminder.setUserId(rs.getLong("user_account_id_user"));
                reminder.setDateReminder(rs.getDate("reminder_date").toLocalDate());
                String timeStr = rs.getString("reminder_time");
                reminder.setTimeReminder(timeStr != null ? LocalTime.parse(timeStr) : null);
                reminder.setDescriptionReminder(rs.getString("description"));
                reminders.add(reminder);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar lembretes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return reminders;
    }



}
