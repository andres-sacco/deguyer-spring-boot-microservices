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
                                    schema = @Schema(implementation = ReservationDTO.class),
                                    examples = {
                                        @ExampleObject(
                                                name = "Successful response",
                                                value =
                                                        "{\"id\": 1,\"itineraryId\":\"2\",\"searchId\":\"2\",\"passengers\":[{\"firstName\":\"Andres\",\"lastName\":\"Sacco\",\"documentNumber\":\"987654321\",\"documentType\":\"DNI\",\"nationality\":\"AR\",\"birthday\":\"1985-01-01\"}],\"contact\":{\"telephoneNumber\":\"54911111111\",\"email\":\"sacco.andres@gmail.com\"}}")
                                    })
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
                                    schema = @Schema(implementation = ReservationDTO.class),
                                    examples = {
                                        @ExampleObject(
                                                name = "Successful response",
                                                value =
                                                        "{\"id\": 6,\"itineraryId\":\"2\",\"searchId\":\"2\",\"passengers\":[{\"firstName\":\"Andres\",\"lastName\":\"Sacco\",\"documentNumber\":\"987654321\",\"documentType\":\"DNI\",\"nationality\":\"AR\",\"birthday\":\"1985-01-01\"}],\"contact\":{\"telephoneNumber\":\"54911111111\",\"email\":\"sacco.andres@gmail.com\"}}")
                                    })
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
                            schema = @Schema(implementation = ReservationDTO.class),
                            examples = {
                                @ExampleObject(
                                        name = "Valid reservation request",
                                        value =
                                                "{\n"
                                                        + "  \"itineraryId\": \"2\",\n"
                                                        + "  \"searchId\": \"2\",\n"
                                                        + "  \"passengers\": [\n"
                                                        + "    {\n"
                                                        + "      \"firstName\": \"Andres\",\n"
                                                        + "      \"lastName\": \"Sacco\",\n"
                                                        + "      \"documentNumber\": \"987654321\",\n"
                                                        + "      \"documentType\": \"DNI\",\n"
                                                        + "      \"nationality\": \"AR\",\n"
                                                        + "      \"birthday\": \"1985-01-01\"\n"
                                                        + "    }\n"
                                                        + "  ],\n"
                                                        + "  \"contact\": {\n"
                                                        + "    \"telephoneNumber\": \"54911111111\",\n"
                                                        + "    \"email\": \"sacco.andres@gmail.com\"\n"
                                                        + "  }\n"
                                                        + "}"),
                                @ExampleObject(
                                        name = "Invalid reservation request",
                                        value =
                                                "{\n"
                                                        + "  \"itineraryId\": \"3\",\n"
                                                        + "  \"searchId\": \"4\",\n"
                                                        + "  \"passengers\": [\n"
                                                        + "    {\n"
                                                        + "      \"firstName\": \"Maria\",\n"
                                                        + "      \"lastName\": \"Gonzalez\",\n"
                                                        + "      \"documentNumber\": \"123456789\",\n"
                                                        + "      \"documentType\": \"PASSPORT\",\n"
                                                        + "      \"nationality\": \"USS\",\n"
                                                        + "      \"birthday\": \"1990-05-20\"\n"
                                                        + "    }\n"
                                                        + "  ],\n"
                                                        + "  \"contact\": {\n"
                                                        + "    \"telephoneNumber\": \"1234567890\",\n"
                                                        + "    \"email\": \"maria.gonzalez@example.com\"\n"
                                                        + "  }\n"
                                                        + "}")
                            }))
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
