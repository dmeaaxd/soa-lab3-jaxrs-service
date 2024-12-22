package org.example.response;

import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SerializableResponse implements Serializable {
    private int code;
    private Object entity;

    public Response getResponse() {
        return Response.status(code).entity(entity).build();
    }
}
