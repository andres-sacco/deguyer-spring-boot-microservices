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

@Tag(name = "Reservation", description = "Operations about the reservations")
public interface ReservationResource {

    @Operation(
            summary = "Get a reservation by ID",
            description = "Fetches a reservation by its unique ID")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Reservation found successfully",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ReservationDTO.class))
                        }),
                @ApiResponse(
                        responseCode = "404",
                        description = "Reservation not found",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class))
                        }),
                @ApiResponse(
                        responseCode = "429",
                        description = "Rate limit exceeded",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class))
                        })
            })
    @Parameter(
            name = "id",
            description = "ID of the reservation to fetch",
            required = true,
            example = "1")
    @GetMapping("/{id}")
    @RateLimiter(name = "reservation", fallbackMethod = "fallbackGetReservationById")
    ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id);

    @Operation(
            summary = "Get all reservations",
            description = "Retrieves a list of all reservations")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Reservations retrieved successfully",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))
                        })
            })
    @GetMapping
    ResponseEntity<List<ReservationDTO>> getReservations();

    @Operation(summary = "Save a new reservation", description = "Creates a new reservation")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Reservation created successfully",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ReservationDTO.class))
                        }),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid reservation data",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class))
                        })
            })
    @RequestBody(
            description = "Details of the reservation to create",
            required = true,
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReservationDTO.class)))
    @PostMapping
    ResponseEntity<ReservationDTO> save(@RequestBody @Valid ReservationDTO reservation);

    @Operation(
            summary = "Update a reservation",
            description = "Updates the details of an existing reservation by ID")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Reservation updated successfully",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ReservationDTO.class))
                        }),
                @ApiResponse(
                        responseCode = "404",
                        description = "Reservation not found",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class))
                        }),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid reservation data",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class))
                        })
            })
    @Parameter(
            name = "id",
            description = "ID of the reservation to update",
            required = true,
            example = "1")
    @RequestBody(
            description = "Updated reservation details",
            required = true,
            content = @Content(schema = @Schema(implementation = ReservationDTO.class)))
    @PutMapping("/{id}")
    ResponseEntity<ReservationDTO> update(
            @PathVariable Long id, @RequestBody @Valid ReservationDTO reservation);

    @Operation(summary = "Delete a reservation", description = "Deletes a reservation by ID")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "204",
                        description = "Reservation deleted successfully",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Void.class))
                        }),
                @ApiResponse(
                        responseCode = "404",
                        description = "Reservation not found",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class))
                        })
            })
    @Parameter(
            name = "id",
            description = "ID of the reservation to delete",
            required = true,
            example = "1")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
