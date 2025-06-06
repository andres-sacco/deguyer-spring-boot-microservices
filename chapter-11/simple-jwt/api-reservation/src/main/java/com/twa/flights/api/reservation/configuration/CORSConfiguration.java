package com.twa.flights.api.reservation.configuration;

import com.twa.flights.api.reservation.configuration.settings.CorsSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CORSConfiguration{

    private final CorsSettings settings;

    public CORSConfiguration(CorsSettings settings) {
        this.settings = settings;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(settings.getAllowedOrigins());
        config.setAllowedMethods(settings.getAllowedMethods());
        config.setAllowedHeaders(settings.getAllowedHeaders());
        config.setAllowCredentials(settings.isAllowCredentials());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
