package dev.springforge.t2_07;

import org.springframework.stereotype.Service;

/**
 * Service layer for weather data — applies business logic to client responses.
 */
@Service
public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public String getWeatherSummary(String city) {
        WeatherResponse response = weatherClient.getWeather(city);
        String summary = response.city() + ": " + response.temperature() + "°C, " + response.condition();
        if (response.temperature() > 30) {
            summary += " (HOT!)";
        } else if (response.temperature() < 0) {
            summary += " (FREEZING!)";
        }
        return summary;
    }
}
