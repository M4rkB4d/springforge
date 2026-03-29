package dev.springforge.t2_07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * EXERCISE 02: Service Layer Pattern
 *
 * DIFFICULTY: ★★★☆☆
 *
 * OBJECTIVE: Build a service layer that processes data from the client.
 *
 * These tests mock the WeatherClient and test the service logic in isolation.
 * This is how you test services — mock dependencies, test behavior.
 *
 * WHAT TO DO:
 * 1. Add @Service to WeatherService
 * 2. Inject WeatherClient via constructor
 * 3. Implement getWeatherSummary() with temperature-based suffixes
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("T2-07 Ex02: Service Layer")
class Ex02_ServiceLayerTest {

    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    @DisplayName("getWeatherSummary returns formatted string for normal temperature")
    void normalTemperature() {
        given(weatherClient.getWeather("London"))
                .willReturn(new WeatherResponse("London", 15.0, "Cloudy", 75));

        String summary = weatherService.getWeatherSummary("London");
        assertThat(summary).as("normal temp summary for London").isEqualTo("London: 15.0°C, Cloudy");
    }

    @Test
    @DisplayName("getWeatherSummary appends (HOT!) for temperatures above 30")
    void hotTemperature() {
        given(weatherClient.getWeather("Dubai"))
                .willReturn(new WeatherResponse("Dubai", 42.0, "Sunny", 20));

        String summary = weatherService.getWeatherSummary("Dubai");
        assertThat(summary).as("hot temp should append (HOT!)").isEqualTo("Dubai: 42.0°C, Sunny (HOT!)");
    }

    @Test
    @DisplayName("getWeatherSummary appends (FREEZING!) for temperatures below 0")
    void freezingTemperature() {
        given(weatherClient.getWeather("Moscow"))
                .willReturn(new WeatherResponse("Moscow", -15.0, "Snow", 90));

        String summary = weatherService.getWeatherSummary("Moscow");
        assertThat(summary).as("freezing temp should append (FREEZING!)").isEqualTo("Moscow: -15.0°C, Snow (FREEZING!)");
    }

    @Test
    @DisplayName("getWeatherSummary handles exactly 30 as normal (not HOT)")
    void borderlineTemperature() {
        given(weatherClient.getWeather("Cairo"))
                .willReturn(new WeatherResponse("Cairo", 30.0, "Clear", 30));

        String summary = weatherService.getWeatherSummary("Cairo");
        assertThat(summary).as("30°C is normal, not HOT").isEqualTo("Cairo: 30.0°C, Clear");
    }
}
