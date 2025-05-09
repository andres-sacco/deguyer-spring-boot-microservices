package com.twa.flights.api.reservation.actuator;

import com.twa.flights.api.reservation.repository.ReservationRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ReservationHealthIndicator implements HealthIndicator {
    ReservationRepository repository;

    public ReservationHealthIndicator(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Health health() {
        if (repository.count() == 0) {
            return Health.down()
                    .withDetail("reason", "There is a problem obtaining the reservations")
                    .build();
        }
        return Health.up()
                .withDetail("reason", "There is not a problem obtaining the reservations")
                .build();
    }
}
