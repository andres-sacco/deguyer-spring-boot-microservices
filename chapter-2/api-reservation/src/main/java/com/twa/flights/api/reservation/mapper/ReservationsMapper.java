package com.twa.flights.api.reservation.mapper;

import com.twa.flights.api.reservation.dto.ReservationDTO;
import com.twa.flights.api.reservation.model.Reservation;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ReservationsMapper extends Converter<List<Reservation>, List<ReservationDTO>> {

    @Override
    List<ReservationDTO> convert(List<Reservation> source);

}