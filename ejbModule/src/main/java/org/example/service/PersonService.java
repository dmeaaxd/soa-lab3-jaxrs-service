package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.example.dto.response.ErrorResponseDto;
import org.example.entity.Person;
import org.example.exception.SpringServiceException;

@ApplicationScoped
public class PersonService {

    private final Client client = ClientBuilder.newClient();
    private final String url = "https://hahaha.drunkenhedgehog.ru/killer";

    public Person findById(Integer id) throws BadRequestException, SpringServiceException {
        if (id == null) {
            throw new BadRequestException("Id can not be null");
        }
        Response response;

        try {
            WebTarget target = client.target(url + "/" + id);
            response =  target.request().get();
        } catch (Exception e) {
            throw new SpringServiceException(e.getMessage());
        }

        if (response.getStatus() != 200) {
            if (response.getStatus() == 404) {
                return null;
            }

            String message = response.readEntity(ErrorResponseDto.class).getMessage();
            throw new SpringServiceException(message);
        }

        return response.readEntity(Person.class);
    }
}
