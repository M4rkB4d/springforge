package dev.springforge.t2_07;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * REST Client using Spring Boot 4.0's RestClient.
 */
@Service
public class WeatherClient {

    private final RestClient restClient;

    public WeatherClient(RestClient.Builder builder,
                         @Value("${weather.api.base-url:http://localhost:8080}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public WeatherResponse getWeather(String city) {
        return restClient.get()
                .uri("/weather/{city}", city)
                .retrieve()
                .body(WeatherResponse.class);
    }
}
