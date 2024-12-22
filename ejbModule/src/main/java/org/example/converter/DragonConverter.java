package org.example.converter;

import org.example.dto.response.DragonResponseDto;
import org.example.entity.Dragon;

public class DragonConverter {
    public static DragonResponseDto convertToDragonResponseDto(Dragon dragon) {
        if (dragon == null) return null;
        return DragonResponseDto.builder()
                .id(dragon.getId())
                .name(dragon.getName())
                .coordinates(
                        DragonResponseDto.CoordinatesResponseDto
                                .builder()
                                .x(dragon.getCoordinates().getX())
                                .y(dragon.getCoordinates().getY())
                                .build()
                )
                .creationDate(dragon.getCreationDate())
                .age(dragon.getAge())
                .color(dragon.getColor())
                .dragonType(dragon.getDragonType())
                .dragonCharacter(dragon.getCharacter())
                .killer(PersonConverter.convertToPersonResponseDto(dragon.getKiller()))
                .build();
    }
}