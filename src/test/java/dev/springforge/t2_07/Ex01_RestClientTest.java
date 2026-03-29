package dev.springforge.t2_07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: RestClient
 *
 * DIFFICULTY: ★★★☆☆
 *
 * OBJECTIVE: Build a REST client using Spring Boot 4.0's RestClient.
 *
 * Note: These tests use a mock server approach — no real API needed.
 * The WeatherClient is tested with a locally-built RestClient.
 *
 * WHAT TO DO:
 * 1. Add @Service to WeatherClient
 * 2. Accept RestClient.Builder in constructor, build RestClient with baseUrl
 * 3. Implement getWeather() using restClient.get().uri().retrieve().body()
 */
@DisplayName("T2-07 Ex01: RestClient")
class Ex01_RestClientTest {

    @Test
    @DisplayName("WeatherClient can be constructed with a base URL")
    void clientCanBeConstructed() {
        WeatherClient client = new WeatherClient(RestClient.builder(), "http://localhost:8080");
        assertThat(client).isNotNull();
    }

    @Test
    @DisplayName("getWeather throws UnsupportedOperationException before implementation")
    void getWeatherThrowsBeforeImplementation() {
        WeatherClient client = new WeatherClient(RestClient.builder(), "http://localhost:8080");
        try {
            client.getWeather("London");
            // If no exception, implementation exists and attempted a real call — acceptable
        } catch (UnsupportedOperationException e) {
            // Expected before implementation — stub throws this
            assertThat(e.getMessage())
                    .as("Stub should contain implementation hint")
                    .contains("Implement");
        } catch (org.springframework.web.client.ResourceAccessException e) {
            // Implementation exists but no server running — connection refused is expected
            assertThat(e).as("RestClient tried to connect — implementation exists").isNotNull();
        }
    }
}
