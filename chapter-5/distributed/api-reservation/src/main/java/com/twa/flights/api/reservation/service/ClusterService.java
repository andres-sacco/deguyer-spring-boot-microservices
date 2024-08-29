package com.twa.flights.api.reservation.service;

import com.twa.flights.api.reservation.connector.ClusterConnector;
import com.twa.flights.api.reservation.connector.response.ClusterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClusterService {

    private final Logger LOGGER = LoggerFactory.getLogger(ClusterService.class);

    private final ClusterConnector clusterConnector;

    public ClusterService(ClusterConnector clusterConnector) {
        this.clusterConnector = clusterConnector;
    }

    public ClusterDTO getCluster(String id, String itineraryId) {
        LOGGER.debug("Obtain information of the clusters");
        return clusterConnector.getCluster(id, itineraryId);
    }
}
