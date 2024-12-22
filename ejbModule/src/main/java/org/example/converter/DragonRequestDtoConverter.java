package org.example.converter;

import org.example.dto.request.DragonRequestDto;
import org.example.entity.Coordinates;
import org.example.entity.Dragon;
import org.example.entity.Person;
import org.example.entity.enums.Color;
import org.example.entity.enums.DragonCharacter;
import org.example.entity.enums.DragonType;

public class DragonRequestDtoConverter {
    public static Dragon convertToDragon(DragonRequestDto dragonRequestDto) {
        if (dragonRequestDto == null) return null;
        return Dragon.builder()
                .name(dragonRequestDto.getName())
                .coordinates(
                        Coordinates.builder()
                                .x(dragonRequestDto.getCoordinates().getX())
                                .y(dragonRequestDto.getCoordinates().getY()).build()
                )
                .age(dragonRequestDto.getAge())
                .color(dragonRequestDto.getColor() != null ? Color.valueOf(dragonRequestDto.getColor()) : null)
                .dragonType(dragonRequestDto.getType() != null ? DragonType.valueOf(dragonRequestDto.getType()) : null)
                .character(dragonRequestDto.getCharacter() != null ? DragonCharacter.valueOf(dragonRequestDto.getCharacter()) : null)
                .killer(dragonRequestDto.getKiller() != null ? Person.builder().id(dragonRequestDto.getKiller()).build() : null)
                .build();
    }
}
