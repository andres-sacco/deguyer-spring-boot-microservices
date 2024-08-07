package com.twa.flights.api.reservation.mapper;

import com.twa.flights.api.reservation.dto.ReservationDTO;
import com.twa.flights.api.reservation.model.Reservation;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;


@Mapper(componentModel = "spring")
public interface ReservationDTOMapper extends Converter<ReservationDTO, Reservation> {

    @Override
    Reservation convert(ReservationDTO source);

}