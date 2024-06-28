package com.twa.flights.api.reservation.dto;

import java.time.LocalDate;

public record PassengerDTO(
        Long id,
        String firstName,
        String lastName,
        String documentNumber,
        String documentType,
        String nationality,
        LocalDate birthday) {
}
