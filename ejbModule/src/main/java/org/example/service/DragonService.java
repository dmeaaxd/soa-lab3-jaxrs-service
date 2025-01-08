package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import jakarta.xml.bind.ValidationException;
import org.example.converter.DragonConverter;
import org.example.converter.DragonRequestDtoConverter;
import org.example.dto.request.DragonRequestDto;
import org.example.dto.response.DragonResponseDto;
import org.example.entity.Dragon;
import org.example.entity.Person;
import org.example.entity.enums.DragonCharacter;
import org.example.exception.DatabaseException;
import org.example.exception.SpringServiceException;
import org.example.repository.DragonRepository;
import org.example.service.parser.FilterParser;
import org.example.service.parser.SortParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class DragonService {

    @Inject
    private DragonRepository dragonRepository;
    @Inject
    private PersonService personService;

    public List<DragonResponseDto> getDragons(
            String sort,
            String filter,
            Integer page,
            Integer size,
            Integer killerId) throws BadRequestException, InternalServerErrorException {
        try {
            if (sort != null && !sort.isEmpty()) {
                sort = SortParser.parse(sort);
            }

            if (filter != null && !filter.isEmpty()) {
                filter = FilterParser.parse(filter);
            }

            if (size == null) {
                if (page != null) {
                    throw new ValidationException("Page must be null when size is null");
                }
            }
            else {
                if (page == null) {
                    throw new ValidationException("Page cannot be null when size not null");
                }
                if (page < 0) {
                    throw new ValidationException("Page cannot be less than zero");
                }
            }

            List<Dragon> dragons = dragonRepository.findAll(sort, filter, page, size, killerId);
            List<DragonResponseDto> dragonResponseDtos = new ArrayList<>();
            for (Dragon dragon : dragons) {
                dragonResponseDtos.add(DragonConverter.convertToDragonResponseDto(dragon));
            }
            return dragonResponseDtos;
        } catch (ValidationException validationException) {
            throw new BadRequestException(validationException.getMessage());
        } catch (DatabaseException databaseException){
            throw new InternalServerErrorException(databaseException.getMessage());
        }
    }

    public void addDragon(DragonRequestDto dragonRequestDto) throws BadRequestException, InternalServerErrorException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DragonRequestDto>> validationResult = validator.validate(dragonRequestDto);
        for (ConstraintViolation<DragonRequestDto> violation : validationResult) {
            throw new BadRequestException(violation.getMessage());
        }
        Dragon dragon = DragonRequestDtoConverter.convertToDragon(dragonRequestDto);

        try {
            if (dragon.getKiller() != null && dragon.getKiller().getId() != null) {
                Person killer = personService.getPersonById(dragon.getKiller().getId());
                if (killer == null) {
                    throw new NotFoundException("Killer not found");
                }
                dragon.setKiller(killer);
            }

            dragonRepository.save(dragon);
        } catch (DatabaseException | SpringServiceException internalServerErrorException){
            throw new InternalServerErrorException(internalServerErrorException.getMessage());
        }
    }

    public DragonResponseDto getDragonById(Integer id) throws BadRequestException, InternalServerErrorException, NotFoundException{
        if (id == null) {
            throw new BadRequestException("Id can not be null");
        }

        try {
            Dragon dragon = dragonRepository.findById(id);
            if (dragon == null) {
                throw new NotFoundException("Dragon not found");
            }
            return DragonConverter.convertToDragonResponseDto(dragon);
        } catch (DatabaseException databaseException){
            throw new InternalServerErrorException(databaseException.getMessage());
        }
    }

    public void updateDragon(Integer id, DragonRequestDto dragonRequestDto) throws BadRequestException, InternalServerErrorException, NotFoundException{
        if (id == null) {
            throw new BadRequestException("Id can not be null");
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DragonRequestDto>> validationResult = validator.validate(dragonRequestDto);
        for (ConstraintViolation<DragonRequestDto> violation : validationResult) {
            throw new BadRequestException(violation.getMessage());
        }
        Dragon newDragon = DragonRequestDtoConverter.convertToDragon(dragonRequestDto);

        try {
            Dragon dragon = dragonRepository.findById(id);
            if (dragon == null) {
                throw new NotFoundException("Dragon not found");
            }

            if (newDragon.getKiller() != null && newDragon.getKiller().getId() != null) {
                Person killer = personService.getPersonById(newDragon.getKiller().getId());
                if (killer == null) {
                    throw new NotFoundException("Killer not found");
                }
                newDragon.setKiller(killer);
            }

            newDragon.setId(id);
            dragonRepository.update(newDragon);
        } catch (DatabaseException | SpringServiceException internalServerErrorException){
            throw new InternalServerErrorException(internalServerErrorException.getMessage());
        }
    }

    public void deleteDragon(Integer id) throws BadRequestException, InternalServerErrorException, NotFoundException{
        if (id == null) {
            throw new BadRequestException("Id can not be null");
        }

        try {
            Dragon dragon = dragonRepository.findById(id);
            if (dragon == null) {
                throw new NotFoundException("Dragon not found");
            }

            dragonRepository.deleteById(id);
        } catch (DatabaseException databaseException){
            throw new InternalServerErrorException(databaseException.getMessage());
        }
    }

    public List<DragonResponseDto> searchByName(String name) throws BadRequestException, InternalServerErrorException {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Name cannot be null");
        }

        try {
            List<Dragon> dragons = dragonRepository.findAllByNameSubstring(name);
            List<DragonResponseDto> dragonResponseDtos = new ArrayList<>();
            for (Dragon dragon : dragons) {
                dragonResponseDtos.add(DragonConverter.convertToDragonResponseDto(dragon));
            }
            return dragonResponseDtos;
        } catch (DatabaseException databaseException){
            throw new InternalServerErrorException(databaseException.getMessage());
        }
    }

    public List<DragonResponseDto> filterByKiller(String passportId) throws BadRequestException, InternalServerErrorException {
        if (passportId == null || passportId.isEmpty()) {
            throw new BadRequestException("PassportId cannot be null");
        }

        try {
            List<Dragon> dragons = dragonRepository.findAllFilterByKiller(passportId);
            List<DragonResponseDto> dragonResponseDtos = new ArrayList<>();
            for (Dragon dragon : dragons) {
                dragonResponseDtos.add(DragonConverter.convertToDragonResponseDto(dragon));
            }
            return dragonResponseDtos;
        } catch (DatabaseException databaseException){
            throw new InternalServerErrorException(databaseException.getMessage());
        }
    }

    public List<DragonResponseDto> filterByCharacter(String character) throws BadRequestException, InternalServerErrorException {
        if (character == null || character.isEmpty()) {
            throw new BadRequestException("Character cannot be null");
        }

        try {
            DragonCharacter dragonCharacter = DragonCharacter.valueOf(character);
            List<Dragon> dragons = dragonRepository.findAllFilterByCharacter(dragonCharacter);
            List<DragonResponseDto> dragonResponseDtos = new ArrayList<>();
            for (Dragon dragon : dragons) {
                dragonResponseDtos.add(DragonConverter.convertToDragonResponseDto(dragon));
            }
            return dragonResponseDtos;
        } catch (DatabaseException databaseException){
            throw new InternalServerErrorException(databaseException.getMessage());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new BadRequestException(illegalArgumentException.getMessage());
        }
    }
}
