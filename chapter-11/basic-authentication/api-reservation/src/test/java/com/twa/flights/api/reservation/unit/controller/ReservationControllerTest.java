package com.twa.flights.api.reservation.unit.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.twa.flights.api.reservation.controller.ReservationController;
import com.twa.flights.api.reservation.dto.ReservationDTO;
import com.twa.flights.api.reservation.service.ReservationService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ReservationControllerTest {

    @Mock private ReservationService reservationService;

    @InjectMocks private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_get_reservation_that_exist() {
        // Arrange
        Long reservationId = 1L;
        ReservationDTO reservation = new ReservationDTO(reservationId, null, null, null, null);

        when(reservationService.getReservationById(reservationId)).thenReturn(reservation);

        // Act
        ResponseEntity<ReservationDTO> response =
                reservationController.getReservationById(reservationId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(reservationId, response.getBody().id());

        // Verify that the service's getReservationById method was called once
        verify(reservationService, times(1)).getReservationById(reservationId);
    }

    @Test
    void should_get_reservations() {
        // Arrange
        List<ReservationDTO> reservations = new ArrayList<>();
        ReservationDTO reservation1 = new ReservationDTO(1L, null, null, null, null);
        ReservationDTO reservation2 = new ReservationDTO(2L, null, null, null, null);
        reservations.add(reservation1);
        reservations.add(reservation2);

        when(reservationService.getReservations()).thenReturn(reservations);

        // Act
        ResponseEntity<List<ReservationDTO>> response = reservationController.getReservations();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        // Verify that the service's getReservations method was called once
        verify(reservationService, times(1)).getReservations();
    }

    @Test
    void should_save_a_reservation() {
        // Arrange
        ReservationDTO reservation = new ReservationDTO(1L, null, null, null, null);

        when(reservationService.save(any(ReservationDTO.class))).thenReturn(reservation);

        // Act
        ResponseEntity<ReservationDTO> response = reservationController.save(reservation);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().id());

        // Verify that the service's save method was called once
        verify(reservationService, times(1)).save(any(ReservationDTO.class));
    }

    @Test
    void should_update_a_reservation() {
        // Arrange
        Long reservationId = 1L;
        ReservationDTO updatedReservation =
                new ReservationDTO(reservationId, null, null, null, null);

        when(reservationService.update(eq(reservationId), any(ReservationDTO.class)))
                .thenReturn(updatedReservation);

        // Act
        ResponseEntity<ReservationDTO> response =
                reservationController.update(reservationId, updatedReservation);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(reservationId, response.getBody().id());

        // Verify that the service's update method was called once
        verify(reservationService, times(1)).update(eq(reservationId), any(ReservationDTO.class));
    }

    @Test
    void should_delete_a_reservation() {
        // Arrange
        Long reservationId = 1L;

        doNothing().when(reservationService).delete(reservationId);

        // Act
        ResponseEntity<Void> response = reservationController.delete(reservationId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the service's delete method was called once
        verify(reservationService, times(1)).delete(reservationId);
    }
}
