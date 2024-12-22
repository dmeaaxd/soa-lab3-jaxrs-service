package org.example.bean.ping;

import jakarta.ejb.Remote;
import org.example.response.SerializableResponse;

@Remote
public interface PingBeanRemote {
    SerializableResponse ping();
}
