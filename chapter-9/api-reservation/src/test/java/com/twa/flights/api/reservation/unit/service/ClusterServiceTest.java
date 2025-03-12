package com.twa.flights.api.reservation.unit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.twa.flights.api.reservation.connector.ClusterConnector;
import com.twa.flights.api.reservation.connector.response.ClusterDTO;
import com.twa.flights.api.reservation.service.ClusterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClusterServiceTest {

    @Mock private ClusterConnector clusterConnector;

    @InjectMocks private ClusterService clusterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_return_information_for_a_cluster() {
        // Arrange
        String id = "123";
        String itineraryId = "456";

        String carrier = "AR";
        String provider = "456";
        String flightType = "ONE_WAY";

        ClusterDTO cluster = new ClusterDTO();
        cluster.setId(id);
        cluster.setCarrier(carrier);
        cluster.setProvider(provider);
        cluster.setFlightType(flightType);

        when(clusterConnector.getCluster(id, itineraryId)).thenReturn(cluster);

        // Act
        ClusterDTO result = clusterService.getCluster(id, itineraryId);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(carrier, result.getCarrier());
        assertEquals(provider, result.getProvider());
        assertEquals(flightType, result.getFlightType());

        // Verify that the connector's getCluster method was called once
        verify(clusterConnector, times(1)).getCluster(id, itineraryId);
    }

    @Test
    void should_not_return_information_for_a_cluster() {
        // Arrange
        String id = "999";
        String itineraryId = "999"; // Non-existent cluster

        when(clusterConnector.getCluster(id, itineraryId)).thenReturn(null);

        // Act
        ClusterDTO result = clusterService.getCluster(id, itineraryId);

        // Assert
        assertNull(result);

        // Verify that the connector's getCluster method was called once
        verify(clusterConnector, times(1)).getCluster(id, itineraryId);
    }
}
