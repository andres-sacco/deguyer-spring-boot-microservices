package com.twa.flights.api.reservation.controller;

import com.twa.flights.api.reservation.controller.resource.ReservationResource;
import com.twa.flights.api.reservation.dto.ReservationDTO;
import com.twa.flights.api.reservation.service.ReservationService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/")
public class ReservationController implements ReservationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    private ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        LOGGER.info("Obtain information from a reservation with {}", id);
        ReservationDTO response = service.getReservationById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        LOGGER.info("Obtain all the reservations");
        List<ReservationDTO> response = service.getReservations();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReservationDTO> save(@RequestBody @Valid ReservationDTO reservation) {
        LOGGER.info("Saving new reservation");
        ReservationDTO response = service.save(reservation);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ReservationDTO> update(
            @PathVariable Long id, @RequestBody @Valid ReservationDTO reservation) {
        LOGGER.info("Updating a reservation with {}", id);
        ReservationDTO response = service.update(id, reservation);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("Deleting a reservation with {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<ReservationDTO> fallbackGetReservationById(
            Long id, RuntimeException exception) {
        LOGGER.info("Exceed the number of request to this endpoint");
        return new ResponseEntity<>(null, HttpStatus.TOO_MANY_REQUESTS);
    }
}
