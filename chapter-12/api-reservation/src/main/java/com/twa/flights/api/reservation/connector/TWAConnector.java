package com.twa.flights.api.reservation.connector;

import com.twa.flights.api.reservation.connector.configuration.EndpointConfiguration;
import com.twa.flights.api.reservation.connector.configuration.HostConfiguration;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.concurrent.TimeUnit;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

public abstract class TWAConnector {

    @LoadBalanced
    protected WebClient getConnector(
            HostConfiguration hostConfiguration, EndpointConfiguration endpointConfiguration) {

        HttpClient httpClient =
                HttpClient.create()
                        .option(
                                ChannelOption.CONNECT_TIMEOUT_MILLIS,
                                Math.toIntExact(endpointConfiguration.getConnectionTimeout()))
                        .doOnConnected(
                                conn ->
                                        conn.addHandler(
                                                        new ReadTimeoutHandler(
                                                                endpointConfiguration
                                                                        .getReadTimeout(),
                                                                TimeUnit.MILLISECONDS))
                                                .addHandler(
                                                        new WriteTimeoutHandler(
                                                                endpointConfiguration
                                                                        .getResponseTimeout(),
                                                                TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .baseUrl(
                        "http://" + hostConfiguration.getHost() + ":" + hostConfiguration.getPort())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT_ENCODING, "gzip")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
