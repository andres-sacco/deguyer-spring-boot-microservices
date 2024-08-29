package com.twa.flights.api.reservation.configuration;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.twa.flights.api.reservation.configuration.settings.CacheConfiguration;
import com.twa.flights.api.reservation.configuration.settings.CacheSettings;
import com.twa.flights.api.reservation.connector.CatalogConnector;
import com.twa.flights.api.reservation.serializer.CountrySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
public class CacheManagerConfiguration {

    public static final String CATALOG_COUNTRY = "CATALOG_COUNTRY";

    private final CacheConfiguration cacheConfiguration;

    private final JedisConnectionFactory catalogJedisConnectionFactory;

    private final CountrySerializer countrySerializer;

    @Autowired
    public CacheManagerConfiguration(final CacheConfiguration cacheConfiguration,
                                     final JedisConnectionFactory catalogJedisConnectionFactory,
                                     final CountrySerializer countrySerializer) {
        this.cacheConfiguration = cacheConfiguration;
        this.catalogJedisConnectionFactory = catalogJedisConnectionFactory;
        this.countrySerializer = countrySerializer;
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(List.of(Objects.requireNonNull(RedisCacheManager.builder(catalogJedisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration()).build().getCache(CATALOG_COUNTRY))));

        return simpleCacheManager;
    }

    private RedisCacheConfiguration redisCacheConfiguration() {
        CacheSettings cacheCitySettings = cacheConfiguration.getCacheSettings(CATALOG_COUNTRY);
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(countrySerializer))
                .entryTtl(Duration.ofMinutes(cacheCitySettings.getExpireAfterWriteTime()));
    }
}