package com.twa.flights.api.reservation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReservationDTO(
        Long id,

        @NotBlank(message = "The itineraryId must be defined")
        String itineraryId,

        @NotBlank(message = "The searchId must be defined")
        String searchId,

        @Valid
        @NotEmpty(message = "You need at least one passenger")
        List<PassengerDTO> passengers,

        @Valid
        @NotNull(message = "The contact must be defined")
        ContactDTO contact
){}
