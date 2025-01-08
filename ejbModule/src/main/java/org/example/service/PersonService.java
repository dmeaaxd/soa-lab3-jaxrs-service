package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import org.example.entity.Person;
import org.example.exception.DatabaseException;
import org.example.exception.SpringServiceException;
import org.example.repository.PersonRepository;

@ApplicationScoped
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    public Person getPersonById(Integer id) throws BadRequestException, SpringServiceException {
        if (id == null) {
            throw new BadRequestException("Id can not be null");
        }

        try {
            Person person =  personRepository.findById(id);
            if (person == null) {
                throw new NotFoundException("Person not found");
            }
            return person;
        } catch (DatabaseException databaseException){
            throw new InternalServerErrorException(databaseException.getMessage());
        }
    }
}
