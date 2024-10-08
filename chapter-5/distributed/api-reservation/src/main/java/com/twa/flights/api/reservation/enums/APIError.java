package com.twa.flights.api.reservation.enums;

import org.springframework.http.HttpStatus;

public enum APIError {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "There are attributes with wrong values"),
    COUNTRY_NOT_FOUND(HttpStatus.BAD_REQUEST, "The country not exist"),
    CLUSTER_NOT_FOUND(HttpStatus.BAD_REQUEST, "The cluster not exist"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST, "The message not have a correct form"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Reservation not found"),
    RESERVATION_WITH_SAME_ID(HttpStatus.BAD_REQUEST, "There is a reservation with the same id");

    private final HttpStatus httpStatus;
    private final String message;

    APIError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}