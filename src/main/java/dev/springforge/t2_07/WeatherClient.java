package dev.springforge.t2_07;

import org.springframework.web.client.RestClient;

/**
 * Exercise 1: REST Client with RestClient
 *
 * Spring Boot 4.0's recommended HTTP client is RestClient (not RestTemplate).
 * RestClient is fluent, synchronous, and much cleaner.
 *
 * YOUR TASKS:
 * 1. Annotate this class with @Service
 * 2. Accept a RestClient.Builder in the constructor (Spring auto-injects it)
 * 3. Build the RestClient with a base URL: builder.baseUrl(baseUrl).build()
 * 4. Implement getWeather() using:
 *    restClient.get()
 *        .uri("/weather/{city}", city)
 *        .retrieve()
 *        .body(WeatherResponse.class)
 *
 * Hint: Store the RestClient as a final field, build it in the constructor.
 */
// TODO: Add @Service
public class WeatherClient {

    // TODO: Add a final RestClient field built from the builder below

    private final RestClient.Builder builder;
    private final String baseUrl;

    public WeatherClient(RestClient.Builder builder, String baseUrl) {
        this.builder = builder;
        this.baseUrl = baseUrl;
        // TODO: Build the RestClient: this.restClient = builder.baseUrl(baseUrl).build();
    }

    public WeatherResponse getWeather(String city) {
        // TODO: Use RestClient to GET /weather/{city}
        throw new UnsupportedOperationException("Implement getWeather()");
    }
}
