package br.com.fiap.resource;

import br.com.fiap.bo.ContactHcBO;
import br.com.fiap.to.ContactHcTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/contatoHC")
public class ContactHcResource {
    private ContactHcBO contactBO = new ContactHcBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ContactHcTO> resultado = contactBO.findAll();
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        ContactHcTO resultado = contactBO.findById(id);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }

        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(ContactHcTO contact) {
        ContactHcTO resultado = contactBO.save(contact);
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
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = null;

        if (contactBO.delete(id)) {
            response = Response.status(204);
        } else {
            response = Response.status(404);
        }

        return response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid ContactHcTO contactHc, @PathParam("codigo") Long codigo){
        contactHc.setIdHC(codigo);
        ContactHcTO resultado = contactBO.update(contactHc);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 created
        } else {
            response = Response.status(400); // 400 Bad Request
        }
        response.entity(resultado);
        return response.build();
    }
}
