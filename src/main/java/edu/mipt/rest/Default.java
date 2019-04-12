package edu.mipt.rest;

import edu.mipt.model.DefaultObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/echo/")
public class Default {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DefaultObject defaultResponse(final @QueryParam("echo") String echo) {
        return new DefaultObject(1, echo);
    }

    @POST
    public Response defaultPost() {
        return Response.ok().build();
    }
}
