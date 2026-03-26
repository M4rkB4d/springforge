package dev.springforge.t2_07;

/**
 * Response DTO from a weather API.
 * Records make perfect DTOs — immutable, auto-generated methods.
 */
public record WeatherResponse(
    String city,
    double temperature,
    String condition,
    int humidity
) {}
