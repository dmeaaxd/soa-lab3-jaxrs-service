package org.example.bean.dragon;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.example.dto.request.DragonRequestDto;
import org.example.response.SerializableResponse;
import org.example.service.DragonService;

@Stateless
public class DragonBean implements DragonBeanRemote{

    @Inject
    private DragonService dragonService;

    @Override
    public SerializableResponse getDragons(String sort, String filter, Integer page, Integer size, Integer killerId) throws BadRequestException, InternalServerErrorException {
        return SerializableResponse.builder()
                .code(Response.Status.OK.getStatusCode())
                .entity(dragonService.getDragons(sort, filter, page, size, killerId))
                .build();
    }

    @Override
    public SerializableResponse addDragon(DragonRequestDto dragonRequestDto) throws BadRequestException, InternalServerErrorException {
        dragonService.addDragon(dragonRequestDto);
        return SerializableResponse.builder()
                .code(Response.Status.CREATED.getStatusCode())
                .build();
    }

    @Override
    public SerializableResponse getDragonById(Integer id) throws BadRequestException, InternalServerErrorException, NotFoundException {
        return SerializableResponse.builder()
                .code(Response.Status.OK.getStatusCode())
                .entity(dragonService.getDragonById(id))
                .build();
    }

    @Override
    public SerializableResponse updateDragon(Integer id, DragonRequestDto dragonRequestDto) throws BadRequestException, InternalServerErrorException, NotFoundException {
        dragonService.updateDragon(id, dragonRequestDto);
        return SerializableResponse.builder()
                .code(Response.Status.OK.getStatusCode())
                .build();
    }

    @Override
    public SerializableResponse deleteDragon(Integer id) throws BadRequestException, InternalServerErrorException, NotFoundException {
        dragonService.deleteDragon(id);
        return SerializableResponse.builder()
                .code(Response.Status.OK.getStatusCode())
                .build();
    }

    @Override
    public SerializableResponse searchByName(String name) throws BadRequestException, InternalServerErrorException {
        return SerializableResponse.builder()
                .code(Response.Status.OK.getStatusCode())
                .entity(dragonService.searchByName(name))
                .build();
    }

    @Override
    public SerializableResponse filterByKiller(String passportId) throws BadRequestException, InternalServerErrorException {
        return SerializableResponse.builder()
                .code(Response.Status.OK.getStatusCode())
                .entity(dragonService.filterByKiller(passportId))
                .build();
    }

    @Override
    public SerializableResponse filterByCharacter(String character) throws BadRequestException, InternalServerErrorException {
        return SerializableResponse.builder()
                .code(Response.Status.OK.getStatusCode())
                .entity(dragonService.filterByCharacter(character))
                .build();
    }
}
