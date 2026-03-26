package dev.springforge.t3_06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.Status;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 01: Custom Health Indicator
 *
 * YOUR TASK:
 * In HealthConfig.java, implement the health() method to return
 * Health.up() with details about the service.
 */
@DisplayName("T3-06 Ex01: Health Indicator")
class Ex01_HealthIndicatorTest {

    @Test
    @DisplayName("Health indicator returns UP status")
    void healthReturnsUp() {
        HealthConfig healthConfig = new HealthConfig();
        Health health = healthConfig.health();

        assertThat(health.getStatus()).isEqualTo(Status.UP);
    }

    @Test
    @DisplayName("Health indicator includes database detail")
    void healthIncludesDbDetail() {
        HealthConfig healthConfig = new HealthConfig();
        Health health = healthConfig.health();

        assertThat(health.getDetails()).containsKey("database");
        assertThat(health.getDetails().get("database")).isEqualTo("connected");
    }

    @Test
    @DisplayName("Health indicator includes service name")
    void healthIncludesServiceName() {
        HealthConfig healthConfig = new HealthConfig();
        Health health = healthConfig.health();

        assertThat(health.getDetails()).containsKey("service");
    }
}
