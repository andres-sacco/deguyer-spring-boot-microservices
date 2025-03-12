package com.twa.flights.api.reservation.controller.resource;

import com.twa.flights.api.reservation.dto.ErrorDTO;
import com.twa.flights.api.reservation.dto.ReservationDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface ReservationResource {


    @GetMapping("/{id}")
    @RateLimiter(name = "reservation", fallbackMethod = "fallbackGetReservationById")
    ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<ReservationDTO>> getReservations();


    @PostMapping
    ResponseEntity<ReservationDTO> save(@RequestBody @Valid ReservationDTO reservation);


    @PutMapping("/{id}")
    ResponseEntity<ReservationDTO> update(
            @PathVariable Long id, @RequestBody @Valid ReservationDTO reservation);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
