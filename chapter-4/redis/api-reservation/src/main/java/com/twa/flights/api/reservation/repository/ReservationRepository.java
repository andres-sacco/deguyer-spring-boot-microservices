package com.twa.flights.api.reservation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twa.flights.api.reservation.model.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, String> {

    // General queries
    List<Reservation> findByItineraryId(String itineraryId);
    List<Reservation> findByItineraryIdAndSearchId(String itineraryId, String searchId);

    // Order queries
    List<Reservation> findByItineraryIdOrderByIdAsc(String itineraryId);
    List<Reservation> findByItineraryIdOrderByIdDesc(String itineraryId);
}
