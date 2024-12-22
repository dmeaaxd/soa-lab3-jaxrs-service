package org.example.bean;

import jakarta.ejb.Remote;
import org.example.response.SerializableResponse;

@Remote
public interface PingBeanRemote {
    SerializableResponse ping();
}
