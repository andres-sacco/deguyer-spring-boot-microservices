package com.twa.flights.api.reservation.unit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.twa.flights.api.reservation.connector.CatalogConnector;
import com.twa.flights.api.reservation.connector.response.CountryDTO;
import com.twa.flights.api.reservation.service.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CatalogServiceTest {

    @Mock private CatalogConnector catalogConnector;

    @InjectMocks private CatalogService catalogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_return_information_for_a_country() {
        // Arrange
        String countryCode = "US";
        CountryDTO country = new CountryDTO();
        country.setCode(countryCode);
        country.setName("United States");

        when(catalogConnector.getCountry(countryCode)).thenReturn(country);

        // Act
        CountryDTO result = catalogService.getCountryByCode(countryCode);

        // Assert
        assertNotNull(result);
        assertEquals(countryCode, result.getCode());
        assertEquals("United States", result.getName());

        // Verify that the connector's getCountry method was called once
        verify(catalogConnector, times(1)).getCountry(countryCode);
    }

    @Test
    void should_not_return_information_for_a_country() {
        // Arrange
        String countryCode = "ZZ"; // Non-existent country code

        when(catalogConnector.getCountry(countryCode)).thenReturn(null);

        // Act
        CountryDTO result = catalogService.getCountryByCode(countryCode);

        // Assert
        assertNull(result);

        // Verify that the connector's getCountry method was called once
        verify(catalogConnector, times(1)).getCountry(countryCode);
    }
}
