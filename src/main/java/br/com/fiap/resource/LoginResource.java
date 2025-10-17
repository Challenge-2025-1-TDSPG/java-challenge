package br.com.fiap.resource;

import br.com.fiap.bo.LoginBO;
import br.com.fiap.to.LoginTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    @POST
    public Response autenticar(LoginTO loginTO) {
        LoginBO bo = new LoginBO();

        boolean autenticado = bo.autenticar(loginTO);

        if (autenticado) {
            return Response.ok("{\"message\":\"Login bem-sucedido\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
               .entity("{\"error\":\"CPF ou data de nascimento inválidos\"}")
               .build();
        }
    }
}
