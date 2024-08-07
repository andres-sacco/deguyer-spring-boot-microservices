package com.twa.flights.api.reservation.dto;

import com.twa.flights.api.reservation.validator.NationalityFormatConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PassengerDTO(
        @NotBlank(message = "firstName is mandatory")
        @Size(min = 2, max = 20, message = "The firstname not have the correct size")
        String firstName,

        @NotBlank(message = "lastName is mandatory")
        @Size(min = 2, max = 20, message = "The lastName not have the correct size")
        String lastName,

        @NotBlank(message = "The documentNumber must be defined")
        @Size(min = 2, max = 10, message = "The documentNumber not have the correct size")
        String documentNumber,

        @NotBlank(message = "The documentType must be defined")
        @Size(min = 3, max = 10, message = "The documentType not have the correct size")
        String documentType,

        @NationalityFormatConstraint
        String nationality,

        @NotNull(message = "The birthday must be defined")
        @Past(message = "birthday need to be a date in the past")
        LocalDate birthday) {
}
