package com.twa.flights.api.reservation.service;

import com.twa.flights.api.reservation.configuration.CacheManagerConfiguration;
import com.twa.flights.api.reservation.connector.CatalogConnector;
import com.twa.flights.api.reservation.connector.response.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    private CatalogConnector connector;

    @Autowired
    public CatalogService(CatalogConnector connector) {
        this.connector = connector;
    }

    @Cacheable(cacheNames = CacheManagerConfiguration.CATALOG_COUNTRY, unless = "#result == null")
    public CountryDTO getCountryByCode(String code) {
        return connector.getCountry(code);
    }
}
