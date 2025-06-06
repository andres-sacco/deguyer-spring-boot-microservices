package com.twa.flights.api.reservation.actuator;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom", enableByDefault = true)
public class CustomEndpointActuator {

    private final Map<String, String> configuration = new HashMap<>();

    @ReadOperation
    public Map<String, String> getConfiguration() {
        return configuration;
    }

    @WriteOperation
    public Map<String, String> addConfiguration(String key, String value) {
        configuration.put(key, value);
        return configuration;
    }

    @DeleteOperation
    public void deleteConfiguration(String key) {
        configuration.remove(key);
    }
}
