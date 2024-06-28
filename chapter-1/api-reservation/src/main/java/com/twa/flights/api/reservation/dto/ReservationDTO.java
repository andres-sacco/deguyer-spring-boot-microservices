package com.twa.flights.api.reservation.dto;

import java.util.List;

public record ReservationDTO(
        Long id,
        String itineraryId,
        String searchId,
        List<PassengerDTO> passengers,
        ContactDTO contact
){}
