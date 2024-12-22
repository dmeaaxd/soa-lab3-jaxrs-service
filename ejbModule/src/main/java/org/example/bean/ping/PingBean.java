package org.example.bean.ping;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.example.response.SerializableResponse;
import org.example.service.PingService;

@Stateless
public class PingBean implements PingBeanRemote {

    @Inject
    private PingService pingService;

    @Override
    public SerializableResponse ping() {
        return SerializableResponse.builder()
                .code(Response.Status.OK.getStatusCode())
                .entity(pingService.ping())
                .build();
    }
}
