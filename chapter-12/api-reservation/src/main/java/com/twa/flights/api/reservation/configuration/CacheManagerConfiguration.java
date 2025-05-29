package com.twa.flights.api.reservation.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.twa.flights.api.reservation.configuration.settings.CacheConfiguration;
import com.twa.flights.api.reservation.configuration.settings.CacheSettings;
import com.twa.flights.api.reservation.connector.CatalogConnector;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheManagerConfiguration {

    public static final String CATALOG_COUNTRY = "CATALOG_COUNTRY";

    private final CacheConfiguration cacheConfiguration;

    private final CatalogConnector catalogConnector;

    public CacheManagerConfiguration(
            final CacheConfiguration cacheConfiguration, final CatalogConnector catalogConnector) {
        this.cacheConfiguration = cacheConfiguration;
        this.catalogConnector = catalogConnector;
    }

    // Define a bean with all the caches, and which is the method responsible to obtain the value if
    // not exist in the cache
    @Bean
    public CacheManager cacheManager() {
        CacheSettings cacheCitySettings = cacheConfiguration.getCacheSettings(CATALOG_COUNTRY);

        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(
                List.of(
                        buildCaffeineCache(
                                CATALOG_COUNTRY, cacheCitySettings, catalogConnector::getCountry)));

        return simpleCacheManager;
    }

    // Define the size, duration, and the behaviour of the cache
    public static CaffeineCache buildCaffeineCache(
            String cacheName, CacheSettings settings, Function<String, Object> serviceCall) {

        return new CaffeineCache(
                cacheName,
                Caffeine.newBuilder()
                        .refreshAfterWrite(settings.getRefreshAfterWriteTime(), TimeUnit.MINUTES)
                        .expireAfterWrite(settings.getExpireAfterWriteTime(), TimeUnit.MINUTES)
                        .maximumSize(settings.getMaxSize())
                        .build(key -> serviceCall.apply(key.toString())));
    }
}
