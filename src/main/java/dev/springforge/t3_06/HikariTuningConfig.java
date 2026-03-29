package dev.springforge.t3_06;

import org.springframework.context.annotation.Configuration;

/**
 * Exercise 2: HikariCP Tuning for Azure SQL
 *
 * YOUR TASKS:
 * 1. Return the correct max-lifetime value (in ms) for Azure SQL
 * 2. Return the correct keepalive-time value (in ms)
 *
 * WHY TUNING MATTERS FOR AZURE SQL:
 * Azure SQL's load balancer silently drops connections idle for ~30 minutes.
 * If HikariCP's max-lifetime exceeds this, your app gets dead connections
 * from the pool — causing random "connection reset" errors in production.
 *
 * RECOMMENDED SETTINGS:
 *   maximum-pool-size: 10
 *   connection-timeout: 30000     (30 seconds)
 *   max-lifetime: 1740000         (29 minutes — UNDER Azure's 30-min limit)
 *   keepalive-time: 30000         (30 seconds — prevent silent drops)
 *   connection-test-query: SELECT 1
 *
 * The max-lifetime setting is the single most important HikariCP
 * parameter for Azure SQL reliability.
 */
@Configuration
public class HikariTuningConfig {

    /**
     * Returns the recommended max-lifetime for Azure SQL in milliseconds.
     * Must be UNDER 30 minutes (1800000ms) to avoid silent connection drops.
     *
     * TODO: Return 1740000 (29 minutes)
     */
    public long getRecommendedMaxLifetime() {
        return 1740000;
    }

    /**
     * Returns the recommended keepalive-time in milliseconds.
     * Prevents Azure infrastructure from killing idle TCP connections.
     */
    public long getRecommendedKeepaliveTime() {
        return 30000;
    }
}
