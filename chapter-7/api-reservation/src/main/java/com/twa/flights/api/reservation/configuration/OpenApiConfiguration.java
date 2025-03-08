package com.twa.flights.api.reservation.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =
                @Info(
                        title = "API Reservations",
                        version = "1.0",
                        description = "API for managing reservations",
                        contact =
                                @Contact(
                                        name = "Andres Sacco",
                                        email = "sacco.andres@gmail.com",
                                        url = "https://example.com"),
                        license =
                                @License(
                                        name = "Apache 2.0",
                                        url = "https://www.apache.org/licenses/LICENSE-2.0")))
public class OpenApiConfiguration {}
