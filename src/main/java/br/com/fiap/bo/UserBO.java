package br.com.fiap.bo;

import br.com.fiap.dao.UserDAO;
import br.com.fiap.to.UserTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserBO {
    private UserDAO userDAO;

    public ArrayList<UserTO> findAll() {
        userDAO = new UserDAO();
        return userDAO.findAll();
    }

    public UserTO findByCodigo (Long id) {
        userDAO = new UserDAO();

        return userDAO.findByCodigo(id);
    }

    public UserTO save(UserTO user){

        userDAO = new UserDAO();
        if (user.getBirthDate().isAfter(LocalDate.now())) {
            return null;
        }
        return userDAO.save(user);
    }

    public boolean delete(Long id){
        userDAO = new UserDAO();
        return  userDAO.delete(id);
    }

    public UserTO update(UserTO user){
        userDAO = new UserDAO();
        return userDAO.update(user);
    }

}
