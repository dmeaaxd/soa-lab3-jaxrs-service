package org.example.converter;

import org.example.dto.response.PersonResponseDto;
import org.example.entity.Person;

public class PersonConverter {
    public static PersonResponseDto convertToPersonResponseDto(Person person) {
        if (person == null) return null;
        return PersonResponseDto.builder()
                .id(person.getId())
                .name(person.getName())
                .passportId(person.getPassportId())
                .location(
                        PersonResponseDto.LocationResponseDto.builder()
                                .x(person.getLocation().getX())
                                .y(person.getLocation().getY())
                                .z(person.getLocation().getZ())
                                .name(person.getLocation().getName())
                                .build()
                )
                .build();
    }
}
