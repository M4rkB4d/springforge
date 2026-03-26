package dev.springforge.t3_06;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Exercise 1: Custom Health Indicator
 *
 * YOUR TASKS:
 * 1. Implement the health() method to return Health.up()
 *    with detail "database", "connected"
 *
 * Hint:
 *   return Health.up()
 *       .withDetail("database", "connected")
 *       .withDetail("service", "springforge")
 *       .build();
 *
 * WHY HEALTH INDICATORS MATTER:
 * Azure App Service and Container Apps use /actuator/health for
 * liveness and readiness probes. A failing health check triggers
 * automatic restarts and stops routing traffic to unhealthy instances.
 *
 * Spring Boot Actuator exposes:
 *   /actuator/health           — overall health
 *   /actuator/health/liveness  — is the app alive?
 *   /actuator/health/readiness — is the app ready for traffic?
 */
@Component
public class HealthConfig implements HealthIndicator {

    @Override
    public Health health() {
        // TODO: Return Health.up() with details
        throw new UnsupportedOperationException("Implement the health indicator");
    }
}
