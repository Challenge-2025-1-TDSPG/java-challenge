package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.ContactHCDAO;
import br.com.fiap.model.dto.ContactHC;

import java.sql.Connection;
import java.util.ArrayList;

public class ContactHCController {

    // CREATE
    public String create(String title, String inPerson, String email, String tel, String schedule) {
        Connection con = ConnectionFactory.abrirConexao();
        ContactHCDAO dao = new ContactHCDAO(con);

        ContactHC contact = new ContactHC();
        contact.setTitle(title);
        contact.setInPerson(inPerson);
        contact.setEmail(email);
        contact.setPhoneHC(tel);
        contact.setSchedule(schedule);

        String resultado = dao.create(contact);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    // UPDATE
    public String update( int id, String title, String inPerson, String email, String tel, String schedule) {
        Connection con = ConnectionFactory.abrirConexao();
        ContactHCDAO dao = new ContactHCDAO(con);

        ContactHC contact = new ContactHC();
        contact.setId(id);
        contact.setTitle(title);
        contact.setInPerson(inPerson);
        contact.setEmail(email);
        contact.setPhoneHC(tel);
        contact.setSchedule(schedule);

        String resultado = dao.update(contact);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    // DELETE
    public String delete(int id) {
        Connection con = ConnectionFactory.abrirConexao();
        ContactHCDAO dao = new ContactHCDAO(con);

        ContactHC contact = new ContactHC();
        contact.setId(id);

        String resultado = dao.delete(contact);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    // READ ONE
    public String readOne(int id) {
        Connection con = ConnectionFactory.abrirConexao();
        ContactHCDAO dao = new ContactHCDAO(con);

        ContactHC contact = new ContactHC();
        contact.setId(id);

        String resultado = dao.readOne(contact);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    // READ ALL
    public ArrayList<ContactHC> readAll() {
        Connection con = ConnectionFactory.abrirConexao();
        ContactHCDAO dao = new ContactHCDAO(con);

        ArrayList<ContactHC> lista = dao.readAll();
        ConnectionFactory.fecharConexao(con);
        return lista;
    }
}
