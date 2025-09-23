package br.com.fiap.Apagar;

import java.sql.*;

public class FeedbackDAO {
    private Connection con;
    private Feedback feedback;

    public FeedbackDAO(Connection con) {
        this.con = con;
    }

    // CREATE
    public String create(Object object) {
        feedback = (Feedback) object;
        String sql = "INSERT INTO FEEDBACK (DS_FEEDBACK, USUARIO_CPF_USER) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, feedback.getDescricao());
            ps.setString(2, feedback.getCpfUser());

            if (ps.executeUpdate() > 0) {
                return "Feedback inserido com sucesso";
            } else {
                return "Erro ao inserir  feedback.";
            }
        } catch (SQLException e) {
            return "SQL Error: " + e.getMessage();
        }
    }

    // UPDATE
    public String update(Object object) {
        feedback = (Feedback) object;
        String sql = "UPDATE FEEDBACK SET DS_FEEDBACK=?, ENVIADO_EM=?, USUARIO_CPF_USER=? WHERE ID_FEEDBACK=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, feedback.getDescricao());
            ps.setDate(2, java.sql.Date.valueOf(feedback.getEnviadoEm()));
            ps.setString(3, feedback.getCpfUser());
            ps.setInt(4, feedback.getIdFeedback());

            if (ps.executeUpdate() > 0) {
                return "Feedback atualizado com sucesso.";
            } else {
                return "Erro ao atualizar feedback.";
            }
        } catch (SQLException e) {
            return "SQL Error: " + e.getMessage();
        }
    }

    // DELETE
    public String delete(Object object) {
        feedback = (Feedback) object;
        String sql = "DELETE FROM FEEDBACK WHERE ID_FEEDBACK=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, feedback.getIdFeedback());

            if (ps.executeUpdate() > 0) {
                return "Feedback excluído com sucesso.";
            } else {
                return "Error ao excluir contato";
            }
        } catch (SQLException e) {
            return "SQL Error: " + e.getMessage();
        }
    }

    // READ ONE
    public String readOne(Object object) {
        feedback = (Feedback) object;
        String sql = "SELECT * FROM FEEDBACK WHERE ID_FEEDBACK=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, feedback.getIdFeedback());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                feedback.setIdFeedback(rs.getInt("ID_FEEDBACK"));
                feedback.setDescricao(rs.getString("DS_FEEDBACK"));
                feedback.setEnviadoEm(rs.getDate("ENVIADO_EM").toLocalDate());
                feedback.setCpfUser(rs.getString("USUARIO_CPF_USER"));

                return String.format(
                   "ID: %d%nDescription: %s%nSent at: %s%nUser CPF: %s",
                   feedback.getIdFeedback(),
                   feedback.getDescricao(),
                   feedback.getEnviadoEm(),
                   feedback.getCpfUser()
                );
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }
}
