package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DragonRequestDto {
    private String name;
    private CoordinatesRequestDto coordinates;
    private Integer age;
    private String color;
    private String type;
    private String character;
    private Integer killer;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoordinatesRequestDto {
        private Integer x;
        private Float y;
    }
}
