package br.com.fiap.bo;

import br.com.fiap.dao.ContactHcDAO;
import br.com.fiap.dao.ReminderDAO;
import br.com.fiap.to.ContactHcTO;
import br.com.fiap.to.ReminderTO;

import java.util.ArrayList;

public class ContactHcBO {
    private ContactHcDAO contactDAO;

    public ArrayList<ContactHcTO> findAll() {
        contactDAO = new ContactHcDAO();
        return contactDAO.findAll();
    }

    public ContactHcTO findById(Long id) {
        contactDAO = new ContactHcDAO();
        return contactDAO.findById(id);
    }

    public ContactHcTO save(ContactHcTO contact) {
        contactDAO = new ContactHcDAO();
        return contactDAO.save(contact);
    }

    public boolean delete(Long id) {
        contactDAO = new ContactHcDAO();
        return contactDAO.delete(id);
    }

    public ContactHcTO update(ContactHcTO contact){
        contactDAO = new ContactHcDAO();
        return contactDAO.update(contact);
    }
}
