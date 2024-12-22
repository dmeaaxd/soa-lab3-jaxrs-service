package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enums.Color;
import org.example.entity.enums.DragonCharacter;
import org.example.entity.enums.DragonType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DragonResponseDto implements Serializable {
    private Integer id;
    private String name;
    private CoordinatesResponseDto coordinates;
    private LocalDateTime creationDate;
    private Integer age;
    private Color color;
    private DragonType dragonType;
    private DragonCharacter dragonCharacter;
    private PersonResponseDto killer;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoordinatesResponseDto implements Serializable {
        private Integer x;
        private Float y;
    }

}
