package com.twa.flights.api.reservation.exception;

import com.twa.flights.api.reservation.enums.APIError;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

public class TWAException extends RuntimeException {
    private final HttpStatus status;
    private final String description;
    private final List<String> reasons;

    public TWAException(APIError error) {
        this.status = error.getHttpStatus();
        this.description = error.getMessage();
        this.reasons = new ArrayList<>();
    }

    public TWAException(HttpStatus status, String description, List<String> reasons) {
        this.status = status;
        this.description = description;
        this.reasons = reasons;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getReasons() {
        return reasons;
    }
}
