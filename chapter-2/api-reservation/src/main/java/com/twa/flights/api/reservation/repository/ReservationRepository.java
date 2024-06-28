package com.twa.flights.api.reservation.repository;


import com.twa.flights.api.reservation.model.Contact;
import com.twa.flights.api.reservation.model.Passenger;
import com.twa.flights.api.reservation.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class ReservationRepository {

    static List<Reservation> reservations = new ArrayList<>();

    static {

        Passenger passenger = new Passenger();
        passenger.setFirstName("Andres");
        passenger.setLastName("Sacco");
        passenger.setId(1L);
        passenger.setDocumentType("DNI");
        passenger.setDocumentNumber("12345678");
        passenger.setBirthday(LocalDate.of(1985, 1, 1));

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setEmail("sacco.andres@gmail.com");
        contact.setTelephoneNumber("54911111111");

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPassengers(List.of(passenger));
        reservation.setItineraryId("1");
        reservation.setSearchId("3");
        reservation.setContact(contact);

        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Optional<Reservation> getReservationById(Long id) {
        List<Reservation> result = reservations.stream().filter(reservation -> Objects.equals(reservation.getId(), id))
                .toList();

        Reservation reservation = !result.isEmpty() ? result.get(0) : null;
        return Optional.ofNullable(reservation);
    }

    public Reservation save(Reservation reservation) {
        reservation.setId((long) (reservations.size() + 1));
        reservations.add(reservation);
        return reservation;
    }

    public Reservation update(Long id, Reservation reservation) {
        List<Reservation> result = reservations.stream().filter(reser -> reser.getId().equals(id)).toList();
        result.get(0).setId(reservation.getId());
        result.get(0).setItineraryId(reservation.getItineraryId());
        result.get(0).setSearchId(reservation.getSearchId());
        result.get(0).setPassengers(reservation.getPassengers());

        return result.get(0);
    }

    public void delete(Long id) {
        List<Reservation> result = reservations.stream().filter(reservation -> reservation.getId().equals(id)).toList();

        reservations.remove(result.get(0));
    }
}