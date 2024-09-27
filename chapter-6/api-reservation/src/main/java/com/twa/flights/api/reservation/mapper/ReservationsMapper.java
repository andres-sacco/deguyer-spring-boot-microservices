package com.twa.flights.api.reservation.mapper;

import com.twa.flights.api.reservation.dto.ReservationDTO;
import com.twa.flights.api.reservation.model.Reservation;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ReservationsMapper extends Converter<List<Reservation>, List<ReservationDTO>> {

    @Override
    List<ReservationDTO> convert(List<Reservation> source);
}
