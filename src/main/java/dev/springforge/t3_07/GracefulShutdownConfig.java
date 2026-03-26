package dev.springforge.t3_07;

import org.springframework.context.annotation.Configuration;

/**
 * Exercise 2: Graceful Shutdown Configuration
 *
 * YOUR TASKS:
 * 1. Return the correct server.shutdown value
 * 2. Return the recommended shutdown timeout
 *
 * WHY GRACEFUL SHUTDOWN MATTERS:
 * When Azure Container Apps scales down or redeploys your app,
 * it sends SIGTERM. Without graceful shutdown, in-flight requests
 * are dropped — users get 502 errors.
 *
 * With graceful shutdown:
 *   1. SIGTERM received
 *   2. Stop accepting NEW requests
 *   3. Wait for IN-FLIGHT requests to complete (up to timeout)
 *   4. Shutdown
 *
 * Configuration:
 *   server.shutdown=graceful
 *   spring.lifecycle.timeout-per-shutdown-phase=30s
 *
 * On Azure Container Apps, also set:
 *   terminationGracePeriodSeconds: 45  (in container spec)
 *   (must be > Spring's timeout to give Spring time to drain)
 */
@Configuration
public class GracefulShutdownConfig {

    /**
     * Returns the server.shutdown property value for graceful shutdown.
     *
     * TODO: Return "graceful" (not "immediate")
     */
    public String getShutdownMode() {
        // TODO: Return "graceful"
        throw new UnsupportedOperationException("Return the shutdown mode");
    }

    /**
     * Returns the recommended shutdown timeout in seconds.
     * Must be less than Azure's terminationGracePeriodSeconds.
     *
     * TODO: Return 30
     */
    public int getShutdownTimeoutSeconds() {
        // TODO: Return 30
        throw new UnsupportedOperationException("Return the shutdown timeout");
    }
}
