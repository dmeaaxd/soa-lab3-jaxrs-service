package org.example.bean;

import jakarta.ejb.Remote;
import jakarta.ws.rs.core.Response;

@Remote
public interface PingBeanRemote {
    Response ping();
}
