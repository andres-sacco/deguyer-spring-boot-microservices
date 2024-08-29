package com.twa.flights.api.reservation.service;

import com.twa.flights.api.reservation.dto.PassengerDTO;
import com.twa.flights.api.reservation.dto.ReservationDTO;
import com.twa.flights.api.reservation.enums.APIError;
import com.twa.flights.api.reservation.exception.TWAException;
import com.twa.flights.api.reservation.model.Reservation;
import com.twa.flights.api.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationService {

    private ReservationRepository repository;

    private ConversionService conversionService;

    private CatalogService catalogService;

    private ClusterService clusterService;

    @Autowired
    public ReservationService(ReservationRepository repository,
                              ConversionService conversionService,
                              CatalogService catalogService,
                              ClusterService clusterService) {
        this.repository = repository;
        this.conversionService = conversionService;
        this.catalogService = catalogService;
        this.clusterService = clusterService;
    }

    public List<ReservationDTO> getReservations() {
        return  conversionService.convert(repository.findAll(), List.class);
    }

    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> result = repository.findById(id);
        if(result.isEmpty()) {
            throw new TWAException(APIError.RESERVATION_NOT_FOUND);
        }
        return conversionService.convert(result.get(), ReservationDTO.class);
    }

    public ReservationDTO save(ReservationDTO reservation) {
        if(Objects.nonNull(reservation.id())) {
            throw new TWAException(APIError.RESERVATION_WITH_SAME_ID);
        }

        validateWithExternalServices(reservation);

        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        Reservation result = repository.save(Objects.requireNonNull(transformed));
        return conversionService.convert(result, ReservationDTO.class);
    }

    public ReservationDTO update(Long id, ReservationDTO reservation) {
        if(getReservationById(id) == null) {
            throw new TWAException(APIError.RESERVATION_NOT_FOUND);
        }

        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        Reservation result = repository.save(Objects.requireNonNull(transformed));
        return conversionService.convert(result, ReservationDTO.class);
    }

    public void delete(Long id) {
        if(getReservationById(id) == null) {
            throw new TWAException(APIError.RESERVATION_NOT_FOUND);
        }

        repository.deleteById(id);
    }

    private void validateWithExternalServices(ReservationDTO reservation) {
        if (reservation.itineraryId() != null && reservation.searchId() != null) {
            // Remove the comment in the case that you want to use this validation
            //clusterService.getCluster(reservation.searchId(), reservation.itineraryId());
        }

        if (reservation.passengers() != null) {
            for (PassengerDTO passenger : reservation.passengers()) {
                catalogService.getCountryByCode(passenger.nationality());
            }
        }
    }

}