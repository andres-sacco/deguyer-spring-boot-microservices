package com.twa.flights.api.reservation.documentation;

import java.io.File;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApiDocumentationTest {

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
}
