package br.com.fiap.bo;

import br.com.fiap.dao.LoginDAO;
import br.com.fiap.to.LoginTO;

public class LoginBO {
    public boolean autenticar(LoginTO loginTO) {

        if (loginTO.getCpf() == null || loginTO.getCpf().isEmpty()) {
            System.out.println("CPF não informado");
            return false;
        }

        if (loginTO.getPasswordDate() == null) {
            System.out.println("Data de nascimento não informada");
            return false;
        }
        LoginDAO dao = new LoginDAO();
        return dao.autenticar(loginTO);
    }
}
