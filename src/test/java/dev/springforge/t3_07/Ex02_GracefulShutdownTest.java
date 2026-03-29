package dev.springforge.t3_07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EXERCISE 02: Graceful Shutdown
 *
 * DIFFICULTY: ★★★☆☆
 *
 * YOUR TASKS:
 * In GracefulShutdownConfig.java:
 *   1. Return "graceful" from getShutdownMode()
 *   2. Return 30 from getShutdownTimeoutSeconds()
 *
 * This verifies you understand the graceful shutdown pattern
 * for Azure Container Apps deployments.
 */
@DisplayName("T3-07 Ex02: Graceful Shutdown")
class Ex02_GracefulShutdownTest {

    @Test
    @DisplayName("Shutdown mode is graceful (not immediate)")
    void shutdownModeIsGraceful() {
        GracefulShutdownConfig config = new GracefulShutdownConfig();
        assertThat(config.getShutdownMode()).as("shutdown mode should be graceful").isEqualTo("graceful");
    }

    @Test
    @DisplayName("Shutdown timeout is reasonable for Azure")
    void shutdownTimeoutIsReasonable() {
        GracefulShutdownConfig config = new GracefulShutdownConfig();
        int timeout = config.getShutdownTimeoutSeconds();

        assertThat(timeout)
                .as("Timeout should be 15-60 seconds for Azure Container Apps")
                .isBetween(15, 60);
    }
}
