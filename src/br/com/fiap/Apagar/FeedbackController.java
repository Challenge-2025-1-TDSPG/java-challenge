package br.com.fiap.Apagar;

import br.com.fiap.model.dao.ConnectionFactory;

import java.sql.Connection;
import java.time.LocalDate;

public class FeedbackController {

    // CREATE
    public String createFeedback(String description, String cpfUser){
        Connection con = ConnectionFactory.abrirConexao();
        FeedbackDAO dao = new FeedbackDAO(con);

        Feedback feedback = new Feedback();
        feedback.setDescricao(description);
        feedback.setCpfUser(cpfUser);

        String result = dao.create(feedback);
        ConnectionFactory.fecharConexao(con);
        return result;
    }

    // UPDATE
    public String updateFeedback(int id, String description, LocalDate sentAt, String cpfUser){
        Connection con = ConnectionFactory.abrirConexao();
        FeedbackDAO dao = new FeedbackDAO(con);

        Feedback feedback = new Feedback();
        feedback.setIdFeedback(id);
        feedback.setDescricao(description);
        feedback.setEnviadoEm(sentAt);
        feedback.setCpfUser(cpfUser);

        String result = dao.update(feedback);
        ConnectionFactory.fecharConexao(con);
        return result;
    }

    // DELETE
    public String deleteFeedback(int id){
        Connection con = ConnectionFactory.abrirConexao();
        FeedbackDAO dao = new FeedbackDAO(con);

        Feedback feedback = new Feedback();
        feedback.setIdFeedback(id);

        String result = dao.delete(feedback);
        ConnectionFactory.fecharConexao(con);
        return result;
    }

    // READ ONE
    public String readOneFeedback(int id){
        Connection con = ConnectionFactory.abrirConexao();
        FeedbackDAO dao = new FeedbackDAO(con);

        Feedback feedback = new Feedback();
        feedback.setIdFeedback(id);

        String result = dao.readOne(feedback);
        ConnectionFactory.fecharConexao(con);
        return result;
    }

}
