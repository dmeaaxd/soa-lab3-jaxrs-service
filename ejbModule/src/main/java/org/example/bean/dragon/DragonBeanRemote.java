package org.example.bean.dragon;

import jakarta.ejb.Remote;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import org.example.dto.request.DragonRequestDto;
import org.example.response.SerializableResponse;

@Remote
public interface DragonBeanRemote {
    SerializableResponse getDragons(String sort, String filter, Integer page, Integer size) throws BadRequestException, InternalServerErrorException;

    SerializableResponse addDragon(DragonRequestDto dragonRequestDto) throws BadRequestException, InternalServerErrorException ;

    SerializableResponse getDragonById(Integer id) throws BadRequestException, InternalServerErrorException, NotFoundException;

    SerializableResponse updateDragon(Integer id, DragonRequestDto dragonRequestDto) throws BadRequestException, InternalServerErrorException, NotFoundException;

    SerializableResponse deleteDragon(Integer id) throws BadRequestException, InternalServerErrorException, NotFoundException ;

    SerializableResponse searchByName(String name) throws BadRequestException, InternalServerErrorException ;

    SerializableResponse filterByKiller(String passportId) throws BadRequestException, InternalServerErrorException ;

    SerializableResponse filterByCharacter(String character) throws BadRequestException, InternalServerErrorException ;
}
