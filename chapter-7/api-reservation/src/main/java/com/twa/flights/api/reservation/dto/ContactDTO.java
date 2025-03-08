package com.twa.flights.api.reservation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactDTO(
        Long id,
        @NotBlank(message = "The telephoneNumber must be defined") String telephoneNumber,
        @NotBlank(message = "The email must be defined")
                @Email(message = "The value must be a valid email")
                String email) {}
