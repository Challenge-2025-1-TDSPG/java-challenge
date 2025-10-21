package br.com.fiap.resource;

import br.com.fiap.bo.EmailReminderBO;
import br.com.fiap.to.EmailReminderTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/EmailReminder")
public class EmailReminderResource {
    private EmailReminderBO EmailReminder = new EmailReminderBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<EmailReminderTO> resultado = EmailReminder.findAll();
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
        EmailReminderTO resultado = EmailReminder.findByCodigo(id);
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
    public Response save(EmailReminderTO emailReminder) {
        EmailReminderTO resultado = EmailReminder.save(emailReminder);
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

        if (EmailReminder.delete(id)) {
            response = Response.status(204);
        } else {
            response = Response.status(404);
        }

        return response.build();
    }
}
