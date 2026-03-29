package dev.springforge.t2_07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Provides RestClient.Builder bean for WeatherClient injection.
 * In Spring Boot 4.0, RestClient.Builder auto-configuration requires
 * an explicit bean definition when not using the restclient starter.
 */
@Configuration
public class WeatherClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}
