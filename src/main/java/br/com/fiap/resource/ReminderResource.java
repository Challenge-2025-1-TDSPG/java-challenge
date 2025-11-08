package br.com.fiap.resource;

import br.com.fiap.bo.ReminderBO;
import br.com.fiap.to.ReminderTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/EmailReminder")
public class ReminderResource {
    private ReminderBO reminderBO = new ReminderBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ReminderTO> resultado = reminderBO.findAll();
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
        ReminderTO resultado = reminderBO.findByCodigo(id);
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
    public Response save(ReminderTO emailReminder) {
        ReminderTO resultado = reminderBO.save(emailReminder);
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

        if (reminderBO.delete(id)) {
            response = Response.status(204);
        } else {
            response = Response.status(404);
        }

        return response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid ReminderTO reminder, @PathParam("codigo") Long codigo){
        reminder.setIdReminder(codigo);
        ReminderTO resultado = reminderBO.update(reminder);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 created
        } else {
            response = Response.status(400); // 400 Bad Request
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/run")
    @Produces(MediaType.TEXT_PLAIN)
    public Response runRemindersNow() {
        reminderBO.sendReminders();
        return Response.ok().build();
    }

}
