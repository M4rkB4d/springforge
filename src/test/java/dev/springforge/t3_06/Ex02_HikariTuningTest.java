package dev.springforge.t3_06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: HikariCP Tuning for Azure SQL
 *
 * YOUR TASKS:
 * In HikariTuningConfig.java:
 *   1. Return 1740000 from getRecommendedMaxLifetime()
 *   2. Return 30000 from getRecommendedKeepaliveTime()
 *
 * This ensures you understand WHY these values matter:
 *   - max-lifetime < 30 min prevents Azure's silent connection drops
 *   - keepalive prevents firewall idle timeout kills
 */
@DisplayName("T3-06 Ex02: HikariCP Tuning")
class Ex02_HikariTuningTest {

    @Test
    @DisplayName("Max lifetime is under Azure's 30-minute limit")
    void maxLifetimeUnderAzureLimit() {
        HikariTuningConfig config = new HikariTuningConfig();
        long maxLifetime = config.getRecommendedMaxLifetime();

        assertThat(maxLifetime)
                .as("max-lifetime must be under 1800000ms (30 minutes)")
                .isLessThan(1_800_000L)
                .isGreaterThan(1_500_000L); // but not too low
    }

    @Test
    @DisplayName("Keepalive time is set to prevent idle drops")
    void keepaliveTimeIsSet() {
        HikariTuningConfig config = new HikariTuningConfig();
        long keepalive = config.getRecommendedKeepaliveTime();

        assertThat(keepalive)
                .as("keepalive-time should be 30-120 seconds")
                .isBetween(10_000L, 120_000L);
    }
}
