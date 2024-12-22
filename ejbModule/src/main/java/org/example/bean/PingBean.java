package org.example.bean;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.core.Response;
import org.example.response.SerializableResponse;
import org.example.service.PingService;

@Stateless
public class PingBean implements PingBeanRemote {

    private final PingService pingService;

    public PingBean(){
        this.pingService = new PingService();
    }

    @Override
    public SerializableResponse ping() {
        return SerializableResponse.builder()
                .code(Response.Status.OK.getStatusCode())
                .entity(pingService.ping())
                .build();
    }
}
