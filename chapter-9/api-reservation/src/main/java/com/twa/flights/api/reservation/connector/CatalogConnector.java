package com.twa.flights.api.reservation.connector;

import com.twa.flights.api.reservation.connector.configuration.EndpointConfiguration;
import com.twa.flights.api.reservation.connector.configuration.HostConfiguration;
import com.twa.flights.api.reservation.connector.configuration.HttpConnectorConfiguration;
import com.twa.flights.api.reservation.connector.response.CountryDTO;
import com.twa.flights.api.reservation.enums.APIError;
import com.twa.flights.api.reservation.exception.TWAException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class CatalogConnector extends TWAConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogConnector.class);

    private final String HOST = "api-catalog";
    private final String ENDPOINT = "country-by-id";

    private HttpConnectorConfiguration configuration;

    public CatalogConnector(HttpConnectorConfiguration configuration) {
        this.configuration = configuration;
    }

    @Bulkhead(name = "catalog", fallbackMethod = "fallbackGetCountry")
    @CircuitBreaker(name = "catalog", fallbackMethod = "fallbackGetCountry")
    public CountryDTO getCountry(String code) {
        LOGGER.info("calling to api-catalog");

        HostConfiguration hostConfiguration = configuration.getHosts().get(HOST);
        EndpointConfiguration endpointConfiguration =
                hostConfiguration.getEndpoints().get(ENDPOINT);

        String url =
                UriComponentsBuilder.fromUriString(endpointConfiguration.getUrl())
                        .buildAndExpand(Collections.singletonMap("nationality", code))
                        .toUriString();

        return getConnector(hostConfiguration, endpointConfiguration)
                .get()
                .uri(url)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        response -> {
                            LOGGER.error("Error to obtain the data with this code {}", code);
                            return Mono.error(new TWAException(APIError.COUNTRY_NOT_FOUND));
                        })
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        response -> {
                            LOGGER.error("Error to obtain the data with this code {}", code);
                            return Mono.error(new TWAException(APIError.COUNTRY_NOT_FOUND));
                        })
                .bodyToMono(CountryDTO.class)
                .share()
                .block();
    }

    private CountryDTO fallbackGetCountry(String code, RuntimeException exception) {
        LOGGER.info("using fallback method for errors on api-catalog");

        CountryDTO country = new CountryDTO();
        country.setCode(code);
        country.setName("DEFAULT");

        return country;
    }
}
