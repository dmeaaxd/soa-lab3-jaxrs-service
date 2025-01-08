package org.example.controller;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.bean.dragon.DragonBeanRemote;
import org.example.dto.request.DragonRequestDto;

@Path("dragons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DragonController {

    @EJB
    private DragonBeanRemote dragonBean;

    @GET
    public Response getAllDragons(
            @QueryParam("sort") String sort,
            @QueryParam("filter") String filter,
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size,
            @QueryParam("killer-id") Integer killerId
    ) {
        return dragonBean.getDragons(sort, filter, page, size, killerId).getResponse();
    }


    @POST
    public Response addDragon(DragonRequestDto dragonRequestDto) {
        return dragonBean.addDragon(dragonRequestDto).getResponse();
    }

    @GET
    @Path("/{id}")
    public Response getDragonById(@PathParam("id") Integer id){
        return dragonBean.getDragonById(id).getResponse();
    }

    @PUT
    @Path("/{id}")
    public Response updateDragon(@PathParam("id") Integer id, DragonRequestDto dragonRequestDto) {
        return dragonBean.updateDragon(id, dragonRequestDto).getResponse();
    }

    @DELETE
    @Path("{id}")
    public Response deleteDragon(@PathParam("id") Integer id) {
        return dragonBean.deleteDragon(id).getResponse();
    }

    @GET
    @Path("search-by-name")
    public Response searchByName(@QueryParam("name") String name) {
        return dragonBean.searchByName(name).getResponse();
    }

    @GET
    @Path("filter-by-killer")
    public Response filterByKiller(@QueryParam("passport-id") String passportId) {
        return dragonBean.filterByKiller(passportId).getResponse();
    }

    @GET
    @Path("filter-by-character")
    public Response filterByCharacter(@QueryParam("character") String character) {
        return dragonBean.filterByCharacter(character).getResponse();
    }
}
