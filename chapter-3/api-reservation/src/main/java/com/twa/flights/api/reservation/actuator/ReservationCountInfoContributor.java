package com.twa.flights.api.reservation.actuator;

import com.twa.flights.api.reservation.repository.ReservationRepository;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReservationCountInfoContributor implements InfoContributor {

    ReservationRepository repository;

    public ReservationCountInfoContributor(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void contribute(Info.Builder builder) {
        long reservationCount = repository.getReservations().size();
        Map<String, Object> reservationMap = new HashMap<>();
        reservationMap.put("amount", reservationCount);
        builder.withDetail("reservation-stats", reservationMap);
    }
}
