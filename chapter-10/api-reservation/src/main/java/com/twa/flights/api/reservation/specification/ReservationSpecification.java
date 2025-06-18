package com.twa.flights.api.reservation.specification;

import com.twa.flights.api.reservation.model.Reservation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class ReservationSpecification implements Specification<Reservation> {

    Reservation entity;

    public ReservationSpecification(Reservation entity) {
        this.entity = entity;
    }

    @Override
    public Predicate toPredicate(
            Root<Reservation> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        // create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        CriteriaQuery<Reservation> cq = builder.createQuery(Reservation.class);

        // You need to define the main entity
        Root<Reservation> reservation = cq.from(Reservation.class);

        // Define all the conditions of the query
        Predicate codePredicate = builder.equal(reservation.get("searchId"), entity.getSearchId());

        predicates.add(codePredicate);

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
