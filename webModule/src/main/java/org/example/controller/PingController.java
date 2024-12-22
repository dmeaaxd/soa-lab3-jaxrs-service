package org.example.controller;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.bean.PingBeanRemote;

@Path("ping")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PingController {

    @EJB
    private PingBeanRemote pingBeanRemote;

    @GET
    @Path("/web")
    public Response webPing() {
        return Response.ok("pong").build();
    }

    @GET
    @Path("/ejb")
    public Response ejbPing() {
        return pingBeanRemote.ping().getResponse();
    }
}
