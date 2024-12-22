package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PingService {

    public String ping() {
        return "pong";
    }

}
