package com.twa.flights.api.reservation.configuration;

import com.twa.flights.api.reservation.configuration.settings.RedisSettings;
import com.twa.flights.api.reservation.connector.response.CountryDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

@Configuration
@ConfigurationProperties
public class RedisConfiguration {

    private Map<String, RedisSettings> redis;

    @Bean
    public JedisConnectionFactory catalogJedisConnectionFactory() {
        RedisSettings settings = redis.get("remote-cache");
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(settings.getHost(),
                settings.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, CountryDTO> catalogRedisTemplate() {
        RedisTemplate<String, CountryDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(catalogJedisConnectionFactory());

        return redisTemplate;
    }

    public Map<String, RedisSettings> getRedis() {
        return redis;
    }

    public void setRedis(Map<String, RedisSettings> redis) {
        this.redis = redis;
    }
}
