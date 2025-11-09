package br.com.fiap.resource;

import br.com.fiap.bo.UserBO;
import br.com.fiap.to.UserTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/user")
public class UserResource {
    private UserBO userBO = new UserBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<UserTO> resultado = userBO.findAll();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("codigo") Long id){
        UserTO resultado = userBO.findByCodigo(id);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        }
        else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(UserTO user) {
        UserTO resultado = userBO.save(user);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long id){
        Response.ResponseBuilder response = null;
        if (userBO.delete(id)) {
            response = Response.status(400);
        } else {
            response = Response.status(404);
        }
        return response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid UserTO user, @PathParam("codigo") Long codigo){
        user.setId(codigo);
        UserTO resultado = userBO.update(user);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); //200
        } else {
            response = Response.status(400); // 400 Bad Request
        }
        response.entity(resultado);
        return response.build();
    }
}
