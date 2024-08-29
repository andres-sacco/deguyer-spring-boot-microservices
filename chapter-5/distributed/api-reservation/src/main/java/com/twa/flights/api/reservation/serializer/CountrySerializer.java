package com.twa.flights.api.reservation.serializer;

import com.twa.flights.api.reservation.connector.response.CountryDTO;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class CountrySerializer implements RedisSerializer<CountryDTO> {

    @Override
    public byte[] serialize(CountryDTO clusterSearch) {
        return JsonSerializer.serialize(clusterSearch);
    }

    @Override
    public CountryDTO deserialize(byte[] bytes) {
        return JsonSerializer.deserialize(bytes, CountryDTO.class);
    }
}