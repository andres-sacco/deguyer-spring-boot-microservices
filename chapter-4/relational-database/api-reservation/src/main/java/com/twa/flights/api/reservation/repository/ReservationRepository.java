package com.twa.flights.api.reservation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.twa.flights.api.reservation.model.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    // General queries
    List<Reservation> findByItineraryId(String itineraryId);
    List<Reservation> findByItineraryIdAndSearchId(String itineraryId, String searchId);

    // Order queries
    List<Reservation> findByItineraryIdOrderByIdAsc(String itineraryId);
    List<Reservation> findByItineraryIdOrderByIdDesc(String itineraryId);


    // Manual query
    @Query("SELECT r FROM Reservation r where r.itineraryId = :itineraryId")
    List<Reservation> retrieveByItineraryId(@Param("itineraryId") String itineraryId);

    @Query("SELECT r FROM Reservation r where r.itineraryId = :itineraryId and r.searchId = :searchId")
    List<Reservation>  retrieveByItineraryIdAndSearchId(@Param("itineraryId") String itineraryId, @Param("searchId") String searchId);
}
