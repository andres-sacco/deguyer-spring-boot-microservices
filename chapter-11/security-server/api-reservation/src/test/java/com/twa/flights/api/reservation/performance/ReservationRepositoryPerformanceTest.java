package com.twa.flights.api.reservation.performance;

import static org.junit.jupiter.api.Assertions.*;

import com.twa.flights.api.reservation.model.Reservation;
import com.twa.flights.api.reservation.repository.ReservationRepository;
import java.io.File;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.quickperf.junit5.QuickPerfTest;
import org.quickperf.spring.sql.QuickPerfSqlConfig;
import org.quickperf.sql.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

@Import(QuickPerfSqlConfig.class)
@QuickPerfTest
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ReservationRepositoryPerformanceTest {

    @Autowired ReservationRepository repository;

    static DockerComposeContainer dockerComposeContainer =
            new DockerComposeContainer(new File("src/test/resources/docker/docker-compose.yml"))
                    .waitingFor(
                            "api-reservation-db",
                            Wait.forLogMessage(
                                    ".*MySQL init process done. Ready for start up.*\\n", 1))
                    .withLocalCompose(true);

    @BeforeAll
    static void setUp() {
        dockerComposeContainer.start();
    }

    @AfterAll
    static void tearDown() {
        dockerComposeContainer.stop();
    }

    @Test
    @ExpectSelect(1) // Validate the number of queries that are executed
    @ExpectMaxQueryExecutionTime(
            thresholdInMilliSeconds = 2) // This check the duration of the execution of the query
    @AnalyzeSql
    void should_get_a_reservation() {
        Optional<Reservation> entity = repository.findById(1L);

        assertAll(
                () -> assertTrue(entity.isPresent()),
                () -> assertEquals("2", entity.get().getItineraryId()));
    }
}
