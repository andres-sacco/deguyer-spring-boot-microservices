package com.twa.api.gateway.controller;

import com.twa.api.gateway.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    private static final String ERROR_MESSAGE = " service is currently unavailable";
    private static final List<String> errors = List.of("It could suffer a high load", "It could be a temporary problem");

    @GetMapping("/clusters")
    public ResponseEntity<ErrorDTO> fallbackClusters() {
        ErrorDTO error = new ErrorDTO("Clusters".concat(ERROR_MESSAGE), errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/reservation")
    public ResponseEntity<ErrorDTO> fallbackReservation() {
        ErrorDTO error = new ErrorDTO("Reservation".concat(ERROR_MESSAGE), errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/catalog")
    public ResponseEntity<ErrorDTO> fallbackCatalog() {
        ErrorDTO error = new ErrorDTO("Catalog".concat(ERROR_MESSAGE), errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }
}

