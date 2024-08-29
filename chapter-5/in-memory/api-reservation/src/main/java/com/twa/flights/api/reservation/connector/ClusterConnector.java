package com.twa.flights.api.reservation.connector;


import com.twa.flights.api.reservation.connector.configuration.EndpointConfiguration;
import com.twa.flights.api.reservation.connector.configuration.HostConfiguration;
import com.twa.flights.api.reservation.connector.configuration.HttpConnectorConfiguration;
import com.twa.flights.api.reservation.connector.response.ClusterDTO;
import com.twa.flights.api.reservation.connector.response.CountryDTO;
import com.twa.flights.api.reservation.enums.APIError;
import com.twa.flights.api.reservation.exception.TWAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@Component
public class ClusterConnector extends TWAConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterConnector.class);

    private final String HOST = "api-clusters";
    private final String ENDPOINT = "clusters-by-id";

    private HttpConnectorConfiguration configuration;

    @Autowired
    public ClusterConnector(HttpConnectorConfiguration configuration) {
        this.configuration = configuration;
    }

    public ClusterDTO getCluster(String id, String itineraryId) {
        LOGGER.info("calling to api-clusters");

        HostConfiguration hostConfiguration = configuration.getHosts().get(HOST);
        EndpointConfiguration endpointConfiguration = hostConfiguration.getEndpoints().get(ENDPOINT);

        String url = UriComponentsBuilder.fromUriString(endpointConfiguration.getUrl())
                .buildAndExpand(Map.of("id", id, "itineraryId", itineraryId))
                .toUriString();

        return getConnector(hostConfiguration, endpointConfiguration).get()
                .uri(url).retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    LOGGER.error("Error to obtain the data with this id, itineraryId {}", id, itineraryId);
                    return Mono.error(new TWAException(APIError.CLUSTER_NOT_FOUND));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    LOGGER.error("Error to obtain the data with this id, itineraryId {}", id, itineraryId);
                    return Mono.error(new TWAException(APIError.CLUSTER_NOT_FOUND));
                })
                .bodyToMono(ClusterDTO.class).share()
                .block();
    }
}