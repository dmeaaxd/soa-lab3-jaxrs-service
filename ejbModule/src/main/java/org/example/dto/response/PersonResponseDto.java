package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponseDto implements Serializable {
    private Integer id;
    private String name;
    private String passportId;
    private LocationResponseDto location;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationResponseDto implements Serializable {
        private Integer x;
        private Double y;
        private Integer z;
        private String name;
    }
}
