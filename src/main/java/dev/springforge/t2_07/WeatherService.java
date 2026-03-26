package dev.springforge.t2_07;

/**
 * Exercise 2: Service Layer Pattern
 *
 * The service layer sits between the controller and the data/client layers.
 * It contains business logic — the controller handles HTTP, the service handles rules.
 *
 * YOUR TASKS:
 * 1. Annotate with @Service
 * 2. Inject WeatherClient via constructor
 * 3. Implement getWeatherSummary():
 *    - Call weatherClient.getWeather(city)
 *    - Return a formatted string: "{city}: {temperature}°C, {condition}"
 *    - If temperature > 30, append " (HOT!)"
 *    - If temperature < 0, append " (FREEZING!)"
 *
 * This pattern separates concerns:
 * - WeatherClient: knows HOW to call the API
 * - WeatherService: knows WHAT to do with the data
 * - Controller: knows HOW to serve it over HTTP
 */
// TODO: Add @Service
public class WeatherService {

    // TODO: Inject WeatherClient via constructor

    public String getWeatherSummary(String city) {
        // TODO: Call weatherClient.getWeather(city) and format the response
        throw new UnsupportedOperationException("Implement getWeatherSummary()");
    }
}
