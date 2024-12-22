package org.example.bean;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.core.Response;
import org.example.service.PingService;

@Stateless
public class PingBean implements PingBeanRemote {

    private final PingService pingService;

    public PingBean(){
        this.pingService = new PingService();
    }

    @Override
    public Response ping() {
        return Response.ok(pingService.ping()).build();
    }
}
